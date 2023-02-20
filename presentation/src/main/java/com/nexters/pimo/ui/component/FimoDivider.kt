package com.nexters.pimo.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FimoDivider(
    modifier: Modifier = Modifier
) {
    Divider(modifier = modifier, color = DIVIDER_COLOR, thickness = DIVIDER_THICKNESS)
}

@Composable
fun FimoVertDivider(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.then(
            Modifier.width(DIVIDER_THICKNESS)
        ),
        color = DIVIDER_COLOR
    ) {}
}

private val DIVIDER_COLOR = Color(0xFFD4D4D4)
private val DIVIDER_THICKNESS = (0.75).dp