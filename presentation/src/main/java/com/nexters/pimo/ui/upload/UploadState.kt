package com.nexters.pimo.ui.upload

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.state.UiState

data class UploadState(
    val post: Post,
    val mode: Mode = if (post.id < 0) Mode.New else Mode.Edit,
    val uiState: UiState = UiState.Done
) {
    enum class Mode { New, Edit }
}

sealed class UploadSideEffect {
    data class Toast(val text: String) : UploadSideEffect()
}
