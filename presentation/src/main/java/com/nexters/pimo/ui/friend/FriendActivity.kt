package com.nexters.pimo.ui.friend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.theme.FimoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendActivity : BaseActivity() {

    private val viewModel: FriendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FimoTheme {
                FriendScreen(
                    viewModel = viewModel,
                    onBack = ::finish,
                    onAction = {}
                )
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, FriendActivity::class.java)
            context.startActivity(intent)
        }
    }
}
