package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class Diary(
        @SerializedName("index")var index: String,
        @SerializedName("token")var token: String
)