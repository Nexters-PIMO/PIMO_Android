package com.nexters.pimo.ui.profile

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nexters.pimo.domain.model.ProviderToken
import com.nexters.pimo.domain.model.SignUpUser
import com.nexters.pimo.domain.repository.AuthRepository
import com.nexters.pimo.domain.repository.ImageRepository
import com.nexters.pimo.domain.repository.UserRepository
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.profile.state.ArchiveNameState
import com.nexters.pimo.ui.profile.state.Mode
import com.nexters.pimo.ui.profile.state.NicknameState
import com.nexters.pimo.ui.profile.state.ProfileSideEffect
import com.nexters.pimo.ui.profile.state.ProfileState
import com.nexters.pimo.ui.profile.state.TextFieldState.Companion.MAX_LENGTH_EN
import com.nexters.pimo.ui.util.BitmapUtil.toFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.nio.charset.Charset
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val imageRepository: ImageRepository
) : ContainerHost<ProfileState, ProfileSideEffect>,
    BaseViewModel() {

    private val provider = savedStateHandle.get<String>(EXTRA_KEY_PROVIDER)!!
    private val identifier = savedStateHandle.get<String>(EXTRA_KEY_IDENTIFIER)!!
    private val mode: Mode = savedStateHandle.get<Mode>(KEY_MODE)!!

    override val container = container<ProfileState, ProfileSideEffect>(
        ProfileState(
            nicknameState = NicknameState(""),
            archiveNameState = ArchiveNameState(""),
            imageState = null,
            mode = mode,
            provider = provider,
            identifier = identifier
        )
    )

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

        viewModelScope.launch {
            val isValidate = userRepository.validateNickname(nickname).getOrThrow()
            nicknameStateNew.isDuplicateChecked = isValidate.not()
        }

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

        viewModelScope.launch {
            val isValidate = userRepository.validateArchive(archiveName).getOrThrow()
            archiveNameStateNew.isDuplicateChecked = isValidate.not()
        }

        reduce {
            state.copy(archiveNameState = archiveNameStateNew)
        }
    }

    fun initNicknameState() = intent {
        val nicknameStateNew = NicknameState(state.nicknameState.text)
        nicknameStateNew.isDuplicateChecked = false
        nicknameStateNew.isValidInput = false

        reduce {
            state.copy(nicknameState = nicknameStateNew)
        }
    }

    fun initArchiveNameState() = intent {
        val archiveNameStateNew = ArchiveNameState(state.archiveNameState.text)
        archiveNameStateNew.isDuplicateChecked = false
        archiveNameStateNew.isValidInput = false

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
        for (element in text) {
            if (!Character.isLetterOrDigit(element)) {
                return false
            }
        }
        return true
    }

    private fun isValidTextLength(text: String): Boolean {
        return text.toByteArray(Charset.forName("EUC-KR")).size <= MAX_LENGTH_EN
    }

    fun signUp(context: Context) = intent {
        viewModelScope.launch {
            val profileImageUrl = imageRepository.uploadImage(
                state.imageState!!.toFile(
                    context = context,
                    name = LocalDateTime.now().toString()
                )
            ).getOrThrow()
            val user = SignUpUser(
                token = ProviderToken.kakao(state.identifier),
                nickname = state.nicknameState.text,
                archiveName = state.archiveNameState.text,
                profileImageUrl = profileImageUrl
            )
            authRepository.signUp(user).onSuccess {
                reduce {
                    state.copy(pageIdx = state.pageIdx + 1)
                }
            }
                .onFailure { Log.d("error", it.stackTraceToString()) }
        }
    }

    companion object {
        const val KEY_MODE = "KEY_MODE"
        const val EXTRA_KEY_PROVIDER = "EXTRA_KEY_PROVIDER"
        const val EXTRA_KEY_IDENTIFIER = "EXTRA_KEY_IDENTIFIER"
    }
}