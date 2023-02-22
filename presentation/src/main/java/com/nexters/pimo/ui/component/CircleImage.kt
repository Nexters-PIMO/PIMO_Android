package com.nexters.pimo.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.nexters.pimo.ui.theme.FimoTheme

@Composable
fun CircleImage(
    imageSource: String,
    placeholderColor: Color = FimoTheme.colors.grey7F,
    size: Dp
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = CircleShape,
            color = placeholderColor,
            modifier = Modifier.size(size)
        ) {}
        AsyncImage(
            model = imageSource,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(size)
        )
    }
}
