package com.nexters.pimo.ui.friend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.CircleImage
import com.nexters.pimo.ui.component.CustomModifier.clickableWithoutRipple
import com.nexters.pimo.ui.component.FimoDialog
import com.nexters.pimo.ui.component.FimoDivider
import com.nexters.pimo.ui.component.FimoSimpleAppBar
import com.nexters.pimo.ui.component.FimoVertDivider
import com.nexters.pimo.ui.state.UiState
import com.nexters.pimo.ui.theme.FimoTheme
import com.nexters.pimo.ui.util.NumberUtil.toSymbolFormat
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendScreen(
    viewModel: FriendViewModel,
    onBack: () -> Unit,
    onAction: () -> Unit
) {
    val state by viewModel.collectAsState()

    var dialogState by remember { mutableStateOf(DialogState(visible = false)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = FimoTheme.colors.white)
    ) {
        FimoSimpleAppBar(
            backIconRes = R.drawable.ic_back,
            backIconSize = 16.dp,
            titleText = state.user.nickname,
            titleFontSize = 16.sp,
            onBack = onBack,
            action = {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        onClick = onAction,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_setting),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 24.dp)
        ) {
            FriendBar(
                selectedType = state.selectedType,
                eachOtherCount = state.eachOtherCount,
                onlyMeCount = state.onlyMeCount,
                onlyOtherCount = state.onlyOtherCount,
                onChangeType = {
                    viewModel.onChangeType(
                        friendType = it,
                        orderType = FriendState.OrderType.Recently
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OrderBar(
                friendCount = when (state.selectedType) {
                    FriendState.FriendType.EachOther -> state.eachOtherCount
                    FriendState.FriendType.OnlyMe -> state.onlyMeCount
                    FriendState.FriendType.OnlyOther -> state.onlyOtherCount
                },
                selectedOrder = state.orderType,
                onChangeOrder = {
                    viewModel.onChangeType(
                        friendType = state.selectedType,
                        orderType = it
                    )
                }
            )
            FimoDivider()
            when (state.uiState) {
                UiState.Done -> {
                    FriendList(
                        friends = when (state.selectedType) {
                            FriendState.FriendType.EachOther -> state.eachOtherFriends
                            FriendState.FriendType.OnlyMe -> state.onlyMeFriends
                            FriendState.FriendType.OnlyOther -> state.onlyOtherFriends
                        },
                        selectedType = state.selectedType,
                        onAction = {
                            dialogState = DialogState(user = it, visible = true)
                        }
                    )
                }
                UiState.Loading -> {
                    Loading()
                }
                is UiState.Failure -> {
                    // oops
                }
            }
        }
    }
    FimoDialog(
        visible = dialogState.visible,
        contentPadding = PaddingValues(top = 24.dp, bottom = 20.dp),
        header = {
            CircleImage(
                imageSource = dialogState.user.profileImageUrl,
                size = 56.dp
            )
        },
        title = dialogState.user.archiveName,
        titleSize = 20.sp,
        subtitle = dialogState.user.nickname,
        leftStringRes = R.string.cancel,
        rightStringRes = when (state.selectedType) {
            FriendState.FriendType.OnlyOther -> R.string.follow
            else -> R.string.unfollow
        },
        onLeftClick = { dialogState = DialogState(visible = false) },
        onRightClick = {
            when (state.selectedType) {
                FriendState.FriendType.OnlyOther -> {}
                else -> {}
            }
        },
        onDismiss = { dialogState = DialogState(visible = false) }
    )
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun FriendList(
    friends: List<User>,
    selectedType: FriendState.FriendType,
    onAction: (User) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 25.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        items(friends) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircleImage(
                            imageSource = it.profileImageUrl,
                            size = 40.dp
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column {
                        Text(
                            text = it.nickname,
                            style = FimoTheme.typography.semibold.copy(
                                fontSize = 16.sp,
                                color = FimoTheme.colors.black
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = it.archiveName,
                            style = FimoTheme.typography.regular.copy(
                                fontSize = 14.sp,
                                color = FimoTheme.colors.grey7F
                            )
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.post_count,
                            it.posts.size.toSymbolFormat()
                        ),
                        style = FimoTheme.typography.regular.copy(
                            fontSize = 12.sp,
                            color = FimoTheme.colors.grey7F
                        )
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Image(
                        painter = painterResource(id = selectedType.actionIconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .width(28.dp)
                            .clickableWithoutRipple { onAction(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun OrderBar(
    friendCount: Int,
    selectedOrder: FriendState.OrderType,
    onChangeOrder: (FriendState.OrderType) -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append(context.getString(R.string.friend))
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(
                        " ${
                            context.getString(
                                R.string.friend_count,
                                friendCount.toSymbolFormat()
                            )
                        }"
                    )
                }
            },
            fontSize = 14.sp,
            color = FimoTheme.colors.black
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            FriendState.OrderType.values().forEach {
                Text(
                    text = stringResource(id = it.stringRes),
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 12.sp,
                        color = if (selectedOrder == it) {
                            FimoTheme.colors.primary
                        } else {
                            FimoTheme.colors.grey7F
                        }
                    ),
                    modifier = Modifier.clickableWithoutRipple { onChangeOrder(it) }
                )
                if (it.ordinal != FriendState.OrderType.values().size - 1) {
                    FimoVertDivider(
                        modifier = Modifier
                            .height(12.dp)
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FriendBar(
    selectedType: FriendState.FriendType,
    eachOtherCount: Int,
    onlyMeCount: Int,
    onlyOtherCount: Int,
    onChangeType: (FriendState.FriendType) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        color = FimoTheme.colors.greyF7
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FriendState.FriendType.values().forEach {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickableWithoutRipple { onChangeType(it) }
                ) {
                    Spacer(modifier = Modifier.height((1.5).dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = when (it) {
                                FriendState.FriendType.EachOther -> eachOtherCount
                                FriendState.FriendType.OnlyMe -> onlyMeCount
                                FriendState.FriendType.OnlyOther -> onlyOtherCount
                            }.toSymbolFormat(),
                            style = FimoTheme.typography.medium.copy(
                                fontSize = 18.sp,
                                color = FimoTheme.colors.black
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = stringResource(id = it.stringRes),
                            style = FimoTheme.typography.medium.copy(
                                fontSize = 12.sp,
                                color = if (selectedType == it) {
                                    FimoTheme.colors.black
                                } else {
                                    FimoTheme.colors.grey7F
                                }
                            )
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((1.5).dp),
                        color = if (selectedType == it) {
                            FimoTheme.colors.grey7F
                        } else {
                            FimoTheme.colors.greyF7
                        }
                    ) {}
                }
            }
        }
    }
}
