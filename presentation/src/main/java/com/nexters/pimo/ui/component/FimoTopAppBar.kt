package com.nexters.pimo.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.theme.FimoTheme
import com.nexters.pimo.ui.util.NumberUtil.toSymbolFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FimoHomeAppBar(
    @DrawableRes actionIconRes: Int = R.drawable.ic_setting,
    onActionClick: () -> Unit,
    newPostCount: Int
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                ambientColor = shadowColor,
                spotColor = shadowColor,
                elevation = 12.dp
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo),
                    contentDescription = null,
                    modifier = Modifier.width(110.dp)
                )
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        onClick = onActionClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = actionIconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.friends_post),
                    style = FimoTheme.typography.semibold.copy(fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.weight(1f))
                if (newPostCount > 0) {
                    Text(
                        text = stringResource(
                            id = R.string.new_post_count,
                            newPostCount.toSymbolFormat()
                        ),
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 14.sp,
                            color = FimoTheme.colors.grey7F
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FimoSimpleAppBar(
    @DrawableRes backIconRes: Int,
    backIconSize: Dp = 12.dp,
    titleText: String,
    titleFontSize: TextUnit = 18.sp,
    onBack: () -> Unit,
    action: @Composable (RowScope.() -> Unit)? = null
) {
    val appBarHeight = 72.dp

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(appBarHeight)
            .shadow(
                ambientColor = shadowColor,
                spotColor = shadowColor,
                elevation = 12.dp
            ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = backIconRes),
                        contentDescription = null,
                        modifier = Modifier.size(backIconSize)
                    )
                }
            }
            Text(
                text = titleText,
                style = FimoTheme.typography.medium.copy(fontSize = titleFontSize)
            )
            action?.let { it() } ?: Spacer(modifier = Modifier.width(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FimoFeedTopAppBar(
    user: User,
    @DrawableRes actionIconRes: Int = R.drawable.ic_setting,
    onActionClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = user.archiveName,
                        style = FimoTheme.typography.semibold.copy(fontSize = 24.sp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_share),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        onClick = onActionClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = actionIconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

val shadowColor = Color(0xFF383838).copy(alpha = 0.5f)
