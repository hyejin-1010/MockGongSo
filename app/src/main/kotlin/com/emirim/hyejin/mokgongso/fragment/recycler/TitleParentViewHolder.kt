package com.emirim.hyejin.mokgongso.fragment.recycler

import android.view.View
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.list_header.view.*

class TitleParentViewHolder: ParentViewHolder {
    lateinit var textView1: TextView
    lateinit var textView2: TextView

    constructor(view: View): super(view) {
        textView1 = view.findViewById(R.id.date) as TextView
        textView2 = view.findViewById(R.id.firstDiary) as TextView
    }
}