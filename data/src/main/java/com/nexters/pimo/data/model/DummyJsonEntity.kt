package com.nexters.pimo.data.model

import com.nexters.pimo.domain.model.DummyJson

data class DummyJsonEntity(
    val id: Long,
    val title: String,
    val images: List<String>
) {
    
    fun toDomain() = DummyJson(
        id = id,
        title = title,
        images = images
    )
}
