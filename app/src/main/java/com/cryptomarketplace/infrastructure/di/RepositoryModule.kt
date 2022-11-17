package com.example.cryptomarketplace2.infrastructure.di

import com.example.cryptomarketplace2.domain.repository.CryptoMarketPlaceRepository
import com.example.cryptomarketplace2.infrastructure.repository.CryptoMarketPlaceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCryptoMarketPlaceRepository(
        cryptoMarketPlaceImpl: CryptoMarketPlaceImpl,
    ): CryptoMarketPlaceRepository
}