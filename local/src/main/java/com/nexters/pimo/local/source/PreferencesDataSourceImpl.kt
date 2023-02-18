package com.nexters.pimo.local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.nexters.pimo.data.source.PreferencesDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferencesDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferencesDataSource {

    override suspend fun isTooltipVisible(): Result<Boolean> = runCatching {
        dataStore.data.map { it[booleanPreferencesKey(KEY_SHOW_TOOLTIP)] ?: true }.first()
    }

    override suspend fun closeTooltip(): Result<Unit> = runCatching {
        dataStore.edit { it[booleanPreferencesKey(KEY_SHOW_TOOLTIP)] = false }
    }

    companion object {
        private const val KEY_SHOW_TOOLTIP = "show_tooltip"
    }
}
