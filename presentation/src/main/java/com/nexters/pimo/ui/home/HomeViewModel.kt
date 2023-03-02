package com.nexters.pimo.ui.home

import com.nexters.pimo.domain.usecase.CloseTooltipUseCase
import com.nexters.pimo.domain.usecase.GetTooltipVisibilityUseCase
import com.nexters.pimo.feature.tts.TtsService
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.model.Dummy
import com.nexters.pimo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTooltipVisibilityUseCase: GetTooltipVisibilityUseCase,
    private val closeTooltipUseCase: CloseTooltipUseCase,
    private val ttsService: TtsService
) : ContainerHost<HomeState, HomeSideEffect>,
    BaseViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState())

    init {
        ttsService.setCallback { onAudioFinished() }
        intent {
            reduce { state.copy(uiState = UiState.Loading) }

            val showTooltip = getTooltipVisibilityUseCase().getOrThrow()
            delay(1000)
            reduce {
                state.copy(
                    uiState = UiState.Done,
                    posts = Dummy.dummyPosts,
                    showTooltip = showTooltip
                )
            }
        }
    }

    fun onCloseTooltip() = intent {
        reduce { state.copy(showTooltip = false) }
        closeTooltipUseCase()
    }

    fun onPlayAudio(text: String) = intent {
        ttsService.speakText(text)
        reduce { state.copy(isAudioPlaying = true) }
    }

    fun onStopAudio() = intent {
        ttsService.stop()
        reduce { state.copy(isAudioPlaying = false) }
    }

    private fun onAudioFinished() = intent {
        reduce { state.copy(isAudioPlaying = false) }
    }
}
