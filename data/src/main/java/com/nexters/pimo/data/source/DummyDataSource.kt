package com.nexters.pimo.data.source

import com.nexters.pimo.data.model.DummyJsonEntity

interface DummyDataSource {
    suspend fun getDummyJson(): Result<DummyJsonEntity>
}
