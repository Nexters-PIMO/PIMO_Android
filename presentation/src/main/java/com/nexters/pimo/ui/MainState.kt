package com.nexters.pimo.ui

import com.nexters.pimo.domain.model.DummyJson
import com.nexters.pimo.ui.state.UiState

data class MainState(
    val dummyJson: DummyJson? = null,
    val uiState: UiState = UiState.Done
)

sealed class MainSideEffect {
    data class Toast(val text: String) : MainSideEffect()
}
