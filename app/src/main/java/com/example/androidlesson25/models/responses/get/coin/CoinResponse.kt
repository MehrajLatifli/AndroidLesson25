package com.example.androidlesson25.models.responses.get.coin


import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("creditUsed")
    val creditUsed: Int,
    @SerializedName("data")
    val data: List<CoinData>,
    @SerializedName("endpoint")
    val endpoint: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("messageTR")
    val messageTR: String,
    @SerializedName("rowCount")
    val rowCount: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("systemTime")
    val systemTime: Int
)