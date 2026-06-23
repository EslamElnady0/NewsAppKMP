package com.example.newsappkmp.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<ArticlesDatabase> {
    val applicationContext = context.applicationContext
    val databaseFile = applicationContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<ArticlesDatabase>(
        context = applicationContext,
        name = databaseFile.absolutePath,
    )
}

fun createAndroidDatabase(context: Context): ArticlesDatabase =
    buildArticlesDatabase(getDatabaseBuilder(context))

private const val DATABASE_NAME = "articles.db"
