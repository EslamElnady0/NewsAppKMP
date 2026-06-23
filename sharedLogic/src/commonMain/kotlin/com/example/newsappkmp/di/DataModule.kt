package com.example.newsappkmp.di

import com.example.newsappkmp.data.local.ArticlesDatabase
import com.example.newsappkmp.data.local.getDatabaseBuilder
import com.example.newsappkmp.data.remote.ArticlesService
import com.example.newsappkmp.data.repository.ArticleRepository
import com.example.newsappkmp.domain.repository.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single { getDatabaseBuilder() }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
        }
    }
    single { ArticlesService(get()) }
    single { get<ArticlesDatabase>().favoriteArticleDao() }
    single<Repository> { ArticleRepository(get(), get()) }
}
