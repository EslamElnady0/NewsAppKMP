package com.example.newsappkmp.domain.repository

import com.example.newsappkmp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getArticles(): Flow<List<Article>>

    suspend fun toggleFavorite(article: Article)
}
