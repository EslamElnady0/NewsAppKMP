package com.example.newsappkmp.presentation.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappkmp.domain.model.Article
import com.example.newsappkmp.domain.usecase.GetArticlesUseCase
import com.example.newsappkmp.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ArticlesContract.State())
    val state: StateFlow<ArticlesContract.State> = _state.asStateFlow()

    private var loadArticlesJob: Job? = null

    fun onIntent(intent: ArticlesContract.Intent) {
        when (intent) {
            ArticlesContract.Intent.LoadArticles -> loadArticles()
            is ArticlesContract.Intent.ToggleFavorite -> toggleFavorite(intent.article)
        }
    }

    private fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            runCatching { toggleFavoriteUseCase(article) }
                .onFailure { error ->
                    _state.update { currentState ->
                        currentState.copy(
                            errorMessage = error.message ?: DEFAULT_FAVORITE_ERROR_MESSAGE,
                        )
                    }
                }
        }
    }

    fun observeState(onStateChanged: (ArticlesContract.State) -> Unit): StateObservation {
        val observationJob = viewModelScope.launch {
            state.collect(onStateChanged)
        }
        return JobStateObservation(observationJob)
    }

    private fun loadArticles() {
        loadArticlesJob?.cancel()
        loadArticlesJob = viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    errorMessage = null,
                )
            }

            getArticlesUseCase()
                .catch { error ->
                    _state.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = error.message ?: DEFAULT_ERROR_MESSAGE,
                        )
                    }
                }
                .collect { articles ->
                    _state.value = ArticlesContract.State(
                        isLoading = false,
                        articles = articles,
                        errorMessage = null,
                    )
                }
        }
    }

    private companion object {
        const val DEFAULT_ERROR_MESSAGE = "Unable to load articles"
        const val DEFAULT_FAVORITE_ERROR_MESSAGE = "Unable to update favorite"
    }
}

interface StateObservation {
    fun cancel()
}

private class JobStateObservation(
    private val job: Job,
) : StateObservation {
    override fun cancel() {
        job.cancel()
    }
}
