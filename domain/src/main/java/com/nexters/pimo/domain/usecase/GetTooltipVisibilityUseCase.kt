package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.repository.PreferencesRepository
import javax.inject.Inject

class GetTooltipVisibilityUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    suspend operator fun invoke(): Result<Boolean> = preferencesRepository.isTooltipVisible()
}
