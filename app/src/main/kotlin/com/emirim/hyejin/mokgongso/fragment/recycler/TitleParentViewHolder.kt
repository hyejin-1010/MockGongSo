package com.emirim.hyejin.mokgongso.fragment.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.list_header.view.*

class TitleParentViewHolder: ParentViewHolder {
    var textView1: TextView
    // var itemRecyclerView: RecyclerView
    var cardView: LinearLayout

    constructor(view: View): super(view) {
        textView1 = view.findViewById(R.id.date) as TextView
        // itemRecyclerView = view.findViewById(R.id.itemRecyclerView) as RecyclerView
        cardView = view.findViewById(R.id.cardView) as LinearLayout
    }
}