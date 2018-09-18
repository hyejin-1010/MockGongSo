package com.emirim.hyejin.mokgongso.fragment.innerrecyclerview

import com.google.gson.annotations.SerializedName

data class InnterItem (
        @SerializedName("diaryText") var diaryText: String,
        @SerializedName("delCheck")var delCheck: Boolean,
        @SerializedName("token")var token: String
)