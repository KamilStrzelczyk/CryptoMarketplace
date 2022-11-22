package com.cryptomarketplace.infrastructure.di

import com.cryptomarketplace.domain.repository.CryptoMarketPlaceRepository
import com.cryptomarketplace.infrastructure.repository.CryptoMarketPlaceRepositoryImpl
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
        cryptoMarketPlaceRepositoryImpl: CryptoMarketPlaceRepositoryImpl,
    ): CryptoMarketPlaceRepository
}