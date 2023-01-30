package com.nexters.pimo.data.di

import com.nexters.pimo.data.repository.DummyRepositoryImpl
import com.nexters.pimo.domain.repository.DummyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindDummyRepository(repository: DummyRepositoryImpl): DummyRepository
}
