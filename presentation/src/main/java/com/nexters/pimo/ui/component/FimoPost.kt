package com.nexters.pimo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.theme.FimoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FimoPost(
    modifier: Modifier = Modifier,
    post: Post,
    showOcrHelp: Boolean,
    onCloseOcrHelp: () -> Unit,
    onMoreClick: () -> Unit,
    onCopyText: () -> Unit,
    onPlayAudio: (onStopCallback: () -> Unit) -> Unit,
    onStopAudio: () -> Unit,
    onClap: () -> Unit,
    onShare: () -> Unit
) {

    var clapCount: Int by remember { mutableStateOf(post.clapCount) }
    var isClapped: Boolean by remember { mutableStateOf(post.isClapped) }
    var isAudioPlaying: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = CircleShape,
                        color = FimoTheme.colors.grey7F,
                        modifier = Modifier.size(28.dp)
                    ) {}
                    AsyncImage(
                        model = post.writer.profileImageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = post.writer.nickname,
                    style = FimoTheme.typography.medium.copy(fontSize = 14.sp)
                )
            }
            Row {
                Text(
                    text = post.postedTime.toString().substring(0, 10),
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 14.sp,
                        color = FimoTheme.colors.grey7F
                    )
                )
                Spacer(modifier = Modifier.width(18.dp))
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        onClick = onMoreClick,
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = FimoTheme.colors.greyEF,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {

            }
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    FilledIconButton(
                        onClick = onCopyText,
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = FimoTheme.colors.secondary,
                            contentColor = FimoTheme.colors.white
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_copy),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = FimoTheme.colors.white
                        )
                    }
                }
                Spacer(modifier = Modifier.width(0.dp))
                if (showOcrHelp) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_ocr_tooltip),
                            contentDescription = null,
                            modifier = Modifier
                                .width(140.dp)
                                .align(Alignment.Center)
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(start = 32.dp, end = 9.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.copy_text),
                                style = FimoTheme.typography.medium.copy(
                                    fontSize = 14.sp,
                                    color = FimoTheme.colors.white
                                )
                            )
                            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                                IconButton(onClick = onCloseOcrHelp) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_close),
                                        contentDescription = null,
                                        tint = FimoTheme.colors.white,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = {
                        onClap()
                        clapCount++
                        isClapped = true
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FimoTheme.colors.greyF7,
                        contentColor = FimoTheme.colors.black
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier.width(78.dp)
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isClapped) R.drawable.ic_clapped else R.drawable.ic_clap
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(width = 24.dp, height = 26.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = clapCount.toString(),
                        style = FimoTheme.typography.regular.copy(fontSize = 16.sp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = onShare,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FimoTheme.colors.greyF7,
                        contentColor = FimoTheme.colors.black
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier.width(44.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            TextButton(
                onClick = {
                    if (isAudioPlaying) {
                        onStopAudio()
                        isAudioPlaying = false
                    } else {
                        onPlayAudio { isAudioPlaying = false }
                        isAudioPlaying = true
                    }
                },
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
            ) {
                Image(
                    painter = painterResource(
                        id = if (isAudioPlaying) R.drawable.ic_audio_on else R.drawable.ic_audio_off
                    ),
                    contentDescription = null,
                    modifier = Modifier.width(80.dp)
                )
            }
        }
    }
}