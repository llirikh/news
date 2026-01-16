package com.example.news.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.news.domain.model.NewsItem
import com.example.news.presentation.ui.theme.NewsTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    onReadClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onReadClicked
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            if (newsItem.imageUrl.isNotBlank()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(newsItem.imageUrl)
                        .networkCachePolicy(CachePolicy.ENABLED)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = "News image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.size(10.dp))
            }

            Text(
                text = newsItem.title,
                fontSize = 22.sp
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Text(
                text = newsItem.description,
                maxLines = 3,
                fontSize = 18.sp
            )
            Spacer(
                modifier = Modifier.size(5.dp)
            )
            HorizontalDivider()
            Spacer(
                modifier = Modifier.size(5.dp)
            )
            Text(
                text = newsItem.publishedAt.date.toString(),
                fontSize = 15.sp
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun NewsItemPreview() {
    NewsTheme {
        NewsItem(
            newsItem = NewsItem(
                id = "1",
                title = "News Title 11",
                description = "News Description 1",
                publishedBy = "News Source 1",
                publishedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                imageUrl = "https://picsum.photos/200/300"
            ),
            onReadClicked = {}
        )
    }
}
