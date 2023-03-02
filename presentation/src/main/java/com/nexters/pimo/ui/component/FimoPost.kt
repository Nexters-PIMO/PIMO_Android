package com.nexters.pimo.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.theme.FimoTheme
import com.nexters.pimo.ui.util.DateUtil.toRelatively
import com.nexters.pimo.ui.util.NumberUtil.toSymbolFormat
import kotlinx.coroutines.delay

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalPagerApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun FimoPost(
    modifier: Modifier = Modifier,
    post: Post,
    isAudioPlaying: Boolean,
    showTooltip: Boolean,
    onCloseTooltip: () -> Unit,
    onClickMore: (Post) -> Unit,
    onCopyText: (String) -> Unit,
    onPlayAudio: (String) -> Unit,
    onStopAudio: () -> Unit,
    onClap: () -> Unit,
    onShare: (Post) -> Unit
) {

    val density = LocalDensity.current

    var clapCount: Int by remember { mutableStateOf(post.clapCount) }
    var clapPopupCount: Int by remember { mutableStateOf(0) }
    var isClapped: Boolean by remember { mutableStateOf(post.isClapped) }

    val pagerState = rememberPagerState()

    Box {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircleImage(
                            imageSource = post.writer.profileImageUrl,
                            size = 28.dp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = post.writer.nickname,
                        style = FimoTheme.typography.medium.copy(fontSize = 14.sp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                Row {
                    Text(
                        text = post.postedTime.toRelatively(),
                        style = FimoTheme.typography.regular.copy(
                            fontSize = 14.sp,
                            color = FimoTheme.colors.grey7F
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick = { onClickMore(post) },
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
                    .clip(RoundedCornerShape(4.dp))
            ) {
                HorizontalPager(
                    count = post.textImages.size,
                    state = pagerState,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = FimoTheme.colors.greyEF,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        ) {}
                        AsyncImage(
                            model = post.textImages[it].imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        )
                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = FimoTheme.colors.primary,
                    inactiveColor = FimoTheme.colors.greyD9,
                    indicatorWidth = 4.dp,
                    indicatorHeight = 4.dp,
                    spacing = 4.dp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(12.dp)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .width(184.dp)
                ) {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        FilledIconButton(
                            onClick = {
                                onCopyText(post.textImages[pagerState.currentPage].text)
                            },
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
                    Spacer(modifier = Modifier.width(4.dp))
                    if (showTooltip) {
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
                                    .padding(start = 21.dp, end = 2.dp),
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
                                    IconButton(onClick = onCloseTooltip) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_close_tooltip),
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
            Spacer(modifier = Modifier.height(9.dp))
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
                            clapPopupCount++
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.width(68.dp)
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
                                text = clapCount.toSymbolFormat(),
                                style = FimoTheme.typography.regular.copy(fontSize = 16.sp),
                                maxLines = 1,
                                overflow = TextOverflow.Visible
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(
                        onClick = { onShare(post) },
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
                        if (isAudioPlaying) onStopAudio()
                        else onPlayAudio(post.textImages[pagerState.currentPage].text)
                    },
                    contentPadding = PaddingValues(horizontal = 5.dp, vertical = 3.dp),
                    interactionSource = NoRippleInteractionSource,
                ) {
                    AnimatedContent(
                        targetState = isAudioPlaying,
                        transitionSpec = { fadeIn() with fadeOut() }
                    ) {
                        GifImage(
                            imageRes = if (it) R.drawable.ic_audio_on else R.drawable.ic_audio_off,
                            modifier = Modifier.width(80.dp)
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = clapPopupCount > 0,
            enter = slideInVertically {
                with(density) { 40.dp.roundToPx() }
            } + fadeIn(),
            exit = slideOutVertically {
                with(density) { 40.dp.roundToPx() }
            } + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 54.dp),
        ) {
            Surface(
                shape = CircleShape,
                color = FimoTheme.colors.white,
                modifier = Modifier.size(40.dp),
                shadowElevation = 1.dp,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (clapPopupCount == 0) "" else "+${clapPopupCount.toSymbolFormat()}",
                        style = FimoTheme.typography.medium.copy(fontSize = 18.sp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    LaunchedEffect(clapPopupCount) {
        delay(1500L)
        clapPopupCount = 0
    }
}
