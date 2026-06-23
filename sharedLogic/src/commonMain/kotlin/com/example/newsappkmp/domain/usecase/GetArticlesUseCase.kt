package com.example.newsappkmp.domain.usecase

import com.example.newsappkmp.domain.model.Article
import com.example.newsappkmp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase(
    private val repository: Repository,
) {
    operator fun invoke(): Flow<List<Article>> = repository.getArticles()
}
