package com.nexters.pimo.ui.login

import com.nexters.pimo.domain.model.ProviderToken
import com.nexters.pimo.domain.usecase.LoginUseCase
import com.nexters.pimo.feature.kakao.KakaoToken
import com.nexters.pimo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ContainerHost<LoginState, LoginSideEffect>,
    BaseViewModel() {

    override val container = container<LoginState, LoginSideEffect>(LoginState())

    fun login(token: KakaoToken) = intent {
        val providerToken = ProviderToken.kakao(
            idToken = token.idToken,
            accessToken = token.kakaoAccessToken,
            refreshToken = token.kakaoRefreshToken
        )
        val result = loginUseCase(providerToken).getOrThrow()
        reduce {
            state.copy(result = result)
        }
    }

}