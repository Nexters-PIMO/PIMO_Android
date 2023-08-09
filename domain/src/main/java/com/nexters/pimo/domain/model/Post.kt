package com.nexters.pimo.domain.model

import java.time.LocalDateTime

data class Post(
    val id: String,
    val writer: User,
    val postedTime: LocalDateTime,
    val textImages: List<TextImage>,
    val clapCount: Int,
    val isClapped: Boolean
)
