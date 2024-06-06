package com.cryptomarketplace.domain.entity

import com.cryptomarketplace.domain.model.CoinType

data class TickerData(
    val dailyChangeRelative: Double,
    val lastPrice: Double,
    val coinType: CoinType,
)
