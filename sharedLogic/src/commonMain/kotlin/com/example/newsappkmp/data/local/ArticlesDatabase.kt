package com.example.newsappkmp.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [FavoriteArticleEntity::class],
    version = 1,
)
@ConstructedBy(ArticlesDatabaseConstructor::class)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun favoriteArticleDao(): FavoriteArticleDao
}

@Suppress("KotlinNoActualForExpect")
expect object ArticlesDatabaseConstructor : RoomDatabaseConstructor<ArticlesDatabase> {
    override fun initialize(): ArticlesDatabase
}

fun buildArticlesDatabase(
    builder: RoomDatabase.Builder<ArticlesDatabase>,
): ArticlesDatabase = builder
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.Default)
    .build()
