package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.model.SignUpUser
import com.nexters.pimo.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(user: SignUpUser): Result<Unit> = authRepository.signUp(user)
}
