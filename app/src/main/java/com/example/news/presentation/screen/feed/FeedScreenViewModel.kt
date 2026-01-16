package com.example.news.presentation.screen.feed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repository.NewsRepository
import com.example.news.domain.model.NewsItem
import com.example.news.presentation.navigation.MainScreenNavigationRoute
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel(assistedFactory = FeedScreenViewModel.Factory::class)
class FeedScreenViewModel @AssistedInject constructor(
    @Assisted val navigate: (MainScreenNavigationRoute) -> Unit,
    private val newsRepository: NewsRepository
) : ViewModel() {

    var state by mutableStateOf(FeedScreenState())
        private set

    init {
        loadNews()
    }

    fun onEvent(event: FeedScreenEvent) {
        when (event) {
            is FeedScreenEvent.NewsItemReadClicked -> onNewsItemReadClicked(event.newsItem)
        }
    }

    private fun onNewsItemReadClicked(newsItem: NewsItem) {
        navigate(MainScreenNavigationRoute.Article(newsItem.id))
    }

    private fun loadNews() = viewModelScope.launch {
        val newsFromRepository = withContext(Dispatchers.IO) {
            newsRepository.loadNews()
        }
        state = state.copy(news = newsFromRepository)
    }

    @AssistedFactory
    interface Factory {
        fun create(navigate: (MainScreenNavigationRoute) -> Unit): FeedScreenViewModel
    }
}