package com.nexters.pimo.ui.component

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FimoDivider(
    modifier: Modifier = Modifier
) {
    Divider(modifier = modifier, color = Color(0xFFD4D4D4), thickness = (0.75).dp)
}
