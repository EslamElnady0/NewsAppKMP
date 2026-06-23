package com.example.newsappkmp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NewsApiResponse(
    val status: String = "",
    val message: String? = null,
    val articles: List<NetworkArticle> = emptyList(),
)

@Serializable
internal data class NetworkArticle(
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    @SerialName("urlToImage")
    val imageUrl: String? = null,
)
