package com.example.newsappkmp.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun getDatabaseBuilder(): RoomDatabase.Builder<ArticlesDatabase> {
    val documentsUrl = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = true,
        error = null,
    )
    val databasePath = requireNotNull(documentsUrl?.path) + "/articles.db"
    return Room.databaseBuilder<ArticlesDatabase>(name = databasePath)
}

fun createIosDatabase(): ArticlesDatabase = buildArticlesDatabase(getDatabaseBuilder())

class IosDatabaseFactory {
    fun createDatabase(): ArticlesDatabase = createIosDatabase()
}
