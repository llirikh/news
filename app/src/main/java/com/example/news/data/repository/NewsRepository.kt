package com.example.news.data.repository

import com.example.news.data.dto.NewsApiResponseDto
import com.example.news.data.util.toModel
import com.example.news.domain.model.NewsItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val httpClient: HttpClient
) {
    private var cachedNews: List<NewsItem> = emptyList()

    suspend fun loadNews(): List<NewsItem> {
        return try {
            val response = httpClient.get("/top-headlines") {
                parameter("category", "technology")
            }.body<NewsApiResponseDto>()
            cachedNews = response.articles.map { it.toModel() }
            cachedNews
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getArticleById(id: String): NewsItem? {
        if (cachedNews.isEmpty()) {
            loadNews()
        }
        return cachedNews.find { it.id == id }
    }
}