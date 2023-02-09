package com.nexters.pimo.feature.tts

interface TtsService {

    fun speakText(text: String): Unit

    fun close(): Unit
}
