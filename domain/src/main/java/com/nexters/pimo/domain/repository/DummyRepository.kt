package com.nexters.pimo.domain.repository

import com.nexters.pimo.domain.model.DummyJson

interface DummyRepository {
    suspend fun getDummyJson(): Result<DummyJson>
}
