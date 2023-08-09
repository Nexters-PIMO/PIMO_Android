package com.nexters.pimo.ui.profile

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.model.ProviderToken
import com.nexters.pimo.domain.model.SignUpUser
import com.nexters.pimo.domain.repository.AuthRepository
import com.nexters.pimo.domain.repository.ImageRepository
import com.nexters.pimo.domain.repository.UserRepository
import com.nexters.pimo.domain.usecase.LoginUseCase
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
    private val imageRepository: ImageRepository,
    private val loginUseCase: LoginUseCase
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
            identifier = identifier,
            loginResult = LoginResult.Unspecified
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

        nicknameStateNew.isValidInput = true;

        if (!isValidTextFormat(nickname)) {
            nicknameStateNew.isValidInput = false
            nicknameStateNew.inputCheckMsg = R.string.profile_input_form_error
        } else if (!isValidTextLength(nickname)) {
            nicknameStateNew.isValidInput = false
            nicknameStateNew.inputCheckMsg = R.string.profile_input_length_error
        }

        if (nicknameStateNew.isValidInput.not()) {
            reduce {
                state.copy(nicknameState = nicknameStateNew)
            }
            return@intent
        }

        viewModelScope.launch {
            userRepository.validateNickname(nickname)
                .onSuccess {
                    if (it.not()) nicknameStateNew.inputCheckMsg = R.string.profile_input_duplicate
                    nicknameStateNew.isValidInput = it
                    reduce {
                        state.copy(nicknameState = nicknameStateNew)
                    }
                }
                .onFailure { it.printStackTrace() }
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

        if (archiveNameStateNew.isValidInput.not()) {
            reduce {
                state.copy(archiveNameState = archiveNameStateNew)
            }
            return@intent
        }

        viewModelScope.launch {
            userRepository.validateArchive(archiveName)
                .onSuccess {
                    if (it.not()) archiveNameStateNew.inputCheckMsg =
                        R.string.profile_input_duplicate
                    archiveNameStateNew.isValidInput = it
                    reduce {
                        state.copy(archiveNameState = archiveNameStateNew)
                    }
                }
                .onFailure {
                    it.printStackTrace()
                }
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
                loginUseCase.invoke(ProviderToken.kakao(state.identifier)).onSuccess {
                    reduce {
                        state.copy(loginResult = it)
                    }
                }
            }
        }
    }

    companion object {
        const val KEY_MODE = "KEY_MODE"
        const val EXTRA_KEY_PROVIDER = "EXTRA_KEY_PROVIDER"
        const val EXTRA_KEY_IDENTIFIER = "EXTRA_KEY_IDENTIFIER"
    }
}