package com.nexters.pimo.ui.friend

import com.nexters.pimo.ui.base.BaseViewModel
import com.nexters.pimo.ui.model.Dummy
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

    val eachOther = listOf(
        Dummy.user2,
        Dummy.user3,
        Dummy.user4,
        Dummy.user5,
        Dummy.user6,
        Dummy.user7,
        Dummy.user8,
        Dummy.user9,
        Dummy.user10,
        Dummy.user11,
        Dummy.user12,
        Dummy.user13,
    )

    val onlyMe = listOf(
        Dummy.user14,
        Dummy.user15,
        Dummy.user16,
        Dummy.user17,
        Dummy.user18,
    )

    val onlyOther = listOf(
        Dummy.user19,
        Dummy.user20,
        Dummy.user21,
        Dummy.user22,
        Dummy.user23,
        Dummy.user24,
    )

    init {
        intent {
            reduce { state.copy(uiState = UiState.Loading) }

            delay(1000L)
            reduce {
                state.copy(
                    uiState = UiState.Done,
                    user = Dummy.user1,
                    eachOtherCount = eachOther.size,
                    onlyMeCount = onlyMe.size,
                    onlyOtherCount = onlyOther.size,
                    eachOtherFriends = eachOther,
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
                    eachOtherCount = eachOther.size,
                    eachOtherFriends = eachOther
                )
            }
            FriendState.FriendType.OnlyMe -> reduce {
                state.copy(
                    uiState = UiState.Done,
                    onlyMeCount = onlyMe.size,
                    onlyMeFriends = onlyMe
                )
            }
            FriendState.FriendType.OnlyOther -> reduce {
                state.copy(
                    uiState = UiState.Done,
                    onlyOtherCount = onlyOther.size,
                    onlyOtherFriends = onlyOther
                )
            }
        }
    }
}
