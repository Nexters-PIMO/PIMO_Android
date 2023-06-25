package com.nexters.pimo.remote.source

import com.nexters.pimo.data.source.ImageDataSource
import com.nexters.pimo.remote.api.ImgurService
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val imgurService: ImgurService
) : ImageDataSource {

    override suspend fun uploadImage(file: File): Result<String> = runCatching {

        val formData = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())
        imgurService.uploadImage(formData).uploadedImage.link
    }
}
