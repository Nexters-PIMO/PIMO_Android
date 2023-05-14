package com.nexters.pimo.local.provider

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nexters.pimo.data.provider.RefreshTokenProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class RefreshTokenProviderImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : RefreshTokenProvider {

    override var value: String
        get() = runBlocking {
            dataStore.data.map {
                it[stringPreferencesKey(KEY_REFRESH_TOKEN)] ?: ""
            }.first()
        }
        set(value) = runBlocking {
            dataStore.edit {
                it[stringPreferencesKey(KEY_REFRESH_TOKEN)] = value
            }
        }


    companion object {
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }
}
