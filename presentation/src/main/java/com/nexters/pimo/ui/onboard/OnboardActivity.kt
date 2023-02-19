package com.nexters.pimo.ui.onboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OnboardScreen (
                onSkip = { startProfileActivity() }
            )
        }
    }

    private fun startProfileActivity() {
//        ProfileActivity.startActivity(this)
//        finish()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, OnboardActivity::class.java)
            context.startActivity(intent)
        }
    }
}