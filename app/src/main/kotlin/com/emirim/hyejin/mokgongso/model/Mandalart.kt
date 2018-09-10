package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class Mandalart (
    @SerializedName("title") var title: String,
    @SerializedName("token") var token: String,
    @SerializedName("middle") var middle: List<Middle>
)