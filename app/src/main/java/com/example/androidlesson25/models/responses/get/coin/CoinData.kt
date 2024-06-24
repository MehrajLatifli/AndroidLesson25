package com.example.androidlesson25.models.responses.get.coin


import com.google.gson.annotations.SerializedName

data class CoinData(
    @SerializedName("buying")
    val buying: Double,
    @SerializedName("Ccode")
    val ccode: String,
    @SerializedName("changeRate")
    val changeRate: Double,
    @SerializedName("code")
    val code: String,
    @SerializedName("dayMax")
    val dayMax: Double,
    @SerializedName("dayMin")
    val dayMin: Double,
    @SerializedName("FullName")
    val fullName: String,
    @SerializedName("lastupdate")
    val lastupdate: String,
    @SerializedName("latest")
    val latest: Double,
    @SerializedName("Mcode")
    val mcode: String,
    @SerializedName("selling")
    val selling: Double,
    @SerializedName("ShortName")
    val shortName: String,
    @SerializedName("volume")
    val volume: String
)