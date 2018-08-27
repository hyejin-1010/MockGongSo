package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

class Signin (
        @SerializedName("email") val email: String,
        @SerializedName("passwd") val passwd: String
)