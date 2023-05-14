package com.nexters.pimo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.model.User
import com.nexters.pimo.feature.kakao.KakaoLogin
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.profile.ProfileActivity
import com.nexters.pimo.ui.profile.state.Mode
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

        setContent {
            FimoTheme {
                LoginScreen(
                    onLogin = { requestKakaoLogin() }
                )
            }
        }

        loginViewModel.observe(
            lifecycleOwner = this,
            state = ::handleState
        )
    }

    private fun handleState(state: LoginState) {
        when (state.result) {
            is LoginResult.SignedIn -> startMainActivity(user = User.Unspecified)
            LoginResult.NeedToSignUp -> startProfileActivity()
            else -> Unit
        }
    }

    private fun startProfileActivity() {
        val intent = ProfileActivity.getIntent(this, mode = Mode.New)
        startActivity(intent)
    }

    private fun startMainActivity(user: User) {
//        MainActivity.startActivity(this)
//        finish()
    }

    private fun requestKakaoLogin() = lifecycleScope.launch {
        kakaoLogin.login(this@LoginActivity)
            .onSuccess { loginViewModel.login(it) }
            .onFailure { handleException(it) }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
