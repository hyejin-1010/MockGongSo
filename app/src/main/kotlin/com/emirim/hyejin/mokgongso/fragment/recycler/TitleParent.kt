package com.emirim.hyejin.mokgongso.fragment.recycler

import com.bignerdranch.expandablerecyclerview.Model.ParentObject
import java.util.*

class TitleParent: ParentObject {

    lateinit var mChildrenList: ArrayList<TitleChild>
    var _id: UUID
    var title: String
    // var subTitle: String

    constructor(title: String) {
        this.title = title
        // this.subTitle = subTitle
        _id = UUID.randomUUID()
    }

    override fun setChildObjectList(p0: MutableList<Any>?) {
        if(p0 != null) {
            mChildrenList = p0 as ArrayList<TitleChild>
        }
    }

    override fun getChildObjectList(): ArrayList<TitleChild> {
        return mChildrenList
    }

}
