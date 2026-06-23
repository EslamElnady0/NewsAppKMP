package com.example.newsappkmp.di

import com.example.newsappkmp.domain.usecase.GetArticlesUseCase
import com.example.newsappkmp.domain.usecase.ToggleFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetArticlesUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
}
