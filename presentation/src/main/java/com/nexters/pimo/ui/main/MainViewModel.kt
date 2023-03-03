package com.nexters.pimo.ui.main

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ContainerHost<MainState, MainSideEffect>, BaseViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    fun onSelectPost(post: Post) = intent {
        reduce { state.copy(selectedPost = post) }
        // TODO: Check is this post created by me
    }
}
