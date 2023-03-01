package com.nexters.pimo.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexters.pimo.domain.model.Post

@Composable
fun FimoPostList(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    isAudioPlaying: Boolean,
    showTooltip: Boolean,
    onClickMore: (Post) -> Unit,
    onCloseTooltip: () -> Unit,
    onPlayAudio: (String) -> Unit,
    onStopAudio: () -> Unit
    onCopyText: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 21.dp, horizontal = 20.dp)
    ) {
        items(posts) {
            Column {
                FimoPost(
                    post = it,
                    isAudioPlaying = isAudioPlaying,
                    showTooltip = showTooltip,
                    onCloseTooltip = onCloseTooltip,
                    onClickMore = onClickMore,
                    onPlayAudio = onPlayAudio,
                    onStopAudio = onStopAudio,
                    onCopyText = onCopyText,
                    onClap = { /*TODO*/ },
                    onShare = { /*TODO*/ }
                )
            }
            FimoDivider(modifier = Modifier.padding(top = 9.dp, bottom = 21.dp))
        }
    }
}
