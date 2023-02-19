package com.nexters.pimo.ui.upload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UploadScreen()
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, UploadActivity::class.java)
            context.startActivity(intent)
        }
    }
}
