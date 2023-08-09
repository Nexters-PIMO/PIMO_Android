package com.nexters.pimo.ui.profile.state

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.nexters.pimo.ui.R

abstract class TextFieldState(
    initialText: String,
    var isDuplicateChecked: Boolean,
    private val validator: (String) -> Boolean = { true },
) {
    var text by mutableStateOf(initialText)
    private var isFocusedDirty: Boolean by mutableStateOf(false)
    private var isFocused: Boolean by mutableStateOf(false)

    val isValid: Boolean
        get() = validator(text)

    val isError: Boolean
        get() = isFocusedDirty && !isValid

    var isValidInput: Boolean = false

    @StringRes
    var inputCheckMsg: Int = R.string.profile_input_available

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) {
            isFocusedDirty = true
        }
    }

    companion object {
        const val MAX_LENGTH_KO = 8
        const val MAX_LENGTH_EN = 16
    }
}

