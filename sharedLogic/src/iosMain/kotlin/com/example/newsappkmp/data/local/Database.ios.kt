package com.example.newsappkmp.data.local

import androidx.room.Room
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSHomeDirectory

@OptIn(ExperimentalForeignApi::class)
actual fun getDatabaseBuilder(): ArticlesDatabase {
    val databasePath = NSHomeDirectory() + "/articles.db"
    val builder = Room.databaseBuilder<ArticlesDatabase>(name = databasePath)
    return buildArticlesDatabase(builder)
}
