package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class GetDiary(
        @SerializedName("re")var re: Array<DRe>
)