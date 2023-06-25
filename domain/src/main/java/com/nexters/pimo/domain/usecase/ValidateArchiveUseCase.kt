package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.repository.UserRepository
import javax.inject.Inject

class ValidateArchiveUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(archive: String): Result<Boolean> =
        userRepository.validateArchive(archive)
}
