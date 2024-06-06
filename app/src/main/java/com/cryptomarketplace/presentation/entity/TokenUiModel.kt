package com.cryptomarketplace.presentation.entity

data class TokenUiModel(
    val symbol: String,
    val lastPrice: String,
    val dailyChangeRelative: String,
    val logoIcon: Int,
    val isUp: Boolean,
)
