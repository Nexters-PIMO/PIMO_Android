package com.nexters.pimo.ui.splash

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
) : ContainerHost<SplashState, SplashSideEffect>,
    BaseViewModel() {

    override val container = container<SplashState, SplashSideEffect>(SplashState(state = SplashUiState.Loading))

    init {
        intent {
            delay(SPLASH_TIME)
            reduce {
                //TODO: 로그인 여부 확인
                state.copy(
                    state = SplashUiState.AlreadyLoggedIn //test
                )
            }
        }
    }

    companion object {
        private const val SPLASH_TIME = 2000L
    }

}