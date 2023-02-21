package com.nexters.pimo.ui.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp

object CustomModifier {

    fun Modifier.bottomElevation(): Modifier = this.then(
        Modifier.drawWithContent {
            val paddingPx = 32.dp.toPx()
            clipRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height + paddingPx
            ) {
                this@drawWithContent.drawContent()
            }
        }
    )
}
