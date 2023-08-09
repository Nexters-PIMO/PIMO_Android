package com.nexters.pimo.ui.profile.state

import android.graphics.Bitmap
import com.nexters.pimo.domain.model.LoginResult

data class ProfileState(
    val pageIdx: Int = 0,
    val identifier: String,
    val provider: String,
    val nicknameState: NicknameState,
    val archiveNameState: ArchiveNameState,
    val imageState: Bitmap?,
    val mode: Mode,
    val loginResult: LoginResult
)

sealed class ProfileSideEffect {
}