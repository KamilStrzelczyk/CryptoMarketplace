package com.example.cryptomarketplace2.domain.repository

import com.example.cryptomarketplace2.infrastructure.entity.TickerDto
import kotlinx.coroutines.flow.Flow

interface CryptoMarketPlaceRepository {
    suspend fun getTickersDetailData(symbol: String): Flow<List<TickerDto>>
}