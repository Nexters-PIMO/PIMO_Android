package com.nexters.pimo.ui.component

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.ui.theme.FimoTheme

@Composable
fun FimoDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    contentPadding: PaddingValues = PaddingValues(top = 42.dp, bottom = 34.dp),
    contentSpacing: Dp = 8.dp,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    @StringRes titleRes: Int,
    @StringRes subtitleRes: Int,
    @StringRes leftStringRes: Int,
    @StringRes rightStringRes: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Surface(
            modifier = modifier.then(
                Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = onDismiss
                    )
            ),
            color = FimoTheme.colors.black.copy(alpha = 0.5f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(2.dp),
                    color = FimoTheme.colors.white,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.67f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(
                            modifier = Modifier.padding(contentPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(contentSpacing)
                        ) {
                            if (header != null) header()
                            Text(
                                text = stringResource(id = titleRes),
                                style = FimoTheme.typography.semibold.copy(
                                    fontSize = 18.sp,
                                    color = FimoTheme.colors.black
                                )
                            )
                            Text(
                                text = stringResource(id = subtitleRes),
                                style = FimoTheme.typography.regular.copy(
                                    fontSize = 16.sp,
                                    color = FimoTheme.colors.black
                                )
                            )
                        }
                        FimoDivider()
                        Row(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Button(
                                onClick = onLeftClick,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = FimoTheme.colors.white
                                ),
                                shape = RectangleShape
                            ) {
                                Text(
                                    text = stringResource(id = leftStringRes),
                                    style = FimoTheme.typography.medium.copy(
                                        fontSize = 16.sp,
                                        color = FimoTheme.colors.grey7F
                                    )
                                )
                            }
                            FimoVertDivider(modifier = Modifier.fillMaxHeight())
                            Button(
                                onClick = onRightClick,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = FimoTheme.colors.white
                                ),
                                shape = RectangleShape
                            ) {
                                Text(
                                    text = stringResource(id = rightStringRes),
                                    style = FimoTheme.typography.medium.copy(
                                        fontSize = 16.sp,
                                        color = FimoTheme.colors.primary
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
