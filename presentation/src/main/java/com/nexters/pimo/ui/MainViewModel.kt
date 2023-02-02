package com.nexters.pimo.ui

import android.util.Log
import com.nexters.pimo.domain.usecase.GetDummyJsonUseCase
import com.nexters.pimo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDummyJsonUseCase: GetDummyJsonUseCase
) : BaseViewModel() {

    fun onDummyButtonClick() {
        launch {
            getDummyJsonUseCase()
                .onSuccess { Log.d("dummy test", it.toString()) }
                .onFailure { handleException(it) }
        }
    }
}
