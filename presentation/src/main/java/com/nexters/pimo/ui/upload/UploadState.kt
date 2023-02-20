package com.nexters.pimo.ui.upload

import com.nexters.pimo.ui.model.TextBitmap
import com.nexters.pimo.ui.state.UiState

data class UploadState(
    val textBitmaps: List<TextBitmap>,
    val selectedIndex: Int,
    val mode: Mode,
    val uiState: UiState = UiState.Done
) {
    enum class Mode { New, Edit }
}

sealed class UploadSideEffect {
    object ShowNonTextImageToast : UploadSideEffect()
}
