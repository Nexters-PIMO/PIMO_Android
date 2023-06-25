package com.nexters.pimo.local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nexters.pimo.data.model.BearerTokenData
import com.nexters.pimo.data.source.TokenDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TokenDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenDataSource {

    override suspend fun saveToken(token: BearerTokenData): Result<Unit> = runCatching {
        dataStore.edit { it[stringPreferencesKey(KEY_ACCESS_TOKEN)] = token.accessToken }
        dataStore.edit { it[stringPreferencesKey(KEY_REFRESH_TOKEN)] = token.refreshToken }
    }

    override suspend fun loadToken(): Result<BearerTokenData> = runCatching {
        val accessToken = dataStore.data.map {
            it[stringPreferencesKey(KEY_ACCESS_TOKEN)] ?: ""
        }.first()
        val refreshToken = dataStore.data.map {
            it[stringPreferencesKey(KEY_REFRESH_TOKEN)] ?: ""
        }.first()
        BearerTokenData(accessToken, refreshToken)
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
    }
}
