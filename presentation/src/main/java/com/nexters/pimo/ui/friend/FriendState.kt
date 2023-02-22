package com.nexters.pimo.ui.friend

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.state.UiState

data class FriendState(
    val user: User = User.Unspecified,
    val eachOtherCount: Int = 0,
    val onlyMeCount: Int = 0,
    val onlyOtherCount: Int = 0,
    val eachOtherFriends: List<User> = emptyList(),
    val onlyMeFriends: List<User> = emptyList(),
    val onlyOtherFriends: List<User> = emptyList(),
    val selectedType: FriendType = FriendType.EachOther,
    val orderType: OrderType = OrderType.Recently,
    val uiState: UiState = UiState.Loading
) {

    enum class FriendType(
        @StringRes val stringRes: Int,
        @DrawableRes val actionIconRes: Int
    ) {
        EachOther(
            stringRes = R.string.friend_each_other,
            actionIconRes = R.drawable.ic_friend_each_other
        ),
        OnlyMe(
            stringRes = R.string.friend_only_me,
            actionIconRes = R.drawable.ic_friend_only_me
        ),
        OnlyOther(
            stringRes = R.string.friend_only_other,
            actionIconRes = R.drawable.ic_friend_only_other
        )
    }

    enum class OrderType(
        @StringRes val stringRes: Int
    ) {
        Recently(R.string.order_recently),
        Alphabet(R.string.order_alphabet)
    }
}

sealed class FriendSideEffect {
}

data class DialogState(
    val user: User = User.Unspecified,
    val visible: Boolean
)
