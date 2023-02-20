package com.nexters.pimo.ui.component

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.ui.theme.FimoTheme
import kotlinx.coroutines.delay

@Composable
fun FimoToast(
    modifier: Modifier = Modifier,
    visible: Boolean,
    @StringRes titleRes: Int,
    @StringRes subtitleRes: Int,
    duration: FimoToastDuration = FimoToastDuration.Normal,
    onDismiss: () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier.then(Modifier.fillMaxSize()),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(2.dp),
                color = FimoTheme.colors.black.copy(alpha = 0.8f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = titleRes),
                        style = FimoTheme.typography.semibold.copy(
                            fontSize = 16.sp,
                            color = FimoTheme.colors.white
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = subtitleRes),
                        style = FimoTheme.typography.light.copy(
                            fontSize = 14.sp,
                            color = FimoTheme.colors.white
                        )
                    )
                }
            }
        }
    }
    LaunchedEffect(visible) {
        if (visible) {
            delay(duration.timeMillis)
            onDismiss()
        }
    }
}

enum class FimoToastDuration(val timeMillis: kotlin.Long) {
    Short(1500L),
    Normal(2000L),
    Long(2500L)
}
