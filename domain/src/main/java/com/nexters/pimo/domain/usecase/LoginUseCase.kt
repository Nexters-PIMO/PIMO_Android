package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.model.ProviderToken
import com.nexters.pimo.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<LoginResult> =
        authRepository.login()
    
    suspend operator fun invoke(token: ProviderToken): Result<LoginResult> =
        authRepository.login(token)
}
