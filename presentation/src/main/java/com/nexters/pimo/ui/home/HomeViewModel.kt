package com.nexters.pimo.ui.home

import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ContainerHost<HomeState, HomeSideEffect>,
    BaseViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState())

    init {
        intent {
            reduce { state.copy(uiState = UiState.Loading) }
            delay(1000) // TODO: fetch data
            reduce { state.copy(uiState = UiState.Done) }
        }
    }
}
