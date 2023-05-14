package com.nexters.pimo.remote.di

import com.nexters.pimo.data.source.AuthDataSource
import com.nexters.pimo.remote.source.AuthDataSourceImpl
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
}
