package com.nexters.pimo.data.source

import com.nexters.pimo.data.model.BearerTokenData
import com.nexters.pimo.data.model.ProviderTokenData
import com.nexters.pimo.data.model.SignUpData

interface AuthDataSource {

    suspend fun login(token: ProviderTokenData): Result<BearerTokenData>

    suspend fun logout(): Result<Unit>

    suspend fun signUp(user: SignUpData): Result<Unit>
}
