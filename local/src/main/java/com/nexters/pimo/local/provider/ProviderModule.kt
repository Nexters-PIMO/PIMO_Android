package com.nexters.pimo.local.provider

import com.nexters.pimo.data.provider.AccessTokenProvider
import com.nexters.pimo.data.provider.RefreshTokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun bindAccessTokenProvider(provider: AccessTokenProviderImpl): AccessTokenProvider

    @Binds
    abstract fun bindRefreshTokenProvider(provider: RefreshTokenProviderImpl): RefreshTokenProvider
}
