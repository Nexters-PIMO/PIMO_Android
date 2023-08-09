package com.nexters.pimo.remote.model

import com.nexters.pimo.data.model.FeedData

data class FeedResponse(
    val id: String,
    val user: UserResponse,
    val favorite: Int,
    val isClicked: Boolean,
    val createdAt: String,
    val items: List<FeedItemResponse>
)

data class FeedItemResponse(
    val imageUrl: String,
    val content: String
)

fun FeedItemResponse.toData() = FeedData.FeedItemData(
    imageUrl, content
)

fun FeedResponse.toData() = FeedData(
    id, user.toData(), favorite, isClicked, createdAt, items.map { it.toData() }
)
