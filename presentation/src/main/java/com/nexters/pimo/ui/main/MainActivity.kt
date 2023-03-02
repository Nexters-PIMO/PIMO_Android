package com.nexters.pimo.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.friend.FriendActivity
import com.nexters.pimo.ui.settings.SettingsActivity
import com.nexters.pimo.ui.theme.FimoTheme
import com.nexters.pimo.ui.upload.UploadActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FimoTheme {
                MainScreen(
                    startUploadActivity = ::startUploadActivity,
                    startFriendActivity = ::startFriendActivity,
                    startSettingsActivity = ::startSettingsActivity,
                )
            }
        }
    }

    private fun startUploadActivity() {
        UploadActivity.startActivity(this)
    }

    private fun startFriendActivity() {
        FriendActivity.startActivity(this)
    }

    private fun startSettingsActivity() {
        SettingsActivity.startActivity(this)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
