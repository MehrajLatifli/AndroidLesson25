package com.example.androidlesson25.source.api

import androidx.room.Query
import com.example.androidlesson25.models.responses.get.coin.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface IApiManager {


    @Headers( "Content-Type: application/json;charset=UTF-8")

    @GET("economy/coin/exchange-rate")
    suspend fun getAllCoins(
    ): Response<CoinResponse>


}