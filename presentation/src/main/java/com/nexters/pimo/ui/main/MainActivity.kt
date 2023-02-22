package com.nexters.pimo.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.friend.FriendActivity
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
                    startFriendActivity = ::startFriendActivity
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
}
