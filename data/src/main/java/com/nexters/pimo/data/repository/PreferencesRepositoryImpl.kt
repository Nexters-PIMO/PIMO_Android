package com.nexters.pimo.data.repository

import com.nexters.pimo.data.source.PreferencesDataSource
import com.nexters.pimo.domain.repository.PreferencesRepository
import javax.inject.Inject

internal class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : PreferencesRepository {

    override suspend fun isTooltipVisible(): Result<Boolean> =
        preferencesDataSource.isTooltipVisible()

    override suspend fun closeTooltip(): Result<Unit> =
        preferencesDataSource.closeTooltip()
}
