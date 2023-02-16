package com.nexters.pimo.ui.theme

import android.app.Activity
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

@Composable
fun FimoTheme(
    colors: FimoColors = FimoTheme.colors,
    typography: FimoTypography = FimoTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography,
        LocalContentColor provides colors.white
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.statusBarColor = colors.white.toArgb()
                ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = true
            }
        }

        MaterialTheme(
            colorScheme = MaterialColors,
            content = content
        )
    }
}

object FimoTheme {

    val colors: FimoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: FimoTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
