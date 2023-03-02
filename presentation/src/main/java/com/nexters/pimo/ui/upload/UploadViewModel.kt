package com.nexters.pimo.ui.upload

import android.graphics.Bitmap
import com.nexters.pimo.feature.ocr.OcrService
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.model.TextBitmap
import com.nexters.pimo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.toImmutableList
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import java.lang.Integer.max
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val ocrService: OcrService
) : ContainerHost<UploadState, UploadSideEffect>,
    BaseViewModel() {
    override val container =
        container<UploadState, UploadSideEffect>(
            UploadState(
                textBitmaps = listOf(),
                selectedIndex = 0,
                mode = UploadState.Mode.New
            )
        )

    fun selectImage(index: Int) = intent {
        reduce { state.copy(selectedIndex = index) }
    }

    fun removeImage(index: Int) = intent {
        val textBitmaps = state.textBitmaps.toMutableList()
        textBitmaps.removeAt(index)
        val selectedIndex = with(state.selectedIndex) {
            if (this >= index) max(0, this - 1)
            else this
        }
        reduce {
            state.copy(
                textBitmaps = textBitmaps.toImmutableList(),
                selectedIndex = selectedIndex
            )
        }
    }

    fun onPickImage(imagePath: String, bitmap: Bitmap) = intent {
        reduce { state.copy(uiState = UiState.Loading) }

        val text = ocrService.getTextOfImage(File(imagePath))
        if (text.trim().isEmpty()) {
            reduce { state.copy(uiState = UiState.Done) }
            postSideEffect(UploadSideEffect.ShowNonTextImageToast)
            return@intent
        }

        val textBitmap = TextBitmap(text, bitmap)
        reduce {
            state.copy(
                textBitmaps = state.textBitmaps + textBitmap,
                selectedIndex = state.textBitmaps.size,
                uiState = UiState.Done
            )
        }
    }

    fun setLoading() = intent {
        reduce { state.copy(uiState = UiState.Loading) }
    }

    fun setDone() = intent {
        reduce { state.copy(uiState = UiState.Done) }
    }
}
