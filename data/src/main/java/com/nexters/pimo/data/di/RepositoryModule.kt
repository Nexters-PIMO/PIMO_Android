package com.nexters.pimo.data.di

import com.nexters.pimo.data.repository.AuthRepositoryImpl
import com.nexters.pimo.data.repository.ImageRepositoryImpl
import com.nexters.pimo.data.repository.PostRepositoryImpl
import com.nexters.pimo.data.repository.PreferencesRepositoryImpl
import com.nexters.pimo.data.repository.UserRepositoryImpl
import com.nexters.pimo.domain.repository.AuthRepository
import com.nexters.pimo.domain.repository.ImageRepository
import com.nexters.pimo.domain.repository.PostRepository
import com.nexters.pimo.domain.repository.PreferencesRepository
import com.nexters.pimo.domain.repository.UserRepository
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

    @Binds
    @Singleton
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindImageRepository(repository: ImageRepositoryImpl): ImageRepository

    @Binds
    @Singleton
    fun bindPostRepository(repository: PostRepositoryImpl): PostRepository
}
