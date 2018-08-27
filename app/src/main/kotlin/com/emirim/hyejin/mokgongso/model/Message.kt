package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class Message (
    @SerializedName("message") var message: String
)