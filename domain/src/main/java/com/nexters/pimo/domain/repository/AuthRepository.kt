package com.nexters.pimo.domain.repository

import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.model.ProviderToken
import com.nexters.pimo.domain.model.SignUpUser

interface AuthRepository {

    suspend fun login(): Result<LoginResult>
    
    suspend fun login(token: ProviderToken): Result<LoginResult>

    suspend fun logout(): Result<Unit>

    suspend fun signUp(user: SignUpUser): Result<Unit>
}