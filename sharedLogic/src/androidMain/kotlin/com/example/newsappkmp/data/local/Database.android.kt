package com.example.newsappkmp.data.local

import android.content.Context
import androidx.room.Room

lateinit var appContext: Context

actual fun getDatabaseBuilder(): ArticlesDatabase {
    val databaseFile = appContext.getDatabasePath(DATABASE_NAME)
    val builder = Room.databaseBuilder<ArticlesDatabase>(
        context = appContext,
        name = databaseFile.absolutePath,
    )
    return buildArticlesDatabase(builder)
}

private const val DATABASE_NAME = "articles.db"
