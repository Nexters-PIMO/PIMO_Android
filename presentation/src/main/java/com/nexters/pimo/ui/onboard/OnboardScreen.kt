package com.nexters.pimo.ui.onboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.theme.FimoTheme
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(
    onSkip: () -> Unit,
) {
    val pagerState = rememberPagerState()
    var lastPage by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut(),
        content = {
            HorizontalPager(
                count = 4,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(),
            ) { page ->
                when (page) {
                    0 -> Onboard1(onClick = onSkip)
                    1 -> Onboard2(onClick = onSkip)
                    2 -> Onboard3(onClick = onSkip)
                    3 -> Onboard4(onClick = onSkip)
                }
            }
        }
    )
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .map { it == 3 }
            .collect { lastPage = it }
    }
}

@Composable
fun Onboard1(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(horizontal = 20.dp)
                .padding(top = 64.dp),
        ) {
            Text(
                modifier = Modifier.clickable { onClick() },
                text = stringResource(id = R.string.onboard_skip),
                style = FimoTheme.typography.medium.copy(
                    fontSize = 18.sp,
                    color = FimoTheme.colors.primary
                )
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 20.dp)
                .padding(bottom = 60.dp),
        ) {
            Image(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(19.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(id = R.string.onboard_title_1),
                    style = FimoTheme.typography.bold.copy(
                        fontSize = 25.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 34.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .width(72.dp)
                            .height(20.dp),
                        painter = painterResource(id = R.drawable.img_fimo),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.onboard_content_1),
                style = FimoTheme.typography.light.copy(
                    fontSize = 15.sp,
                    color = FimoTheme.colors.black
                ),
                lineHeight = 23.sp
            )
            Spacer(modifier = Modifier.height(69.dp))
            Row() {
                Box(
                    modifier = Modifier
                        .width(18.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.primary)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
            }
        }
    }
}

@Composable
fun Onboard2(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white),
    ) {
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(422.dp)
                    .background(FimoTheme.colors.greyEF),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 64.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Text(
                        modifier = Modifier.clickable { onClick() },
                        text = stringResource(id = R.string.onboard_skip),
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 18.sp,
                            color = FimoTheme.colors.primary
                        )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 39.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.onboard_title_2_1),
                    style = FimoTheme.typography.bold.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.primary
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.onboard_title_2_2),
                    style = FimoTheme.typography.semibold.copy(
                        fontSize = 22.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 32.sp
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = stringResource(id = R.string.onboard_content_2),
                    style = FimoTheme.typography.light.copy(
                        fontSize = 15.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 23.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 20.dp)
                .padding(bottom = 60.dp),
        ) {
            Row() {
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(18.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.primary)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
            }
        }
    }
}

@Composable
fun Onboard3(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white),
    ) {
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(422.dp)
                    .background(FimoTheme.colors.greyEF),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 64.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Text(
                        modifier = Modifier.clickable { onClick() },
                        text = stringResource(id = R.string.onboard_skip),
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 18.sp,
                            color = FimoTheme.colors.primary
                        )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 39.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.onboard_title_3_1),
                    style = FimoTheme.typography.bold.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.primary
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.onboard_title_3_2),
                    style = FimoTheme.typography.semibold.copy(
                        fontSize = 22.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 32.sp
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = stringResource(id = R.string.onboard_content_3),
                    style = FimoTheme.typography.light.copy(
                        fontSize = 15.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 23.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 20.dp)
                .padding(bottom = 60.dp),
        ) {
            Row() {
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(18.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.primary)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(FimoTheme.colors.greyD9)
                )
            }
        }

    }
}

@Composable
fun Onboard4(onClick: () -> Unit) {
    val interactionSource = MutableInteractionSource()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(422.dp)
                    .background(FimoTheme.colors.greyEF),
            ) {
            }
            Column(
                modifier = Modifier
                    .padding(top = 39.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.onboard_title_4_1),
                    style = FimoTheme.typography.bold.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.primary
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.onboard_title_4_2),
                    style = FimoTheme.typography.semibold.copy(
                        fontSize = 22.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 32.sp
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = stringResource(id = R.string.onboard_content_4),
                    style = FimoTheme.typography.light.copy(
                        fontSize = 15.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 23.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 20.dp)
                .padding(bottom = 60.dp),
        ) {
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = FimoTheme.colors.black,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                    ) {
                        onClick()
                    }
                    .height(56.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo_white),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = FimoTheme.colors.white
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        text = stringResource(id = R.string.onboard_start_fimo),
                        style = FimoTheme.typography.semibold.copy(
                            fontSize = 16.sp,
                            color = FimoTheme.colors.white
                        ),
                    )
                }
            }
        }
    }
}
