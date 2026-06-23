package com.example.newsappkmp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {
    @Query("SELECT * FROM favorite_articles")
    fun observeFavorites(): Flow<List<FavoriteArticleEntity>>

    @Upsert
    suspend fun upsert(article: FavoriteArticleEntity)

    @Query("DELETE FROM favorite_articles WHERE id = :articleId")
    suspend fun deleteById(articleId: String)
}
