package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class Middle (
        @SerializedName("title") var title: String,
        //@SerializedName("small") var small: List<String> = List(8, { "" })
        @SerializedName("small") var small: String
)