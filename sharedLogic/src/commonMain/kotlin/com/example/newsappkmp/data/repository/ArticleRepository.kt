package com.example.newsappkmp.data.repository

import com.example.newsappkmp.data.local.FavoriteArticleDao
import com.example.newsappkmp.data.mapper.toDomainArticle
import com.example.newsappkmp.data.mapper.toFavoriteEntity
import com.example.newsappkmp.data.remote.ArticlesService
import com.example.newsappkmp.domain.model.Article
import com.example.newsappkmp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

internal class ArticleRepository(
    private val articlesService: ArticlesService,
    private val favoriteArticleDao: FavoriteArticleDao,
) : Repository {
    override fun getArticles(): Flow<List<Article>> {
        val networkArticles = flow {
            emit(articlesService.fetchArticles().mapNotNull { it.toDomainArticle() })
        }

        return combine(networkArticles, favoriteArticleDao.observeFavorites()) { articles, favorites ->
            val favoriteIds = favorites.asSequence().map { it.id }.toSet()
            articles.map { article ->
                article.copy(isFavorite = article.id in favoriteIds)
            }
        }
    }

    override suspend fun toggleFavorite(article: Article) {
        if (article.isFavorite) {
            favoriteArticleDao.deleteById(article.id)
        } else {
            favoriteArticleDao.upsert(article.toFavoriteEntity())
        }
    }
}
