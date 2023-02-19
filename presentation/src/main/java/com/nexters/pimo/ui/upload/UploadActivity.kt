package com.nexters.pimo.ui.upload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nexters.pimo.ui.base.ActivityTransition
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.theme.FimoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadActivity : BaseActivity() {

    private val viewModel: UploadViewModel by viewModels()
    override val transitionAnimation = ActivityTransition.Animation.Cover
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FimoTheme {
                UploadScreen(
                    viewModel = viewModel,
                    onBack = ::finish
                )
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, UploadActivity::class.java)
            context.startActivity(intent)
        }
    }
}
