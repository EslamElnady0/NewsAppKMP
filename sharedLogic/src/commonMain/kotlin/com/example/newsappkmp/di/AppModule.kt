package com.example.newsappkmp.di

import com.example.newsappkmp.data.local.ArticlesDatabase
import org.koin.core.context.startKoin

fun initKoin(database: ArticlesDatabase) {
    startKoin {
        modules(
            dataModule(database),
            domainModule,
            presentationModule,
        )
    }
}

class KoinInitializer {
    fun initialize(database: ArticlesDatabase) {
        initKoin(database)
    }
}
