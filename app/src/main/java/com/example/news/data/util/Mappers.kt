package com.example.news.data.util

import com.example.news.data.dto.NewsItemDto
import com.example.news.domain.model.NewsItem
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.security.MessageDigest
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun NewsItemDto.toModel(): NewsItem {
    return NewsItem(
        id = generateNewsItemIdFromUrl(url),
        title = title ?: "No Title",
        description = description ?: "No Description",
        publishedBy = source?.name ?: "Unknown Source",
        publishedAt = publishedAt?.let {
            Instant.parse(it).toLocalDateTime(TimeZone.currentSystemDefault())
        } ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        imageUrl = urlToImage ?: ""
    )
}

fun generateNewsItemIdFromUrl(url: String?): String {
    return if (url != null) {
        MessageDigest.getInstance("MD5")
            .digest(url.toByteArray())
            .joinToString("") { "%02x".format(it) }
    } else {
        System.currentTimeMillis().toString()
    }
}