package com.nexters.pimo.ui.profile.state

import androidx.compose.runtime.Stable

@Stable
class NicknameState(initialText: String = "") : TextFieldState(
    initialText = initialText,
    validator = {
        it.isNotBlank() && it.length <= MAX_LENGTH_EN
    }
) {
    companion object {
        const val MAX_LENGTH_KO = 8
        const val MAX_LENGTH_EN = 16
    }
}