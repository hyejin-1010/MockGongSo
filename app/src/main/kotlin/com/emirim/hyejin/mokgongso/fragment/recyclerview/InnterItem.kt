package com.emirim.hyejin.mokgongso.fragment.recyclerview

import com.google.gson.annotations.SerializedName

data class InnterItem (
        @SerializedName("diaryText") var diaryText: String,
        @SerializedName("delCheck")var delCheck: Boolean
)