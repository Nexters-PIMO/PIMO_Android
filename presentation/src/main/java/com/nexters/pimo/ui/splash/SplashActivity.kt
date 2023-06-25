package com.nexters.pimo.ui.splash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.main.MainActivity
import com.nexters.pimo.ui.onboard.OnboardActivity
import com.nexters.pimo.ui.theme.FimoTheme
import org.orbitmvi.orbit.viewmodel.observe

class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.observe(
            lifecycleOwner = this,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        setContent {
            FimoTheme {
                SplashScreen()
            }
        }
    }

    private fun handleState(splashState: SplashState) {
        when (splashState.state) {
            SplashUiState.AlreadyLoggedIn -> startMainActivity()
            SplashUiState.NeedToOnboard -> startOnboardActivity()
            else -> Unit
        }
    }

    private fun handleSideEffect(sideEffect: SplashSideEffect) {
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    private fun startOnboardActivity() {
        val intent = OnboardActivity.getIntent(this, isLogin = true)
        startActivity(intent)
        finish()
    }

}