package com.nexters.pimo.core_feature.di

import com.nexters.pimo.core_feature.ocr.OcrServiceImpl
import com.nexters.pimo.feature.ocr.OcrService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ServiceModule {

    @Binds
    @Singleton
    fun bindOcrService(service: OcrServiceImpl): OcrService
}
