package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class DRe(
        @SerializedName("date")var date: String,
        @SerializedName("diary")var diary: Array<Diary>
)