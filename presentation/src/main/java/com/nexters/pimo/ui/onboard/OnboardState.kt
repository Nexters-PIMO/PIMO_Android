package com.nexters.pimo.ui.onboard

data class OnboardState(
    val isLogin: Boolean = true
)

sealed class OnboardSideEffect {
}
