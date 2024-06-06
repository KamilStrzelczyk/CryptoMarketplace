package com.cryptomarketplace.infrastructure.api

import retrofit2.http.GET
import retrofit2.http.Query

interface TickersApi {

    @GET("tickers")
    suspend fun getTickersData(
        @Query("symbols") symbols: String,
    ) : ArrayList<ArrayList<Any>>
}
