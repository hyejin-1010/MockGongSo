package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class DelDiary (
        @SerializedName("token") var token: String,
        @SerializedName("diaryToken") var diaryToken: List<String>
)