package com.example.news.presentation.screen.article

sealed interface ArticleDetailEvent {
    data object BackClicked : ArticleDetailEvent
}
