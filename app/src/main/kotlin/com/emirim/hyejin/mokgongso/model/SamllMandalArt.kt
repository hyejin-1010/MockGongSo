package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class SamllMandalArt (
        @SerializedName("achievement") var achievement: String,
        @SerializedName("title")var title: String
)