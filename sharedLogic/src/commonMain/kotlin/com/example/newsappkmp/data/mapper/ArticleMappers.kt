package com.example.newsappkmp.data.mapper

import com.example.newsappkmp.data.local.FavoriteArticleEntity
import com.example.newsappkmp.data.remote.NetworkArticle
import com.example.newsappkmp.domain.model.Article

internal fun NetworkArticle.toDomainArticle(): Article? {
    val articleUrl = url?.trim().orEmpty()
    val articleTitle = title?.trim().orEmpty()
    if (articleUrl.isEmpty() || articleTitle.isEmpty()) return null

    return Article(
        id = articleUrl,
        imageUrl = imageUrl?.trim().orEmpty(),
        title = articleTitle,
        description = description?.trim().orEmpty(),
        author = author?.trim().takeUnless { it.isNullOrEmpty() } ?: "Unknown author",
    )
}

internal fun Article.toFavoriteEntity() = FavoriteArticleEntity(
    id = id,
    imageUrl = imageUrl,
    title = title,
    description = description,
    author = author,
)
