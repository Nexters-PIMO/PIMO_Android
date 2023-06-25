package com.nexters.pimo.remote.di

import com.nexters.pimo.data.source.AuthDataSource
import com.nexters.pimo.data.source.ImageDataSource
import com.nexters.pimo.data.source.UserDataSource
import com.nexters.pimo.remote.source.AuthDataSourceImpl
import com.nexters.pimo.remote.source.ImageDataSourceImpl
import com.nexters.pimo.remote.source.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    @Singleton
    fun bindAuthDataSource(sourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    fun bindUserDataSource(sourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    @Singleton
    fun bindImageDataSource(sourceImpl: ImageDataSourceImpl): ImageDataSource
}
