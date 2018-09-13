package com.emirim.hyejin.mokgongso.fragment.recycler

import android.support.v7.widget.CardView
import android.util.Log
import android.view.View
import android.widget.*
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.list_header.view.*

class TitleChildViewHolder: ChildViewHolder {
    var cardLayout: LinearLayout
    var listview: ListView
    var diaryWriteBtn: Button

    constructor(view: View): super(view) {
        listview = view.findViewById(R.id.childListview) as ListView
        cardLayout = view.findViewById(R.id.listcardView) as LinearLayout
        diaryWriteBtn = view.findViewById(R.id.diaryWriteBtn) as Button

        diaryWriteBtn.setOnClickListener {
            Log.d("버튼 ","\"${position}\"")
        }
    }
}