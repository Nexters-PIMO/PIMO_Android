package com.nexters.pimo.local.di

import com.nexters.pimo.data.source.PreferencesDataSource
import com.nexters.pimo.data.source.TokenDataSource
import com.nexters.pimo.local.source.PreferencesDataSourceImpl
import com.nexters.pimo.local.source.TokenDataSourceImpl
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
    fun bindPreferencesDataSource(source: PreferencesDataSourceImpl): PreferencesDataSource

    @Binds
    @Singleton
    fun bindTokenDataSource(source: TokenDataSourceImpl): TokenDataSource
}
