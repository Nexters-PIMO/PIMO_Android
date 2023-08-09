package com.nexters.pimo.data.source

import com.nexters.pimo.data.model.FeedData

interface PostDataSource {

    suspend fun getFeed(): Result<List<FeedData>>
}
