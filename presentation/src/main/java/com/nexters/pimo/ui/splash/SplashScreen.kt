package com.nexters.pimo.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.theme.FimoTheme

@Composable
fun SplashScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    Box(
        modifier = Modifier.fillMaxSize().background(FimoTheme.colors.primary).padding(top = (screenHeight / 7 * 3).dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_white),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = FimoTheme.colors.white
            )
            Spacer(modifier = Modifier.width(14.dp))
            Image(
                modifier = Modifier
                    .width(115.dp)
                    .height(32.dp),
                painter = painterResource(id = R.drawable.img_logo_text_white),
                contentDescription = null,
            )
        }
    }

}
