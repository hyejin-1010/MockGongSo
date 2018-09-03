package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class SignInMessage (
        @SerializedName("token") var token: String
)