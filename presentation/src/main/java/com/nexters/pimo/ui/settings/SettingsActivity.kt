package com.nexters.pimo.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.onboard.OnboardActivity
import com.nexters.pimo.ui.profile.ProfileActivity
import com.nexters.pimo.ui.profile.state.Mode
import com.nexters.pimo.ui.theme.FimoTheme


class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FimoTheme {
                SettingsScreen (
                    onBack = { finish() },
                    startOnboardActivity = ::startOnboardActivity,
                    startProfileActivity = ::startProfileActivity,
                    startPrivacyPolicyActivity = :: startPrivacyPolicyActivity,
                )
            }
        }
    }

    private fun startProfileActivity() {
        val intent = ProfileActivity.getIntent(this, mode = Mode.Edit)
        startActivity(intent)
    }

    private fun startOnboardActivity() {
        val intent = OnboardActivity.getIntent(this, isLogin = false)
        startActivity(intent)
    }

    private fun startPrivacyPolicyActivity() {
        PrivacyPolicyActivity.startActivity(this)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent)
        }
    }

}