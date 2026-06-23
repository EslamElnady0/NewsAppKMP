package com.example.newsappkmp.presentation.articles

import com.example.newsappkmp.domain.model.Article

object ArticlesContract {
    data class State(
        val isLoading: Boolean = false,
        val articles: List<Article> = emptyList(),
        val errorMessage: String? = null,
    )

    sealed interface Intent {
        data object LoadArticles : Intent

        data class ToggleFavorite(
            val article: Article,
        ) : Intent
    }
}
