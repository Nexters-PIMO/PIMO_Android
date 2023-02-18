package com.nexters.pimo.data.source

interface PreferencesDataSource {

    suspend fun isTooltipVisible(): Result<Boolean>

    suspend fun closeTooltip(): Result<Unit>
}
