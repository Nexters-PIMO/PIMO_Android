package com.nexters.pimo.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.CustomModifier.bottomElevation
import com.nexters.pimo.ui.component.FimoFeedTopAppBar
import com.nexters.pimo.ui.component.FimoPostGrid
import com.nexters.pimo.ui.component.FimoPostList
import com.nexters.pimo.ui.component.FimoPostView
import com.nexters.pimo.ui.component.bottomPanelHeight
import com.nexters.pimo.ui.component.shadowColor
import com.nexters.pimo.ui.state.UiState
import com.nexters.pimo.ui.theme.FimoTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun FeedScreen(viewModel: FeedViewModel = hiltViewModel()) {
    val state = viewModel.collectAsState().value

    var viewMode: FeedViewMode by remember { mutableStateOf(FeedViewMode.List) }
    var selectedPost: Post? by remember { mutableStateOf(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        FimoFeedTopAppBar(
            user = state.user,
            onActionClick = {}
        )
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
                                showTooltip = state.showTooltip,
                                onCloseTooltip = viewModel::onCloseTooltip
                            )
                        }
                        FeedViewMode.Grid -> {
                            selectedPost?.let {
                                FimoPostView(
                                    post = it,
                                    showTooltip = state.showTooltip,
                                    onCloseTooltip = viewModel::onCloseTooltip,
                                    onBack = { selectedPost = null }
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
fun FeedBar(
    postCount: Int,
    currentViewMode: FeedViewMode,
    onChangeViewMode: (FeedViewMode) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .bottomElevation()
            .shadow(
                ambientColor = shadowColor,
                spotColor = shadowColor,
                elevation = 12.dp
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
