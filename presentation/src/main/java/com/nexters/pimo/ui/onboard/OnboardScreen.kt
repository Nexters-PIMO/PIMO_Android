package com.nexters.pimo.ui.onboard

import androidx.compose.foundation.*
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.FimoSimpleAppBar
import com.nexters.pimo.ui.component.NoRippleInteractionSource
import com.nexters.pimo.ui.component.shadowColor
import com.nexters.pimo.ui.theme.FimoTheme
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(
    viewModel: OnboardViewModel = hiltViewModel(),
    onSkip: () -> Unit,
    onBack: () -> Unit,
) {

    val state = viewModel.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        if (!state.isLogin) {
            FimoSimpleAppBar(
                backIconRes = R.drawable.ic_back,
                onBack = onBack,
                titleText = stringResource(id = R.string.setting_guide)
            )
            Box(
                modifier = Modifier.height(1.dp).fillMaxWidth()
                    .shadow(
                        ambientColor = shadowColor,
                        spotColor = shadowColor,
                        elevation = 12.dp
                    )
            ) {
            }
        }
        HorizontalPager(
            count = 4,
            modifier = Modifier
                .fillMaxWidth(),
        ) { page ->
            when (page) {
                0 -> OnboardFirst(state, onSkip)
                1 -> OnboardFollowing(state, OnboardStep(page, R.string.onboard_title_2_1,R.string.onboard_title_2_2,  R.string.onboard_content_2), onSkip)
                2 -> OnboardFollowing(state, OnboardStep(page, R.string.onboard_title_3_1,R.string.onboard_title_3_2,  R.string.onboard_content_3), onSkip)
                3 -> OnboardFollowing(state, OnboardStep(page, R.string.onboard_title_4_1,R.string.onboard_title_4_2,  R.string.onboard_content_4), onSkip)
            }
        }
    }

}

@Composable
fun OnboardFollowing(
    state: OnboardState,
    onboardStep: OnboardStep,
    onSkip: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(FimoTheme.colors.white),
            ) {
                Image(
                    modifier = Modifier.width(300.dp).align(Alignment.BottomCenter),
                    painter =
                    when (onboardStep.step) {
                        1 -> painterResource(id = R.drawable.img_onboard_2)
                        2 -> painterResource(id = R.drawable.img_onboard_3)
                        3 -> painterResource(id = R.drawable.img_onboard_4)
                        else -> painterResource(id = R.drawable.img_onboard_2)
                    },
                    contentDescription = null,
                )
                if (onboardStep.step != 3 && state.isLogin) {
                    Text(
                        modifier = Modifier
                            .clickable { onSkip() }
                            .align(Alignment.TopEnd)
                            .padding(horizontal = 20.dp)
                            .padding(top = 30.dp),
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
                    text = stringResource(id = onboardStep.titleRes),
                    style = FimoTheme.typography.bold.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.primary
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = onboardStep.subtitleRes),
                    style = FimoTheme.typography.semibold.copy(
                        fontSize = 22.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 32.sp
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = stringResource(id = onboardStep.contentRes),
                    style = FimoTheme.typography.light.copy(
                        fontSize = 15.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 23.sp
                )
            }
            if (onboardStep.step == 3 && state.isLogin) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 20.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = FimoTheme.colors.black,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .clickable(
                                interactionSource = NoRippleInteractionSource,
                                indication = null,
                            ) {
                                onSkip()
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
        if (onboardStep.step != 3) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 60.dp),
            ) {
                Row(
                ) {
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(FimoTheme.colors.greyD9)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if (onboardStep.step == 1) {
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
                    if (onboardStep.step == 2) {
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
                    }
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
}


@Composable
fun OnboardFirst(
    state: OnboardState,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.img_onboard_1), contentScale = ContentScale.FillBounds)
    ) {
        if (state.isLogin) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp),
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
                        color = FimoTheme.colors.white
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
                        painter = painterResource(id = R.drawable.img_logo_text_white),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.onboard_content_1),
                style = FimoTheme.typography.light.copy(
                    fontSize = 15.sp,
                    color = FimoTheme.colors.white
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
