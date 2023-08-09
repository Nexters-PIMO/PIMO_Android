package com.nexters.pimo.remote.source

import com.nexters.pimo.data.model.FeedData
import com.nexters.pimo.data.source.PostDataSource
import com.nexters.pimo.remote.api.ApiService
import com.nexters.pimo.remote.model.toData
import javax.inject.Inject

internal class PostDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : PostDataSource {

    override suspend fun getFeed(): Result<List<FeedData>> = runCatching {
        apiService.getFeed().map { it.toData() }
    }
}
