package com.emirim.hyejin.mokgongso.fragment.innerrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R

class InnerViewHolder: RecyclerView.ViewHolder {
    var mTextView: TextView
    var mCheckBox: CheckBox
    lateinit var innerItem: InnterItem

    constructor(itemView: View): super(itemView) {
        mTextView = itemView.findViewById(R.id.diaryText)
        mCheckBox = itemView.findViewById(R.id.delCheckBox)
    }
}