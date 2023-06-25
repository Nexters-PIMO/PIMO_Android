package com.nexters.pimo.data.repository

import com.nexters.pimo.data.source.ImageDataSource
import com.nexters.pimo.domain.repository.ImageRepository
import java.io.File
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataSource: ImageDataSource
) : ImageRepository {

    override suspend fun uploadImage(file: File): Result<String> = imageDataSource.uploadImage(file)
}
