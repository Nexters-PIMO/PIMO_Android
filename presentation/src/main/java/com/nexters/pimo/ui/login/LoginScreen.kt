package com.nexters.pimo.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.theme.FimoTheme


@Composable
fun LoginScreen(onLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.bg_login), contentScale = ContentScale.FillHeight)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(199.dp))
            Text(
                text = stringResource(id = R.string.login_title),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 15.sp,
                    color = FimoTheme.colors.greyD9
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp),
                painter = painterResource(id = R.drawable.img_logo_login),
                contentDescription = null,
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 68.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_sns),
                style = FimoTheme.typography.medium.copy(
                    fontSize = 14.sp,
                    color = FimoTheme.colors.white
                ),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(24.dp))
            KakaoLoginButton(onClick = onLogin)
        }
    }

}

@Composable
fun KakaoLoginButton(onClick: () -> Unit) {
    val interactionSource = MutableInteractionSource()

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEE500),
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onClick()
            }
            .height(54.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_kakao),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(26.dp)
            )
            Text(
                text = stringResource(id = R.string.login_kakao),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 18.sp,
                    color = FimoTheme.colors.black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

