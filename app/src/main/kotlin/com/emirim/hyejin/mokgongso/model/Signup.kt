package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

class Signup (
        @SerializedName("name") val name: String,
        @SerializedName("email") val email: String,
        @SerializedName("passwd") val passwd: String
)