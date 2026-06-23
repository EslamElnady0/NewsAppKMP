package com.example.newsappkmp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class ArticlesService(
    private val client: HttpClient,
) {
    suspend fun fetchArticles(): List<NetworkArticle> {
        val response = client.get(HEADLINES_URL) {
            parameter("country", "us")
            parameter("category", "business")
            parameter("apiKey", API_KEY)
        }.body<NewsApiResponse>()

        check(response.status == "ok") {
            response.message ?: "NewsAPI returned an unsuccessful response"
        }
        return response.articles
    }

    private companion object {
        const val HEADLINES_URL = "https://newsapi.org/v2/top-headlines"
        const val API_KEY = "5e001f528dac42199e161deb3b6d1836"
    }
}
