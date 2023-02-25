package com.nexters.pimo.ui.profile.state

import android.graphics.Bitmap

data class ProfileState(
    val pageIdx: Int = 0,
    val nicknameState: NicknameState,
    val archiveNameState: ArchiveNameState,
    val imageState: Bitmap?
)

sealed class ProfileSideEffect {
}