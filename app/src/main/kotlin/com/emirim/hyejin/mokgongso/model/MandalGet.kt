package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class MandalGet (
        @SerializedName("_id") var _id: String,
        @SerializedName("order")var order: Int,
        @SerializedName("smallMandalArt8")var smallMandalArt8: SamllMandalArt,
        @SerializedName("smallMandalArt7")var smallMandalArt7: SamllMandalArt,
        @SerializedName("smallMandalArt6")var smallMandalArt6: SamllMandalArt,
        @SerializedName("smallMandalArt5")var smallMandalArt5: SamllMandalArt,
        @SerializedName("smallMandalArt4")var smallMandalArt4: SamllMandalArt,
        @SerializedName("smallMandalArt3")var smallMandalArt3: SamllMandalArt,
        @SerializedName("smallMandalArt2")var smallMandalArt2: SamllMandalArt,
        @SerializedName("smallMandalArt1")var smallMandalArt1: SamllMandalArt,
        @SerializedName("achievement")var achievement: Int,
        @SerializedName("middleTitle")var middleTitle: String
)