package com.nexters.pimo.ui.home

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.state.UiState

data class HomeState(
    val posts: List<Post> = listOf(), // TODO: Int -> Post
    val newPostCount: Int = 0,
    val showOcrHelp: Boolean = false,
    val uiState: UiState = UiState.Loading
)

sealed class HomeSideEffect {
    object HideOcrHelp : HomeSideEffect()
    data class Toast(val text: String) : HomeSideEffect()
}
