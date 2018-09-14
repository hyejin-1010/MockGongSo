package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class MandalTGet (
        @SerializedName("_id") var _id: String,
        @SerializedName("order")var order: Int,
        @SerializedName("smallMandalArt3")var smallMandalArt3: SamllMandalArt,
        @SerializedName("smallMandalArt2")var smallMandalArt2: SamllMandalArt,
        @SerializedName("smallMandalArt1")var smallMandalArt1: SamllMandalArt,
        @SerializedName("achievement")var achievement: Int,
        @SerializedName("middleTitle")var middleTitle: String
)