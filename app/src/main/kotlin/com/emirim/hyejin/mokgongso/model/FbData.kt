package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class FbData (
        @SerializedName("token")var token: String,
        @SerializedName("name")var name: String
)