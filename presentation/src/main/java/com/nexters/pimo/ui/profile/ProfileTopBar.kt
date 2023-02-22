package com.nexters.pimo.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.NoRippleInteractionSource
import com.nexters.pimo.ui.theme.FimoTheme

@Composable
fun AddProfileTopBar(
    pageIdx: Int,
    onBack: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val appBarHeight = 76.dp

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(appBarHeight),
    ) {
        Column(

        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(72.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = onBack,
                            interactionSource = NoRippleInteractionSource,
                            indication = null,
                        ),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    )
                Text(
                    text = stringResource(id = R.string.profile_top_title),
                    style = FimoTheme.typography.medium.copy(
                        fontSize = 18.sp,
                        color = FimoTheme.colors.black,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = (pageIdx+1).toString(),
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.primary,
                    ),
                    textAlign = TextAlign.Right
                )
                Text(
                    text = "/3",
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.primary,
                    ),
                    textAlign = TextAlign.Right
                )
            }
            Row(
                modifier = Modifier.height(4.dp),
            ) {
                Box(
                    modifier = Modifier.background(FimoTheme.colors.primary).width(((screenWidth/3)*(pageIdx+1)).dp).fillMaxHeight()
                ) {
                }
                Box(
                    modifier = Modifier.background(FimoTheme.colors.greyD9).width((screenWidth-((screenWidth/3)*(pageIdx+1))).dp).fillMaxHeight()
                ) {
                }
            }
        }
    }

}
