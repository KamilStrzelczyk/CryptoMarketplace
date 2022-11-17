package com.example.cryptomarketplace2.domain.usecase

import com.example.cryptomarketplace2.domain.repository.CryptoMarketPlaceRepository
import com.example.cryptomarketplace2.infrastructure.entity.TickerDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val cryptoMarketPlaceRepository: CryptoMarketPlaceRepository) {
    suspend operator fun invoke(
        symbols: String,
    ): Flow<List<TickerDto>> {
        return cryptoMarketPlaceRepository.getTickersDetailData(symbols)
    }
}