package com.nexters.pimo.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.CircleImage
import com.nexters.pimo.ui.component.CustomModifier.bottomElevation
import com.nexters.pimo.ui.component.FimoFeedTopAppBar
import com.nexters.pimo.ui.component.FimoPostGrid
import com.nexters.pimo.ui.component.FimoPostList
import com.nexters.pimo.ui.component.FimoPostView
import com.nexters.pimo.ui.component.NoRippleInteractionSource
import com.nexters.pimo.ui.component.bottomPanelHeight
import com.nexters.pimo.ui.component.shadowColor
import com.nexters.pimo.ui.state.UiState
import com.nexters.pimo.ui.theme.FimoTheme
import com.nexters.pimo.ui.util.NumberUtil.toSymbolFormat
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel(),
    startFriendActivity: () -> Unit,
    onClickMore: (Post) -> Unit
) {
    val state = viewModel.collectAsState().value
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val clipboardManager = LocalClipboardManager.current

    var viewMode: FeedViewMode by remember { mutableStateOf(FeedViewMode.List) }
    var selectedPost: Post? by remember { mutableStateOf(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        FimoFeedTopAppBar(
            user = state.user,
            onActionClick = {}
        )
        Scaffold(
            topBar = {
                ProfileBar(
                    user = state.user,
                    onFriendClick = startFriendActivity,
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                if (selectedPost == null) {
                    FeedBar(
                        postCount = state.user.posts.size,
                        currentViewMode = viewMode,
                        onChangeViewMode = { viewMode = it }
                    )
                }
                when (state.uiState) {
                    UiState.Done -> {
                        if (state.user.posts.isNotEmpty()) {
                            when (viewMode) {
                                FeedViewMode.List -> {
                                    FimoPostList(
                                        posts = state.user.posts,
                                        isAudioPlaying = state.isAudioPlaying,
                                        showTooltip = state.showTooltip,
                                        onCloseTooltip = viewModel::onCloseTooltip,
                                        onPlayAudio = viewModel::onPlayAudio,
                                        onStopAudio = viewModel::onStopAudio
                                        onCopyText = { clipboardManager.setText(AnnotatedString(it)) }
                                        onClickMore = onClickMore
                                    )
                                }
                                FeedViewMode.Grid -> {
                                    selectedPost?.let { post ->
                                        FimoPostView(
                                            post = post,
                                            isAudioPlaying = state.isAudioPlaying,
                                            showTooltip = state.showTooltip,
                                            onCloseTooltip = viewModel::onCloseTooltip,
                                            onPlayAudio = viewModel::onPlayAudio,
                                            onStopAudio = viewModel::onStopAudio,
                                            onBack = { selectedPost = null },
                                            onCopyText = {
                                                clipboardManager.setText(
                                                    AnnotatedString(it)
                                                )
                                            }
                                        )
                                    } ?: FimoPostGrid(
                                        posts = state.user.posts,
                                        onPostClick = { selectedPost = it }
                                    )
                                }
                            }
                        } else {
                            Empty()
                        }
                    }
                    UiState.Loading -> {
                        Loading()
                    }
                    is UiState.Failure -> {
                        /* TODO */
                    }
                }
            }
        }
    }
}

@Composable
fun Empty() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPanelHeight),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_grey),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.empty_text_image),
            style = FimoTheme.typography.medium.copy(fontSize = 19.sp)
        )
        Spacer(modifier = Modifier.height(23.dp))
        Text(
            text = stringResource(id = R.string.empty_text_image_help),
            style = FimoTheme.typography.light.copy(
                fontSize = 14.sp,
                color = FimoTheme.colors.grey7F
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPanelHeight),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBar(
    user: User,
    onFriendClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.padding(start = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircleImage(
                            imageSource = user.profileImageUrl,
                            size = 52.dp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = user.nickname,
                            style = FimoTheme.typography.medium.copy(fontSize = 16.sp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = stringResource(
                                id = R.string.archiving_count,
                                user.posts.size.toSymbolFormat()
                            ),
                            style = FimoTheme.typography.medium.copy(
                                fontSize = 14.sp,
                                color = FimoTheme.colors.grey7F
                            )
                        )
                    }
                }
                Button(
                    onClick = onFriendClick,
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FimoTheme.colors.greyE9,
                        contentColor = FimoTheme.colors.black
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 12.dp
                    ),
                    interactionSource = NoRippleInteractionSource,
                    modifier = Modifier.size(width = 64.dp, height = 40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_friend),
                        contentDescription = null,
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            scrolledContainerColor = FimoTheme.colors.white
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedBar(
    modifier: Modifier = Modifier,
    postCount: Int,
    currentViewMode: FeedViewMode,
    onChangeViewMode: (FeedViewMode) -> Unit
) {
    Surface(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .bottomElevation()
                .shadow(
                    ambientColor = shadowColor,
                    spotColor = shadowColor,
                    elevation = 12.dp
                )
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_small),
                    contentDescription = null,
                    modifier = Modifier.width(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.text_image) + " $postCount",
                    style = FimoTheme.typography.medium.copy(fontSize = 16.sp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FeedViewMode.values().forEach {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick = { onChangeViewMode(it) },
                            modifier = Modifier.size(20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = it.iconRes),
                                contentDescription = null,
                                tint = if (currentViewMode == it) {
                                    FimoTheme.colors.primary
                                } else {
                                    FimoTheme.colors.greyD9
                                },
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
