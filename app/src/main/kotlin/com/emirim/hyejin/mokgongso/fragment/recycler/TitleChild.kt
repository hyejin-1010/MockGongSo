package com.emirim.hyejin.mokgongso.fragment.recycler

import kotlin.collections.ArrayList

class TitleChild{
    lateinit var data: ArrayList<ListViewItem>

    constructor(data: ArrayList<ListViewItem>) {
        this.data = data
    }
}
