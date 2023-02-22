package com.nexters.pimo.ui.profile

import android.graphics.Bitmap
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.profile.state.ArchiveNameState
import com.nexters.pimo.ui.profile.state.NicknameState
import com.nexters.pimo.ui.profile.state.ProfileSideEffect
import com.nexters.pimo.ui.profile.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
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
        nicknameStateNew.isInputValid = true; //test

        //TODO:
        //형식 체크
        //글자수 체크
        //중복 체크

        reduce {
            state.copy(nicknameState = nicknameStateNew)
        }
    }

    fun checkArchiveName() = intent {
        val archiveName = state.archiveNameState.text
        val archiveNameStateNew = ArchiveNameState(archiveName)
        archiveNameStateNew.isDuplicateChecked = true
        archiveNameStateNew.isInputValid = true; //test

        //TODO:
        //형식 체크
        //글자수 체크
        //중복 체크

        reduce {
            state.copy(archiveNameState = archiveNameStateNew)
        }
    }

    fun onPickImage(bitmap: Bitmap) = intent {
        reduce {
            state.copy(imageState = bitmap)
        }
    }

}