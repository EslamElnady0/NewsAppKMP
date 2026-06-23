package com.example.newsappkmp.di

import com.example.newsappkmp.presentation.articles.ArticlesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::ArticlesViewModel)
}
