package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class AddDiary (
        @SerializedName("token") var token: String,
        @SerializedName("date") var date: String,
        @SerializedName("index") var index: String
)