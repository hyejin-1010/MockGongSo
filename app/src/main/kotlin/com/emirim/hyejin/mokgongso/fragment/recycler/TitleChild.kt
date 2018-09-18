package com.emirim.hyejin.mokgongso.fragment.recycler

import com.emirim.hyejin.mokgongso.fragment.innerrecyclerview.InnterItem
import android.os.Parcel
import android.os.Parcelable

/*
class TitleChild: Parcelable{
    lateinit var innerList: List<InnterItem>

    constructor(innerList: List<InnterItem>) {
        this.innerList = innerList
    }
}
*/


data class TitleChild(var innerList: List<InnterItem>): Parcelable {
    constructor(parcel: Parcel):this(arrayListOf<InnterItem>().apply {
        parcel.readArrayList(InnterItem::class.java.classLoader)
    })

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TitleChild> {
        override fun createFromParcel(parcel: Parcel): TitleChild {
            return TitleChild(parcel)
        }

        override fun newArray(size: Int): Array<TitleChild?> {
            return arrayOfNulls(size)
        }
    }
}