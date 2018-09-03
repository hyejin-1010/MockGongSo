package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class Mandal (
        @SerializedName("title") var title: String,
        @SerializedName("mostWork") var mostWork: String,
        @SerializedName("achievement") var achievement: String,
        @SerializedName("startDay") var startDay: String,
        @SerializedName("endDay") var endDay: String,
        @SerializedName("isEnd") var isEnd: Boolean,
        @SerializedName("themaCode") var themaCode: String
)