package com.uteev.convertorusdtobtc.domain.pojo.coininfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo (
    @SerializedName("Id")
    @Expose
    val id: String,

    @SerializedName("Name")
    @Expose
    val name: String,

    @SerializedName("FullName")
    @Expose
    val fullName: String,

    @SerializedName("ImageUrl")
    @Expose
    val imageURL: String
)
