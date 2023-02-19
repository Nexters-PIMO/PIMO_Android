package com.nexters.pimo.ui.component

import androidx.annotation.StringRes
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.ui.theme.FimoTheme

@Composable
fun FimoDialog(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(top = 42.dp, bottom = 34.dp),
    contentSpacing: Dp = 8.dp,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    @StringRes titleRes: Int,
    @StringRes subtitleRes: Int,
    @StringRes leftStringRes: Int,
    @StringRes rightStringRes: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Surface(
        modifier = modifier.then(Modifier.fillMaxSize()),
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
                        TextButton(
                            onClick = onLeftClick,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentPadding = PaddingValues(0.dp),
                            interactionSource = NoRippleInteractionSource
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
                        TextButton(
                            onClick = onRightClick,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentPadding = PaddingValues(0.dp),
                            interactionSource = NoRippleInteractionSource
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
