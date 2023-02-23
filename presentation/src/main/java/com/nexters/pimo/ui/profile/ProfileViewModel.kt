package com.nexters.pimo.ui.profile

import android.graphics.Bitmap
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.profile.state.ArchiveNameState
import com.nexters.pimo.ui.profile.state.NicknameState
import com.nexters.pimo.ui.profile.state.ProfileSideEffect
import com.nexters.pimo.ui.profile.state.ProfileState
import com.nexters.pimo.ui.profile.state.TextFieldState.Companion.MAX_LENGTH_EN
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.nio.charset.Charset
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    ) : ContainerHost<ProfileState, ProfileSideEffect>,
    BaseViewModel() {

    override val container = container<ProfileState, ProfileSideEffect>(ProfileState(nicknameState = NicknameState(""), archiveNameState = ArchiveNameState(""), imageState = null))

    fun goBack() = intent {
        reduce { state.copy(pageIdx = state.pageIdx - 1) }
    }

    fun goForward() = intent {
        reduce { state.copy(pageIdx = state.pageIdx + 1) }
    }

    fun checkNickname() = intent {
        val nickname = state.nicknameState.text
        val nicknameStateNew = NicknameState(nickname)
        nicknameStateNew.isDuplicateChecked = true
        nicknameStateNew.isValidInput = true;

        if (!isValidTextFormat(nickname)) {
            nicknameStateNew.isValidInput = false
            nicknameStateNew.inputCheckMsg = R.string.profile_input_form_error
        } else if (!isValidTextLength(nickname)) {
            nicknameStateNew.isValidInput = false
            nicknameStateNew.inputCheckMsg = R.string.profile_input_length_error
        }
        //TODO: 중복 체크

        reduce {
            state.copy(nicknameState = nicknameStateNew)
        }
    }

    fun checkArchiveName() = intent {
        val archiveName = state.archiveNameState.text
        val archiveNameStateNew = ArchiveNameState(archiveName)
        archiveNameStateNew.isDuplicateChecked = true
        archiveNameStateNew.isValidInput = true;

        if (!isValidTextFormat(archiveName)) {
            archiveNameStateNew.isValidInput = false
            archiveNameStateNew.inputCheckMsg = R.string.profile_input_form_error
        } else if (!isValidTextLength(archiveName)) {
            archiveNameStateNew.isValidInput = false
            archiveNameStateNew.inputCheckMsg = R.string.profile_input_length_error
        }
        //TODO: 중복 체크

        reduce {
            state.copy(archiveNameState = archiveNameStateNew)
        }
    }

    fun onPickImage(bitmap: Bitmap) = intent {
        reduce {
            state.copy(imageState = bitmap)
        }
    }

    private fun isValidTextFormat(text: String): Boolean {
        for (element in text){
            if (!Character.isLetterOrDigit(element)) {
                return false
            }
        }
        return true
    }

    private fun isValidTextLength(text: String): Boolean {
        return text.toByteArray(Charset.forName("EUC-KR")).size <= MAX_LENGTH_EN
    }

}