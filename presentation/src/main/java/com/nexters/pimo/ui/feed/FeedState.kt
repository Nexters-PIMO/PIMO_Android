package com.nexters.pimo.ui.feed

import androidx.annotation.DrawableRes
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.state.UiState

data class FeedState(
    val user: User = User.Unspecified,
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    val uiState: UiState = UiState.Loading
)

sealed class FeedSideEffect {
}

enum class FeedViewMode(
    @DrawableRes val iconRes: Int
) {
    List(R.drawable.ic_list),
    Grid(R.drawable.ic_grid)
}
