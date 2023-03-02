package com.nexters.pimo.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.FimoHomeAppBar
import com.nexters.pimo.ui.component.FimoPostList
import com.nexters.pimo.ui.component.bottomPanelHeight
import com.nexters.pimo.ui.settings.SettingsActivity
import com.nexters.pimo.ui.state.UiState
import com.nexters.pimo.ui.theme.FimoTheme
import com.nexters.pimo.ui.util.DateUtil.isToday
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    startSettingsActivity: () -> Unit
) {
    val state = viewModel.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        FimoHomeAppBar(
            onActionClick = startSettingsActivity ,
            newPostCount = state.posts.filter { it.postedTime.isToday() }.size
        )
        when (state.uiState) {
            UiState.Done -> {
                if (state.posts.isNotEmpty()) {
                    FimoPostList(
                        posts = state.posts,
                        isAudioPlaying = state.isAudioPlaying,
                        showTooltip = state.showTooltip,
                        onCloseTooltip = viewModel::onCloseTooltip,
                        onPlayAudio = viewModel::onPlayAudio,
                        onStopAudio = viewModel::onStopAudio
                    )
                } else {
                    Welcome()
                }
            }
            UiState.Loading -> {
                Loading()
            }
            is UiState.Failure -> {
                /* TODO */
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPanelHeight),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Welcome() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 50.dp, horizontal = 52.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo_text),
                contentDescription = null,
                modifier = Modifier.height(17.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = stringResource(id = R.string.welcome),
                style = FimoTheme.typography.medium.copy(fontSize = 19.sp)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = stringResource(id = R.string.welcome_info),
            style = FimoTheme.typography.light.copy(
                fontSize = 14.sp,
                color = FimoTheme.colors.grey7F,
                lineHeight = 21.sp
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.img_welcome),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
