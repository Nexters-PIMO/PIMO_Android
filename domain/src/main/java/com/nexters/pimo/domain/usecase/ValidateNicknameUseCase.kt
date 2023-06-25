package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.repository.UserRepository
import javax.inject.Inject

class ValidateNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(nickname: String): Result<Boolean> =
        userRepository.validateNickname(nickname)
}