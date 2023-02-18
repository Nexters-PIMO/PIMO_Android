package com.nexters.pimo.domain.repository

interface PreferencesRepository {

    suspend fun isTooltipVisible(): Result<Boolean>

    suspend fun closeTooltip(): Result<Unit>
}
