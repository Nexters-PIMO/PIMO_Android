package com.nexters.pimo.ui.splash

import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.usecase.LoginUseCase
import com.nexters.pimo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ContainerHost<SplashState, SplashSideEffect>,
    BaseViewModel() {

    override val container =
        container<SplashState, SplashSideEffect>(SplashState(state = SplashUiState.Loading))

    init {
        intent {
            delay(SPLASH_TIME)
            loginUseCase.invoke().onSuccess { loginResult ->
                reduce {
                    state.copy(
                        state = when (loginResult) {
                            is LoginResult.SignedIn -> SplashUiState.AlreadyLoggedIn
                            else -> SplashUiState.NeedToOnboard
                        }
                    )
                }
            }
        }
    }

    companion object {
        private const val SPLASH_TIME = 2000L
    }

}