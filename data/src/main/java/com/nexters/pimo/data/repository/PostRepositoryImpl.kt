package com.nexters.pimo.data.repository

import com.nexters.pimo.data.model.toDomain
import com.nexters.pimo.data.source.PostDataSource
import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.domain.repository.PostRepository
import javax.inject.Inject

internal class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource
) : PostRepository {

    override suspend fun getFeed(): Result<List<Post>> =
        postDataSource.getFeed().mapCatching { it.map { feedData -> feedData.toDomain() } }
}
