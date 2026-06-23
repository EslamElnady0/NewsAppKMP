package com.example.newsappkmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsappkmp.articles.ArticlesScreen
import com.example.newsappkmp.presentation.articles.ArticlesContract
import com.example.newsappkmp.presentation.articles.ArticlesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val articlesViewModel = koinViewModel<ArticlesViewModel>()
    val state by articlesViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(articlesViewModel) {
        articlesViewModel.onIntent(ArticlesContract.Intent.LoadArticles)
    }

    NewsAppTheme {
        ArticlesScreen(
            state = state,
            onRetry = {
                articlesViewModel.onIntent(ArticlesContract.Intent.LoadArticles)
            },
            onToggleFavorite = { article ->
                articlesViewModel.onIntent(ArticlesContract.Intent.ToggleFavorite(article))
            },
        )
    }
}
