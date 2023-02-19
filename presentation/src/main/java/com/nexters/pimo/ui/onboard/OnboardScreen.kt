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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
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
                count = 3,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(),
            ) { page ->
                when (page) {
                    0 -> Onboard1(onClick = onSkip)
                    1 -> Onboard2(onClick = onSkip)
                    2 -> Onboard3(onClick = onSkip)
                }
            }
        }
    )
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .map { it == 2 }
            .collect { lastPage = it }
    }
}

@Composable
fun Onboard1(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.bg_login),
                contentScale = ContentScale.FillHeight
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(horizontal = 24.dp)
                .padding(top = 60.dp),
        ) {
            Text(
                modifier = Modifier.clickable { onClick() },
                text = stringResource(id = R.string.onboard_skip),
                style = FimoTheme.typography.medium.copy(
                    fontSize = 18.sp,
                    color = FimoTheme.colors.primaryDark
                )
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 40.dp)
                .padding(bottom = 60.dp),
        ) {
            Image(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                painter = painterResource(id = R.drawable.img_logo_icon),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.onboard_title_1),
                style = FimoTheme.typography.bold.copy(
                    fontSize = 26.sp,
                    color = FimoTheme.colors.white
                )
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.onboard_content_1),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 16.sp,
                    color = FimoTheme.colors.white
                ),
                lineHeight = 25.sp
            )
            Spacer(modifier = Modifier.height(80.dp))
            Row() {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(FimoTheme.colors.black)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(FimoTheme.colors.white)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(FimoTheme.colors.white)
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
            .background(FimoTheme.colors.white)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 60.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    modifier = Modifier.clickable { onClick() },
                    text = stringResource(id = R.string.onboard_skip),
                    style = FimoTheme.typography.medium.copy(
                        fontSize = 18.sp,
                        color = FimoTheme.colors.primaryDark
                    ),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(187.dp)
                        .height(381.dp),
                    painter = painterResource(id = R.drawable.img_onboard_2),
                    contentDescription = null,

                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.onboard_title_2_1))
                        withStyle(style = SpanStyle(color = FimoTheme.colors.primary)) {
                            append(stringResource(id = R.string.onboard_title_2_2))
                        }
                        append(stringResource(id = R.string.onboard_title_2_3))
                    },
                    style = FimoTheme.typography.bold.copy(
                        fontSize = 26.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 36.sp,
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = stringResource(id = R.string.onboard_content_2),
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 25.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 40.dp)
                .padding(bottom = 60.dp)
        ) {
            Row() {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(FimoTheme.colors.greyD0)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(FimoTheme.colors.black)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(FimoTheme.colors.greyD0)
                )
            }
        }

    }
}

@Composable
fun Onboard3(onClick: () -> Unit) {
    val interactionSource = MutableInteractionSource()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 100.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(187.dp)
                        .height(381.dp),
                    painter = painterResource(id = R.drawable.img_onboard_2),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.onboard_title_3_1))
                        withStyle(style = SpanStyle(color = FimoTheme.colors.primary)) {
                            append(stringResource(id = R.string.onboard_title_3_2))
                        }
                        append(stringResource(id = R.string.onboard_title_3_3))
                        withStyle(style = SpanStyle(color = FimoTheme.colors.primary)) {
                            append(stringResource(id = R.string.onboard_title_3_4))
                        }
                        append(stringResource(id = R.string.onboard_title_3_5))
                    },
                    style = FimoTheme.typography.bold.copy(
                        fontSize = 26.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 36.sp,
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = stringResource(id = R.string.onboard_content_3),
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 25.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 40.dp)
                .padding(bottom = 60.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(5.dp),
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                    ) {
                        onClick()
                    }
                    .height(54.dp),
                border = BorderStroke(1.dp, FimoTheme.colors.greyD9),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = null,
                            tint = FimoTheme.colors.primary,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(id = R.string.onboard_create_archive),
                            style = FimoTheme.typography.semibold.copy(
                                fontSize = 16.sp,
                                color = FimoTheme.colors.black
                            ),
                        )
                    }
            }
        }
    }
}