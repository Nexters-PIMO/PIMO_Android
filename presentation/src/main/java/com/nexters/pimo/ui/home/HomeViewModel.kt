package com.nexters.pimo.ui.home

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.domain.model.TextImage
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.domain.usecase.CloseTooltipUseCase
import com.nexters.pimo.domain.usecase.GetTooltipVisibilityUseCase
import com.nexters.pimo.feature.tts.TtsService
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTooltipVisibilityUseCase: GetTooltipVisibilityUseCase,
    private val closeTooltipUseCase: CloseTooltipUseCase,
    private val ttsService: TtsService
) : ContainerHost<HomeState, HomeSideEffect>,
    BaseViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState())

    val tempPost = Post(
        id = "",
        writer = User(
            id = "",
            profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
            nickname = "yjyoon"
        ),
        postedTime = LocalDateTime.now(),
        textImages = List(5) {
            TextImage(
                text = "피모 화이팅 ${it + 1}",
                imageUrl = "https://images.unsplash.com/photo-1676590809985-2335879fcd05?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1286&q=80"
            )
        },
        clapCount = 320,
        isClapped = false
    )

    init {
        ttsService.setCallback { onAudioFinished() }
        intent {
            reduce { state.copy(uiState = UiState.Loading) }

            val showTooltip = getTooltipVisibilityUseCase().getOrThrow()
            delay(1000)
            reduce {
                state.copy(
                    uiState = UiState.Done,
                    posts = List(5) { tempPost },
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
