package com.nexters.pimo.ui.state

sealed interface UiState {
    object Done : UiState
    object Loading : UiState
    data class Failure(val exception: Throwable?) : UiState
}
