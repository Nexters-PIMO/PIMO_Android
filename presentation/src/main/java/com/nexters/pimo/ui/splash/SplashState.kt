package com.nexters.pimo.ui.splash

data class SplashState(
    val state: SplashUiState
)

sealed class SplashSideEffect {
}

sealed interface SplashUiState {
    object Loading : SplashUiState
    object AlreadyLoggedIn : SplashUiState
    object NeedToOnboard : SplashUiState
}
