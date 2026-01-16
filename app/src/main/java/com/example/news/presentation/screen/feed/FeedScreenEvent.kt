package com.example.news.presentation.screen.feed

import com.example.news.domain.model.NewsItem

sealed interface FeedScreenEvent {
    data class NewsItemReadClicked(val newsItem: NewsItem) : FeedScreenEvent
}