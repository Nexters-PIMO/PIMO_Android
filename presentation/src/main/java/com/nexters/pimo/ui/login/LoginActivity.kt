package com.nexters.pimo.ui.login

import android.os.Bundle
import androidx.activity.compose.setContent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.nexters.pimo.feature.kakao.KakaoLogin
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    @Inject
    lateinit var kakaoLogin: KakaoLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(
                onLogin = { requestKakaoLogin() }
            )
        }
    }

    private fun requestKakaoLogin() = lifecycleScope.launch {
        kakaoLogin.login(this@LoginActivity)
            .onSuccess {
                Log.d("accessToken: ", it.kakaoAccessToken)
                Log.d("refreshToken: ", it.kakaoRefreshToken)
            }
            .onFailure { /* no-op */ }
    }

}
