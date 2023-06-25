package com.nexters.pimo.data.source

import java.io.File

interface ImageDataSource {

    suspend fun uploadImage(file: File): Result<String>
}
