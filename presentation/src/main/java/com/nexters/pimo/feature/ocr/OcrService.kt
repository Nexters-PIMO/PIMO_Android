package com.nexters.pimo.feature.ocr

import android.graphics.Bitmap
import java.io.File

interface OcrService {

    fun getTextOfImage(image: Bitmap): String

    fun getTextOfImage(imagePath: File): String

    fun close(): Unit
}
