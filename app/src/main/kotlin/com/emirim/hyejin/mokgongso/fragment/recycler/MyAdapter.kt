package com.emirim.hyejin.mokgongso.fragment.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.Model.ParentObject
import com.emirim.hyejin.mokgongso.MainActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter: ExpandableRecyclerAdapter<TitleParentViewHolder, TitleChildViewHolder> {
    lateinit var inflater: LayoutInflater
    var context: Context

    constructor(context: Context, parentItemList: List<ParentObject>): super(context, parentItemList) {
        inflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onBindParentViewHolder(p0: TitleParentViewHolder?, p1: Int, p2: Any?) {
        var title: TitleParent = p2 as TitleParent
        p0?.textView1?.text = title.title
        p0?.textView2?.text = title.subTitle
    }

    override fun onCreateChildViewHolder(p0: ViewGroup?): TitleChildViewHolder {
        var view: View = inflater.inflate(R.layout.list_item, p0, false)

        return TitleChildViewHolder(view)
    }

    override fun onCreateParentViewHolder(p0: ViewGroup?): TitleParentViewHolder {
        var view: View = inflater.inflate(R.layout.list_header, p0, false)

        return TitleParentViewHolder(view)
    }

    override fun onBindChildViewHolder(p0: TitleChildViewHolder?, p1: Int, p2: Any?) {
        var title: TitleChild = p2 as TitleChild
        var adapter: ListViewAdapter = ListViewAdapter(context , R.layout.listview, title.data)
        p0?.listview?.adapter = adapter
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        Log.d("onBindViewHolder", "position : ${position}")
    }

    override fun onParentItemClickListener(position: Int) {
        super.onParentItemClickListener(position)

        Log.d("onBindViewHolder", "Parent position : ${position}")
    }

}
