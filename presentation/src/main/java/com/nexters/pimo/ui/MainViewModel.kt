package com.nexters.pimo.ui

import com.nexters.pimo.domain.usecase.GetDummyJsonUseCase
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDummyJsonUseCase: GetDummyJsonUseCase
) : ContainerHost<MainState, MainSideEffect>, BaseViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    fun onDummyButtonClick() {
        intent {
            reduce { state.copy(uiState = UiState.Loading) }
            getDummyJsonUseCase()
                .onSuccess {
                    reduce { state.copy(dummyJson = it, uiState = UiState.Done) }
                    postSideEffect(MainSideEffect.Toast("데이터를 성공적으로 불러왔습니다."))
                }
                .onFailure {
                    reduce { state.copy(uiState = UiState.Failure(it)) }
                    handleException(it)
                }
        }
    }
}
