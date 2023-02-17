package com.nexters.pimo.ui.home

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.domain.model.User
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
class HomeViewModel @Inject constructor() : ContainerHost<HomeState, HomeSideEffect>,
    BaseViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState())

    val tempPost = Post(
        id = 0,
        writer = User(
            id = 0,
            profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
            nickname = "yjyoon"
        ),
        postedTime = LocalDateTime.now(),
        textImages = listOf(),
        clapCount = 320,
        isClapped = false
    )

    init {
        intent {
            reduce { state.copy(uiState = UiState.Loading) }

            delay(1000)
            reduce { state.copy(posts = List(5) { tempPost }) }

            reduce { state.copy(uiState = UiState.Done) }
        }
    }
}
