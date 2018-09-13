package com.emirim.hyejin.mokgongso.fragment.recycler

import android.support.v7.widget.CardView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.list_header.view.*

class TitleChildViewHolder: ChildViewHolder {
    var cardLayout: CardView
    var listview: ListView
    //var button: Button

    constructor(view: View): super(view) {
        listview = view.findViewById(R.id.childListview) as ListView
        cardLayout = view.findViewById(R.id.listcardView) as CardView
       /* button = view.findViewById(R.id.btn) as Button

        button.setOnClickListener {
            Log.d("버튼 ","\"${adapterPosition}\"")
        }*/
    }
}