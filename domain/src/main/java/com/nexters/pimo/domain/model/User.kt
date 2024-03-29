package com.nexters.pimo.domain.model

data class User(
    val id: String,
    val nickname: String,
    val archiveName: String = "밤에 쓰는 편지",
    val profileImageUrl: String,
    val posts: List<Post> = emptyList()
) {
    companion object {
        val Unspecified = User("", "", "", "", emptyList())
    }
}
