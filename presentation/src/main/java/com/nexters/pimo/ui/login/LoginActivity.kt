package com.nexters.pimo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.feature.kakao.KakaoLogin
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.main.MainActivity
import com.nexters.pimo.ui.profile.ProfileActivity
import com.nexters.pimo.ui.theme.FimoTheme

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    @Inject
    lateinit var kakaoLogin: KakaoLogin

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.observe(lifecycleOwner = this, state = ::handleState, sideEffect = ::handleSideEffect)

        setContent {
            FimoTheme {
                LoginScreen (
                    onLogin = { requestKakaoLogin() }
                )
            }
        }
    }

    private fun handleState(state: LoginState) {
        when (state.result) {
            is LoginResult.SignedIn -> startMainActivity(user = state.result.user)
            LoginResult.NotSignedUpYet -> startProfileActivity()
            else -> Unit
        }
    }

    private fun startProfileActivity() {
        ProfileActivity.startActivity(this)
//        finish()
    }

    private fun startMainActivity(user: User) {
//        MainActivity.startActivity(this)
//        finish()
    }

    private fun handleSideEffect(sideEffect: LoginSideEffect) {
        when (sideEffect) {
            is LoginSideEffect.Toast -> LoginSideEffect.Toast(sideEffect.text)
        }
    }

    private fun requestKakaoLogin() = lifecycleScope.launch {
        kakaoLogin.login(this@LoginActivity)
            .onSuccess {
//                Log.d("accessToken: ", it.kakaoAccessToken)
//                Log.d("refreshToken: ", it.kakaoRefreshToken)
                loginViewModel.login(it.kakaoAccessToken, it.kakaoRefreshToken)
            }
            .onFailure {
                handleException(it)
            }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
