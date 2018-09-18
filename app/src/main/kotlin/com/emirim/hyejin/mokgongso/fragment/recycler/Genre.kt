package com.emirim.hyejin.mokgongso.fragment.recycler

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class Genre: ExpandableGroup<TitleChild> {
    constructor(title: String, items: List<TitleChild>): super(title, items)
}