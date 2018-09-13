package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class TMake (
    @SerializedName("token") var token: String,
    @SerializedName("title") var title: String,
    @SerializedName("startDay ") var startDay : String,
    @SerializedName("middle") var middle: List<TMiddle>
)