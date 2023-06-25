package com.nexters.pimo.domain.repository

import java.io.File

interface ImageRepository {

    suspend fun uploadImage(file: File): Result<String>
}
