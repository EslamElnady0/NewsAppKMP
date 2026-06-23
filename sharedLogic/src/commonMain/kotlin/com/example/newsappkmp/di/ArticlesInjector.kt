package com.example.newsappkmp.di

import com.example.newsappkmp.presentation.articles.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel
        get() = get()
}
