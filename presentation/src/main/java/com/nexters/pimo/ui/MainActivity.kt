package com.nexters.pimo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.state.UiState
import com.nexters.pimo.ui.theme.PIMOTheme
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PIMOTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state = viewModel.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state.dummyJson) {
            null -> {
                Button(onClick = viewModel::onDummyButtonClick) {
                    Text("Get Dummy Json")
                }
            }
            else -> {
                Text(text = state.dummyJson.toString())
            }
        }
        if (state.uiState is UiState.Loading) {
            CircularProgressIndicator()
        }
    }
}
