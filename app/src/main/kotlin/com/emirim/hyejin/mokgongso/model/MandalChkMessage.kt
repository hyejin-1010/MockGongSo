package com.emirim.hyejin.mokgongso.model

import com.google.gson.annotations.SerializedName

data class MandalChkMessage (
        @SerializedName("mandal") var mandal: Mandal
)