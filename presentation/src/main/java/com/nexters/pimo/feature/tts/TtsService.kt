package com.nexters.pimo.feature.tts

interface TtsService {

    fun speakText(text: String)

    fun setCallback(callback: () -> Unit)

    fun stop()

    fun close()
}
