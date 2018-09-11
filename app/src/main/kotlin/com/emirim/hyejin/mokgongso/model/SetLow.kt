package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class SetLow (
        @SerializedName("token")var token: String,
        @SerializedName("middle")var middle: Int,
        @SerializedName("low")var low: Int,
        @SerializedName("achievement")var achievement: Int
)