package com.example.newsappkmp.di

import com.example.newsappkmp.data.local.ArticlesDatabase
import com.example.newsappkmp.data.remote.ArticlesService
import com.example.newsappkmp.data.repository.ArticleRepository
import com.example.newsappkmp.domain.repository.Repository
import com.example.newsappkmp.domain.usecase.GetArticlesUseCase
import com.example.newsappkmp.domain.usecase.ToggleFavoriteUseCase
import com.example.newsappkmp.presentation.articles.ArticlesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val articlesModule = module {
    single {
        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    },
                )
            }
        }
    }
    single { ArticlesService(get()) }
    single { get<ArticlesDatabase>().favoriteArticleDao() }
    single<Repository> { ArticleRepository(get(), get()) }
    factory { GetArticlesUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
    viewModelOf(::ArticlesViewModel)
}

fun initKoin(database: ArticlesDatabase) {
    startKoin {
        modules(
            module { single { database } },
            articlesModule,
        )
    }
}

class KoinInitializer {
    fun initialize(database: ArticlesDatabase) {
        initKoin(database)
    }
}

class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel
        get() = get()
}
