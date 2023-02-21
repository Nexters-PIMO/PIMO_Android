package com.nexters.pimo.ui.onboard

import androidx.annotation.StringRes

data class OnboardStep(
    val step: Int,
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int,
    @StringRes val contentRes: Int
)