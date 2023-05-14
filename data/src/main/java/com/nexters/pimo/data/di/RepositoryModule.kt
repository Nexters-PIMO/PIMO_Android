package com.nexters.pimo.data.di

import com.nexters.pimo.data.repository.AuthRepositoryImpl
import com.nexters.pimo.data.repository.PreferencesRepositoryImpl
import com.nexters.pimo.domain.repository.AuthRepository
import com.nexters.pimo.domain.repository.PreferencesRepository
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
    fun bindPreferencesRepository(repository: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    @Singleton
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}
