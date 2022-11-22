package com.example.cryptomarketplace2.presentation.entity

data class TokenUiModel(
    val symbol: String,
    val lastPrice: Double,
    val dailyChangeRelative: Double,
    val logoIcon: Int,
    val isUp: Boolean,
)
