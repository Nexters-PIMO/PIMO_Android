package com.nexters.pimo.data.model

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.domain.model.TextImage
import java.time.LocalDateTime

data class FeedData(
    val id: String,
    val user: UserData,
    val favorite: Int,
    val isClicked: Boolean,
    val createdAt: String,
    val items: List<FeedItemData>
) {
    data class FeedItemData(
        val imageUrl: String,
        val content: String
    )
}

fun FeedData.FeedItemData.toDomain() = TextImage(
    text = content,
    imageUrl = imageUrl
)

fun FeedData.toDomain() = Post(
    id = id,
    writer = user.toDomain(),
    postedTime = LocalDateTime.parse(createdAt),
    clapCount = favorite,
    isClapped = isClicked,
    textImages = items.map { it.toDomain() }
)
