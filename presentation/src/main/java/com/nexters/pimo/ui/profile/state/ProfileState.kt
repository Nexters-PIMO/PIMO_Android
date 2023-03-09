package com.nexters.pimo.ui.profile.state

import android.graphics.Bitmap
import com.nexters.pimo.domain.model.User

data class ProfileState(
    val pageIdx: Int = 0,
    val nicknameState: NicknameState,
    val archiveNameState: ArchiveNameState,
    val imageState: Bitmap?,
    val mode: Mode,
    val user: User = User.Unspecified,
)

sealed class ProfileSideEffect {
}