package com.nexters.pimo.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.theme.FimoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FimoPostView(
    modifier: Modifier = Modifier,
    post: Post,
    isAudioPlaying: Boolean,
    showTooltip: Boolean,
    onCloseTooltip: () -> Unit,
    onPlayAudio: (String) -> Unit,
    onStopAudio: () -> Unit,
    onCopyText: (String) -> Unit,
    onClickMore: (Post) -> Unit,
    onBack: () -> Unit
) {
    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .shadow(
                    ambientColor = shadowColor,
                    spotColor = shadowColor,
                    elevation = 12.dp
                ),
        ) { }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 22.dp)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollableState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.size(24.dp),
                        interactionSource = NoRippleInteractionSource
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            modifier = Modifier.height(14.dp)
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.text_image),
                    style = FimoTheme.typography.medium.copy(fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.width(24.dp))
            }
            FimoDivider(modifier = Modifier.padding(top = 16.dp, bottom = 20.dp))
            FimoPost(
                post = post,
                isAudioPlaying = isAudioPlaying,
                showTooltip = showTooltip,
                onCloseTooltip = onCloseTooltip,
                onClickMore = onClickMore,
                onCopyText = onCopyText,
                onPlayAudio = onPlayAudio,
                onStopAudio = onStopAudio,
                onClap = { /*TODO*/ },
                onShare = { }
            )
            Spacer(modifier = Modifier.height(bottomPanelHeight / 2))
        }
    }
}
