package com.nexters.pimo.domain.repository

import com.nexters.pimo.domain.model.Post

interface PostRepository {

    suspend fun getFeed(): Result<List<Post>>
}
