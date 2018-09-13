package com.emirim.hyejin.mokgongso.fragment.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import java.util.zip.Inflater

class ListViewAdapter: BaseAdapter {
    var data: ArrayList<ListViewItem>

    var layout: Int
    var inflater: LayoutInflater
    constructor(context: Context, layout: Int, data: ArrayList<ListViewItem>) {
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.data = data
        this.layout = layout
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if(convertView == null) {
            // convertView = inflater.inflate(layout, parent, false)
        }

        var listViewItem = data[position]

        var text: TextView? = convertView?.findViewById(R.id.textView)
        text?.text = listViewItem.text

        if(convertView != null) {
            return convertView
        } else {
            return inflater.inflate(layout, parent, false)
        }
    }

    override fun getItem(position: Int): Any {
        return data[position].text
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }


}
