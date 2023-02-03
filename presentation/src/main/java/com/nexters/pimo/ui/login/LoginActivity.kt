package com.nexters.pimo.ui.login

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.nexters.pimo.feature.kakao.KakaoLogin
import com.nexters.pimo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

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
            .onFailure {
                handleException(it)
            }
    }
}
