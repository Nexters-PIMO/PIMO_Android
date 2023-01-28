package com.nexters.pimo.ui.login

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nexters.pimo.ui.R


@Composable
fun LoginScreen(onLogin: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginGraphic(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .height(192.dp)
                .padding(start = 24.dp, top = 60.dp, end = 24.dp, bottom = 48.dp)
        ) {
            KakaoLoginButton(onClick = onLogin)
        }
    }
}

@Composable
fun LoginGraphic(modifier: Modifier = Modifier) {
    Surface(
        color = Color.White,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterVertically),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {

        }
    }
}

@Composable
fun KakaoLoginButton(onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEE500),
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_kakao),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            //HorizontalSpacer(width = 12.dp)
            Text(
                text = stringResource(R.string.login_kakao),
                color = Color.Black.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


