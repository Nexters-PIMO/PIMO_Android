package com.nexters.pimo.remote.model

import com.google.gson.annotations.SerializedName

data class ImgurResponse(
    @SerializedName("data")
    val uploadedImage: UploadedImage
)

data class UploadedImage(
    val link: String
)
