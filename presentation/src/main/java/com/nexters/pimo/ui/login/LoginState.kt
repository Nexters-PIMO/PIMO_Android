package com.nexters.pimo.ui.login

import com.nexters.pimo.domain.model.LoginResult

data class LoginState(
    val result: LoginResult = LoginResult.Unspecified,
)

sealed class LoginSideEffect {
    data class Toast(val text: String) : LoginSideEffect()
}
