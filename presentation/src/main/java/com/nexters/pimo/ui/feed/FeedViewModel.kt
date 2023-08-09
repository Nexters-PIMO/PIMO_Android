package com.nexters.pimo.ui.feed

import com.nexters.pimo.domain.repository.PostRepository
import com.nexters.pimo.domain.repository.UserRepository
import com.nexters.pimo.domain.usecase.CloseTooltipUseCase
import com.nexters.pimo.domain.usecase.GetTooltipVisibilityUseCase
import com.nexters.pimo.feature.tts.TtsService
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val getTooltipVisibilityUseCase: GetTooltipVisibilityUseCase,
    private val closeTooltipUseCase: CloseTooltipUseCase,
    private val ttsService: TtsService
) : ContainerHost<FeedState, FeedSideEffect>,
    BaseViewModel() {

    override val container = container<FeedState, FeedSideEffect>(FeedState())

    init {
        ttsService.setCallback { onAudioFinished() }
        intent {
            reduce { state.copy(uiState = UiState.Loading) }

            val showTooltip = getTooltipVisibilityUseCase().getOrThrow()
            val user = userRepository.getMe().getOrThrow()
            val feeds = postRepository.getFeed().getOrThrow()
            reduce {
                state.copy(
                    uiState = UiState.Done,
                    user = user,
                    feeds = feeds,
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
