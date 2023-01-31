package com.nexters.pimo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.pimo.domain.usecase.GetDummyJsonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDummyJsonUseCase: GetDummyJsonUseCase
) : ViewModel() {

    fun onDummyButtonClick() {
        viewModelScope.launch {
            getDummyJsonUseCase()
                .onSuccess { Log.d("dummy test", it.toString()) }
        }
    }
}
