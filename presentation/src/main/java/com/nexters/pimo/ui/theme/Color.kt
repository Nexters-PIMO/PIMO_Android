package com.nexters.pimo.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

val FimoPrimary = Color(0xFFFD542E)
val FimoPrimaryDark = Color(0xFFEC5629)
val FimoSecondary = Color(0xFF000000)
val GreyF7 = Color(0xFFF7F7F7)
val GreyEF = Color(0xFFEFEFEF)
val GreyE9 = Color(0xFFE9E9E9)
val GreyD9 = Color(0xFFD9D9D9)
val GreyD0 = Color(0xFFD0D0D0)
val Grey7F = Color(0xFF7F7F7F)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

val MaterialColors = lightColorScheme(
    primary = FimoPrimary,
    onPrimary = FimoSecondary,
    secondary = FimoSecondary,
    onSecondary = FimoPrimary,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

@Stable
class FimoColors(
    primary: Color,
    primaryDark: Color,
    secondary: Color,
    greyF7: Color,
    greyEF: Color,
    greyE9: Color,
    greyD0: Color,
    greyD9: Color,
    grey7F: Color,
    black: Color,
    white: Color
) {
    var primary: Color by mutableStateOf(primary, structuralEqualityPolicy())
        private set
    var primaryDark: Color by mutableStateOf(primaryDark, structuralEqualityPolicy())
        private set
    var secondary: Color by mutableStateOf(secondary, structuralEqualityPolicy())
        private set
    var greyF7: Color by mutableStateOf(greyF7, structuralEqualityPolicy())
        private set
    var greyEF: Color by mutableStateOf(greyEF, structuralEqualityPolicy())
        private set
    var greyD0: Color by mutableStateOf(greyD0, structuralEqualityPolicy())
        private set
    var greyE9: Color by mutableStateOf(greyE9, structuralEqualityPolicy())
        private set
    var greyD9: Color by mutableStateOf(greyD9, structuralEqualityPolicy())
        private set
    var grey7F: Color by mutableStateOf(grey7F, structuralEqualityPolicy())
        private set
    var black: Color by mutableStateOf(black, structuralEqualityPolicy())
        private set
    var white: Color by mutableStateOf(white, structuralEqualityPolicy())
        private set

    fun copy(
        primary: Color = this.primary,
        primaryDark: Color = this.primaryDark,
        secondary: Color = this.secondary,
        greyF7: Color = this.greyF7,
        greyEF: Color = this.greyEF,
        greyE9: Color = this.greyE9,
        greyD0: Color = this.greyD0,
        greyD9: Color = this.greyD9,
        grey7F: Color = this.grey7F,
        black: Color = this.black,
        white: Color = this.white
    ) = FimoColors(
        primary = primary,
        primaryDark = primaryDark,
        secondary = secondary,
        greyF7 = greyF7,
        greyEF = greyEF,
        greyE9 = greyE9,
        greyD0 = greyD0,
        greyD9 = greyD9,
        grey7F = grey7F,
        black = black,
        white = white
    )
}

fun fimoLightColors(
    primary: Color = FimoPrimary,
    primaryDark: Color = FimoPrimaryDark,
    secondary: Color = FimoSecondary,
    greyF7: Color = GreyF7,
    greyEF: Color = GreyEF,
    greyE9: Color = GreyE9,
    greyD0: Color = GreyD0,
    greyD9: Color = GreyD9,
    grey7F: Color = Grey7F,
    black: Color = Black,
    white: Color = White
) = FimoColors(
    primary = primary,
    primaryDark = primaryDark,
    secondary = secondary,
    greyF7 = greyF7,
    greyEF = greyEF,
    greyE9 = greyE9,
    greyD0 = greyD0,
    greyD9 = greyD9,
    grey7F = grey7F,
    black = black,
    white = white
)

internal val LocalColors = staticCompositionLocalOf { fimoLightColors() }
