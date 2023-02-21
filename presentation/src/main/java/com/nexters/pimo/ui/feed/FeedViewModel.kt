package com.nexters.pimo.ui.feed

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.domain.model.TextImage
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.domain.usecase.CloseTooltipUseCase
import com.nexters.pimo.domain.usecase.GetTooltipVisibilityUseCase
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
class FeedViewModel @Inject constructor(
    private val getTooltipVisibilityUseCase: GetTooltipVisibilityUseCase,
    private val closeTooltipUseCase: CloseTooltipUseCase
) : ContainerHost<FeedState, FeedSideEffect>,
    BaseViewModel() {

    override val container = container<FeedState, FeedSideEffect>(FeedState())

    val tempPost = Post(
        id = 0,
        writer = User(
            id = 0,
            profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
            nickname = "yjyoon"
        ),
        postedTime = LocalDateTime.now(),
        textImages = List(5) {
            TextImage(
                id = 0,
                text = "",
                imageUrl = "https://images.unsplash.com/photo-1676590809985-2335879fcd05?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1286&q=80"
            )
        },
        clapCount = 320,
        isClapped = false
    )

    init {
        intent {
            reduce { state.copy(uiState = UiState.Loading) }

            val showTooltip = getTooltipVisibilityUseCase().getOrThrow()
            delay(1000)

            reduce {
                state.copy(
                    uiState = UiState.Done,
                    user = User(
                        id = 0,
                        profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
                        nickname = "yjyoon",
                        posts = List(5) { tempPost }
                    ),
                    showTooltip = showTooltip
                )
            }
        }
    }

    fun onCloseTooltip() = intent {
        reduce { state.copy(showTooltip = false) }
        closeTooltipUseCase()
    }
}
