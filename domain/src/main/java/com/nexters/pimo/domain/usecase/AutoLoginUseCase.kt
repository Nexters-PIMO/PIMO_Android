package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.repository.AuthRepository
import javax.inject.Inject

class AutoLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<LoginResult> =
        authRepository.login()
}
