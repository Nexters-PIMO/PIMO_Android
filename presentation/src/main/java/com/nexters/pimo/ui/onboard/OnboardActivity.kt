package com.nexters.pimo.ui.onboard

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.login.LoginActivity
import com.nexters.pimo.ui.onboard.OnboardViewModel.Companion.KEY_MODE
import com.nexters.pimo.ui.theme.FimoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FimoTheme {
                OnboardScreen(
                    onSkip = { startLoginActivity() },
                    onBack = { finish() }
                )
            }
        }
    }

    private fun startLoginActivity() {
        LoginActivity.startActivity(this)
        finish()
    }

    companion object {
        fun getIntent(context: Context, isLogin: Boolean) =
            Intent(context, OnboardActivity::class.java)
                .addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra(KEY_MODE, isLogin)
    }
}
