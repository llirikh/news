package com.example.news.presentation.screen.article

import com.example.news.domain.model.NewsItem

data class ArticleDetailState(
    val newsItem: NewsItem? = null,
    val isLoading: Boolean = true
)
