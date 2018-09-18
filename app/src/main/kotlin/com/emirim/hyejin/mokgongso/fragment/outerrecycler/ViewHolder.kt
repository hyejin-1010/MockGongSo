package com.emirim.hyejin.mokgongso.fragment.outerrecycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.list_item.view.*

class ViewHolder: RecyclerView.ViewHolder {
    var date: TextView
    var itemRecyclerView: RecyclerView
    var inputDiary: EditText
    var diaryWriteBtn: Button
    var listcardView: LinearLayout
    var priviewText: TextView
    var preview: TextView

    constructor(itemView: View): super(itemView) {
        date = itemView.findViewById(R.id.date) as TextView
        itemRecyclerView = itemView.findViewById(R.id.itemRecyclerView) as RecyclerView
        inputDiary = itemView.findViewById(R.id.inputDiary) as EditText
        diaryWriteBtn = itemView.findViewById(R.id.diaryWriteBtn) as Button
        listcardView = itemView.findViewById(R.id.listcardView) as LinearLayout
        priviewText = itemView.findViewById(R.id.priviewText) as TextView
        preview = itemView.findViewById(R.id.preview) as TextView
    }
}