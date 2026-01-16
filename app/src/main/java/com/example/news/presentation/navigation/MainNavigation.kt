package com.example.news.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.news.presentation.screen.article.ArticleDetailScreen
import com.example.news.presentation.screen.feed.FeedScreen
import kotlinx.serialization.Serializable

sealed interface MainScreenNavigationRoute {
    @Serializable
    data object Feed : MainScreenNavigationRoute

    @Serializable
    data class Article(val articleId: String) : MainScreenNavigationRoute
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = MainScreenNavigationRoute.Feed
    ) {
        composable<MainScreenNavigationRoute.Feed> {
            FeedScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<MainScreenNavigationRoute.Article> { backStackEntry ->
            val articleRoute = backStackEntry.toRoute<MainScreenNavigationRoute.Article>()
            ArticleDetailScreen(
                articleId = articleRoute.articleId,
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}