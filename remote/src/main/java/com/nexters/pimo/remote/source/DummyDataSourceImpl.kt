package com.nexters.pimo.remote.source

import com.nexters.pimo.data.model.DummyJsonEntity
import com.nexters.pimo.data.source.DummyDataSource
import com.nexters.pimo.remote.model.DummyJsonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class DummyDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : DummyDataSource {

    override suspend fun getDummyJson(): Result<DummyJsonEntity> = runCatching {
        val responseBody: DummyJsonResponse = httpClient.get("product/1").body()
        responseBody.toData()
    }
}
