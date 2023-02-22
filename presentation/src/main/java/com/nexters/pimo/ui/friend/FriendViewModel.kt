package com.nexters.pimo.ui.friend

import com.nexters.pimo.domain.model.User
import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor() : ContainerHost<FriendState, FriendSideEffect>,
    BaseViewModel() {

    override val container = container<FriendState, FriendSideEffect>(FriendState())

    val tempUser = User(
        id = 0,
        profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
        nickname = "yjyoon"
    )

    init {
        intent {
            reduce { state.copy(uiState = UiState.Loading) }

            delay(1000L)
            reduce {
                state.copy(
                    uiState = UiState.Done,
                    user = tempUser,
                    eachOtherCount = 32,
                    onlyMeCount = 48,
                    onlyOtherCount = 109,
                    eachOtherFriends = List(32) { tempUser },
                    selectedType = FriendState.FriendType.EachOther,
                    orderType = FriendState.OrderType.Recently
                )
            }
        }
    }

    fun onChangeType(
        friendType: FriendState.FriendType,
        orderType: FriendState.OrderType
    ) = intent {
        if (friendType == state.selectedType && orderType == state.orderType) return@intent

        reduce {
            state.copy(
                uiState = UiState.Loading,
                selectedType = friendType,
                orderType = orderType
            )
        }

        //TODO: GetFriendListUseCase(friendType, orderType)
        delay(250L)
        when (friendType) {
            FriendState.FriendType.EachOther -> reduce {
                state.copy(
                    uiState = UiState.Done,
                    eachOtherCount = 32,
                    eachOtherFriends = List(32) { tempUser }
                )
            }
            FriendState.FriendType.OnlyMe -> reduce {
                state.copy(
                    uiState = UiState.Done,
                    onlyMeCount = 48,
                    onlyMeFriends = List(48) { tempUser }
                )
            }
            FriendState.FriendType.OnlyOther -> reduce {
                state.copy(
                    uiState = UiState.Done,
                    onlyOtherCount = 109,
                    onlyOtherFriends = List(109) { tempUser }
                )
            }
        }
    }
}
