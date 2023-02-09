package com.nexters.pimo.core_feature.tts

import android.speech.tts.TextToSpeech
import com.nexters.pimo.feature.tts.TtsService
import javax.inject.Inject

internal class TtsServiceImpl @Inject constructor(
    private val textToSpeech: TextToSpeech
) : TtsService {

    override fun speakText(text: String) {
        if (textToSpeech.isSpeaking) textToSpeech.stop()
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, text)
    }

    override fun close() {
        if (textToSpeech.isSpeaking) textToSpeech.stop()
        textToSpeech.shutdown()
    }
}
