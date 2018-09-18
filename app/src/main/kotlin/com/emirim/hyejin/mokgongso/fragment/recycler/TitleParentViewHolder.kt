package com.emirim.hyejin.mokgongso.fragment.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

class TitleParentViewHolder: RecyclerView.ViewHolder {
    var textView1: TextView
    // var itemRecyclerView: RecyclerView
    var cardView: LinearLayout

    constructor(view: View): super(view) {
        textView1 = view.findViewById(R.id.date) as TextView
        // itemRecyclerView = view.findViewById(R.id.itemRecyclerView) as RecyclerView
        cardView = view.findViewById(R.id.cardView) as LinearLayout
    }
}