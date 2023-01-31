package com.nexters.pimo.data.repository

import com.nexters.pimo.data.source.DummyDataSource
import com.nexters.pimo.domain.model.DummyJson
import com.nexters.pimo.domain.repository.DummyRepository
import javax.inject.Inject

internal class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource
) : DummyRepository {

    override suspend fun getDummyJson(): Result<DummyJson> =
        dummyDataSource.getDummyJson().map { it.toDomain() }
}
