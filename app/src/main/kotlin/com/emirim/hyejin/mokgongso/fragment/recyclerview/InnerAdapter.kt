package com.emirim.hyejin.mokgongso.fragment.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.emirim.hyejin.mokgongso.R

class InnerAdapter: RecyclerView.Adapter<InnerViewHolder> {
    var list: List<InnterItem>
    var context: Context

    constructor(list: List<InnterItem>, ctx: Context) {
        this.list = list
        context = ctx
    }

    // Create new views ( 레이아웃 관리자가 호출 )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        var v: View = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_item, parent, false)

        var innerViewHolder = InnerViewHolder(v)

        return innerViewHolder
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val pos = position

        holder.mTextView.setText(list[position].diaryText)
        holder.mCheckBox.isChecked = list[position].delCheck
        holder.mCheckBox.setTag(list[position])

        holder.mCheckBox.setOnClickListener { v ->
            var checkbox: CheckBox = v as CheckBox

            var inner: InnterItem = checkbox.getTag() as InnterItem

            inner.delCheck = checkbox.isChecked
            list[pos].delCheck = checkbox.isChecked
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getInnerList(): List<InnterItem> {
        return list
    }
}