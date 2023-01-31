package com.nexters.pimo.remote.di

import com.nexters.pimo.data.source.DummyDataSource
import com.nexters.pimo.remote.source.DummyDataSourceImpl
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
    fun bindDummyDataSource(source: DummyDataSourceImpl): DummyDataSource
}
