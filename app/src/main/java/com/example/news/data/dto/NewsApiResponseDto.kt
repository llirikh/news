package com.example.news.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsApiResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsItemDto>
)