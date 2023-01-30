package com.nexters.pimo.remote.model

import com.nexters.pimo.data.model.DummyJsonEntity
import kotlinx.serialization.Serializable

@Serializable
data class DummyJsonResponse(
    val id: Long,
    val title: String,
    val images: List<String>
) {

    fun toData() = DummyJsonEntity(
        id = id,
        title = title,
        images = images
    )
}
