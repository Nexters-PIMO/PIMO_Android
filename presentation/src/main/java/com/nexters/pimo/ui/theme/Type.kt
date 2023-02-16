package com.nexters.pimo.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.nexters.pimo.ui.R
import javax.annotation.concurrent.Immutable

private val Pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_light, FontWeight.Light),
)

private val PretendardVariable = FontFamily(
    Font(R.font.pretendard_variable)
)

val Typography = FimoTypography(
    bold = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Bold),
    semibold = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.SemiBold),
    medium = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Medium),
    regular = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Normal),
    light = TextStyle(fontFamily = Pretendard, fontWeight = FontWeight.Light),
    variable = TextStyle(fontFamily = PretendardVariable),
)

@Immutable
data class FimoTypography(
    val bold: TextStyle,
    val semibold: TextStyle,
    val medium: TextStyle,
    val regular: TextStyle,
    val light: TextStyle,
    val variable: TextStyle
)

internal val LocalTypography = staticCompositionLocalOf { Typography }
