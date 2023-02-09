package com.nexters.pimo.core_feature.di

import android.content.Context
import android.speech.tts.TextToSpeech
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object TtsModule {

    @Provides
    @Singleton
    fun provideTextToSpeech(@ApplicationContext context: Context): TextToSpeech {
        lateinit var textToSpeech: TextToSpeech

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                with(textToSpeech) {
                    language = Locale.getDefault()
                    setPitch(1f)
                    setSpeechRate(1f)
                }
            }
        }

        return textToSpeech
    }
}
