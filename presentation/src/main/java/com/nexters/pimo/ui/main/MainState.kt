package com.nexters.pimo.ui.main

import com.nexters.pimo.domain.model.Post

data class MainState(
    val selectedPost: Post? = null
)

sealed class MainSideEffect {

}
