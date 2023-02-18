package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.repository.PreferencesRepository
import javax.inject.Inject

class CloseTooltipUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    suspend operator fun invoke(): Result<Unit> = preferencesRepository.closeTooltip()
}
