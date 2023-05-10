package com.nexters.pimo.ui.onboard

import androidx.lifecycle.SavedStateHandle
import com.nexters.pimo.ui.base.BaseViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class OnboardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ContainerHost<OnboardState, OnboardSideEffect>,
    BaseViewModel() {

    override val container = container<OnboardState, OnboardSideEffect>(OnboardState())

    private val isLogin: Boolean = savedStateHandle.get<Boolean>(KEY_MODE)!!

    init {
        intent {
            reduce {
                state.copy(
                    isLogin = isLogin,
                )
            }
        }
    }

    companion object {
        const val KEY_MODE = "KEY_MODE"
    }
}
