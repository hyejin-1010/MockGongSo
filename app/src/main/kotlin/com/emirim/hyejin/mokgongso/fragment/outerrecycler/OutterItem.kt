package com.emirim.hyejin.mokgongso.fragment.outerrecycler

import com.emirim.hyejin.mokgongso.fragment.innerrecyclerview.InnterItem
import com.google.gson.annotations.SerializedName

data class OutterItem (
        @SerializedName("diaryText") var diaryText: String,
        @SerializedName("delCheck")var delCheck: Boolean,
        @SerializedName("innerItem")var innerItem: List<InnterItem>
)