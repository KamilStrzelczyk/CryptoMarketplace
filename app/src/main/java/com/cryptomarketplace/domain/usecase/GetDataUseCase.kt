package com.cryptomarketplace.domain.usecase

import com.cryptomarketplace.domain.entity.TickerData
import com.cryptomarketplace.domain.repository.CryptoMarketPlaceRepository
import com.cryptomarketplace.infrastructure.entity.TickerDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val cryptoMarketPlaceRepository: CryptoMarketPlaceRepository) {
    suspend operator fun invoke(
        symbols: String,
    ): Flow<List<TickerData>> {
        return cryptoMarketPlaceRepository.getTickersDetailData(symbols)
    }
}