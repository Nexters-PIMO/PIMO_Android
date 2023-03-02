package com.nexters.pimo.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.theme.FimoTheme

class PrivacyPolicyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FimoTheme {
                PrivacyPolicyScreen (
                    onBack = { finish() },
                )
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, PrivacyPolicyActivity::class.java)
            context.startActivity(intent)
        }
    }
}