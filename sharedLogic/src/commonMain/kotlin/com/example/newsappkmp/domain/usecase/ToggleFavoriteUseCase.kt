package com.example.newsappkmp.domain.usecase

import com.example.newsappkmp.domain.model.Article
import com.example.newsappkmp.domain.repository.Repository

class ToggleFavoriteUseCase(
    private val repository: Repository,
) {
    suspend operator fun invoke(article: Article) = repository.toggleFavorite(article)
}
