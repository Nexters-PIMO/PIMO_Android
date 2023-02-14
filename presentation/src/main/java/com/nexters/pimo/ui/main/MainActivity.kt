package com.nexters.pimo.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.theme.PIMOTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PIMOTheme {
                MainScreen()
            }
        }
    }
}
