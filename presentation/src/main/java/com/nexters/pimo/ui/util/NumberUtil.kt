package com.nexters.pimo.ui.util

import java.text.NumberFormat
import kotlin.math.round

object NumberUtil {

    fun Int.toSymbolFormat(): String {
        return when {
            this >= 1E9 -> "${round(this.toFloat() / 1E9 * 10) / 10}B"
            this >= 1E6 -> "${round(this.toFloat() / 1E6 * 10) / 10}M"
            this >= 1E3 -> "${round(this.toFloat() / 1E3 * 10) / 10}K"
            else -> NumberFormat.getInstance().format(this)
        }
    }

    fun Long.toSymbolFormat(): String {
        return when {
            this >= 1E9 -> "${round(this.toFloat() / 1E9 * 10) / 10}B"
            this >= 1E6 -> "${round(this.toFloat() / 1E6 * 10) / 10}M"
            this >= 1E3 -> "${round(this.toFloat() / 1E3 * 10) / 10}K"
            else -> NumberFormat.getInstance().format(this)
        }
    }
}
