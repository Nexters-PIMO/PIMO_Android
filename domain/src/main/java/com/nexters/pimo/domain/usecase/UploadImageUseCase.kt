package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.repository.ImageRepository
import java.io.File
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(file: File) = imageRepository.uploadImage(file)
}
