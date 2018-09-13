package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class AddDay (
        @SerializedName("token") var token: String,
        @SerializedName("startDay") var startDay: String
)