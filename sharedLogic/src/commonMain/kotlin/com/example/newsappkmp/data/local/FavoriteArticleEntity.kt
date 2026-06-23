package com.example.newsappkmp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_articles")
data class FavoriteArticleEntity(
    @PrimaryKey val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val author: String,
)
