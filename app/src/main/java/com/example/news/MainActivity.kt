package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.navigation.MainNavigation
import com.example.news.presentation.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier
) {
    val navHostController = rememberNavController()

    MainNavigation(
        modifier = Modifier.fillMaxSize(),
        navHostController = navHostController
    )
}