package com.example.newsappkmp.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappkmp.NewsCanvas
import com.example.newsappkmp.NewsInk
import com.example.newsappkmp.domain.model.Article
import com.example.newsappkmp.presentation.articles.ArticlesContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticlesScreen(
    state: ArticlesContract.State,
    onRetry: () -> Unit,
    onToggleFavorite: (Article) -> Unit,
) {
    Scaffold(
        containerColor = NewsCanvas,
        topBar = { ArticlesAppBar() },
    ) { innerPadding ->
        when {
            state.isLoading && state.articles.isEmpty() -> LoadingFeed(innerPadding)
            state.errorMessage != null && state.articles.isEmpty() -> ErrorContent(
                message = state.errorMessage.orEmpty(),
                contentPadding = innerPadding,
                onRetry = onRetry,
            )
            else -> ArticleList(
                articles = state.articles,
                contentPadding = innerPadding,
                onToggleFavorite = onToggleFavorite,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticlesAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Articles",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = NewsInk,
            scrolledContainerColor = NewsInk,
        ),
    )
}

@Composable
private fun ArticleList(
    articles: List<Article>,
    contentPadding: PaddingValues,
    onToggleFavorite: (Article) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = contentPadding.calculateTopPadding() + 18.dp,
            end = 16.dp,
            bottom = 28.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        itemsIndexed(
            items = articles,
            key = { _, article -> article.id },
        ) { index, article ->
            AnimatedArticleCard(
                article = article,
                index = index,
                onToggleFavorite = onToggleFavorite,
            )
        }
    }
}
