package com.example.news.presentation.screen.feed

import com.example.news.domain.model.NewsItem

data class FeedScreenState(
    val news: List<NewsItem> = emptyList()
)
