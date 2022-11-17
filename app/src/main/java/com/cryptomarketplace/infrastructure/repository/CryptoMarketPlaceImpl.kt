package com.example.cryptomarketplace2.infrastructure.repository

import com.example.cryptomarketplace2.domain.repository.CryptoMarketPlaceRepository
import com.example.cryptomarketplace2.infrastructure.api.TickersApi
import com.example.cryptomarketplace2.infrastructure.entity.TickerDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoMarketPlaceImpl @Inject constructor(
    private val api: TickersApi,
) : CryptoMarketPlaceRepository {

    override suspend fun getTickersDetailData(symbols: String): Flow<List<TickerDto>> = flow {
        while (true){
            val result: List<TickerDto> = api.getTickersData(symbols).map {
                parseToTicker(it)
            }
            emit(result)
            delay(5000)
        }
    }

    private fun parseToTicker(data: ArrayList<Any>) = data.run {
        TickerDto(
            symbol = this[0] as String,
            bid = this[1] as Double,
            bidSize = this[2] as Double,
            ask = this[3] as Double,
            askSize = this[4] as Double,
            dailyChange = this[5] as Double,
            dailyChangeRelative = this[6] as Double,
            lastPrice = this[7] as Double,
            volume = this[8] as Double,
            high = this[9] as Double,
            low = this[10] as Double,
        )
    }
}
