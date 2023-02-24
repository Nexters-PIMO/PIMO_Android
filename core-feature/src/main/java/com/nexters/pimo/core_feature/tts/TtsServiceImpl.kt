package com.nexters.pimo.core_feature.tts

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import com.nexters.pimo.feature.tts.TtsService
import javax.inject.Inject

internal class TtsServiceImpl @Inject constructor(
    private val textToSpeech: TextToSpeech
) : TtsService {

    override fun speakText(text: String) {
        if (textToSpeech.isSpeaking) textToSpeech.stop()
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, text)
    }

    override fun setCallback(callback: () -> Unit) {
        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(utteranceId: String?) {
                callback()
            }

            override fun onError(utteranceId: String?) {

            }

            override fun onStart(utteranceId: String?) {
                
            }
        })
    }

    override fun stop() {
        if (textToSpeech.isSpeaking) textToSpeech.stop()
    }

    override fun close() {
        stop()
        textToSpeech.shutdown()
    }
}
