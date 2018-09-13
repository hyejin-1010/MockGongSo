package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class TMiddle(@SerializedName("title") var title: String, @SerializedName("small") var small: List<String>)