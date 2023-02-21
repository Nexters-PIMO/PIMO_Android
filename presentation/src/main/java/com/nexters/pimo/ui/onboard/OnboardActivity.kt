package com.nexters.pimo.ui.onboard

import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.login.LoginActivity
import com.nexters.pimo.ui.theme.FimoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FimoTheme {
                OnboardScreen (
                    onSkip = { startLoginActivity() }
                )
            }
        }
    }

    private fun startLoginActivity() {
        LoginActivity.startActivity(this)
        finish()
    }

}