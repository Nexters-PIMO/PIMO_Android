package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.model.DummyJson
import com.nexters.pimo.domain.repository.DummyRepository
import javax.inject.Inject

class GetDummyJsonUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {

    suspend operator fun invoke(): Result<DummyJson> = dummyRepository.getDummyJson()
}
