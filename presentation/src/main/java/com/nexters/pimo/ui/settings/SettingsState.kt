package com.nexters.pimo.ui.settings

import androidx.annotation.StringRes
import com.nexters.pimo.domain.model.User

data class SettingsState(
    val user: User = User.Unspecified,
)

sealed class SettingsSideEffect {
}

data class DialogState(
    val visible: Boolean,
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @StringRes val leftStringRes: Int,
    @StringRes val rightStringRes: Int,
)