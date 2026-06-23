package com.example.newsappkmp.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            domainModule,
            presentationModule,
        )
    }
}

class KoinInitializer {
    fun initialize() {
        initKoin()
    }
}
