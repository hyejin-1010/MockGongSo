package com.emirim.hyejin.mokgongso.fragment.recycler

import com.emirim.hyejin.mokgongso.fragment.recyclerview.InnterItem
import kotlin.collections.ArrayList

class TitleChild{
    lateinit var innerList: List<InnterItem>

    constructor(innerList: List<InnterItem>) {
        this.innerList = innerList
    }
}
