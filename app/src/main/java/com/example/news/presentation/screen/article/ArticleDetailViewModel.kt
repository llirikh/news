package com.example.news.presentation.screen.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repository.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel(assistedFactory = ArticleDetailViewModel.Factory::class)
class ArticleDetailViewModel @AssistedInject constructor(
    @Assisted private val articleId: String,
    @Assisted private val onBack: () -> Unit,
    private val newsRepository: NewsRepository
) : ViewModel() {

    var state by mutableStateOf(ArticleDetailState())
        private set

    fun onEvent(event: ArticleDetailEvent) {
        when (event) {
            ArticleDetailEvent.BackClicked -> onBack()
        }
    }

    private fun loadArticle() = viewModelScope.launch {
        state = state.copy(isLoading = true)
        val article = withContext(Dispatchers.IO) {
            newsRepository.getArticleById(articleId)
        }
        state = state.copy(
            newsItem = article,
            isLoading = false
        )
    }

    init {
        loadArticle()
    }

    @AssistedFactory
    interface Factory {
        fun create(articleId: String, onBack: () -> Unit): ArticleDetailViewModel
    }
}
