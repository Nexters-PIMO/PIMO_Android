package com.nexters.pimo.data.source

import com.nexters.pimo.data.model.BearerTokenData

interface TokenDataSource {

    suspend fun saveToken(token: BearerTokenData): Result<Unit>

    suspend fun loadToken(): Result<BearerTokenData>
}
