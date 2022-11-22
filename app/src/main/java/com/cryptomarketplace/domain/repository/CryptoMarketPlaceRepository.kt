package com.cryptomarketplace.domain.repository

import com.cryptomarketplace.domain.entity.TickerData
import com.cryptomarketplace.infrastructure.entity.TickerDto
import kotlinx.coroutines.flow.Flow

interface CryptoMarketPlaceRepository {
    suspend fun getTickersDetailData(symbol: String): Flow<List<TickerData>>
}