package com.example.newsappkmp.domain.model

data class Article(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val author: String,
    val isFavorite: Boolean = false,
)
