package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class GetMandal (
        @SerializedName("title")var title: String,
        @SerializedName("achievement")var achievement: Int,
        @SerializedName("mandal")var mandal: Array<MandalGet>
)