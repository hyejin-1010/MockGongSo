package com.emirim.hyejin.mokgongso.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.Model.ParentObject
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.fragment.recycler.*
import kotlinx.android.synthetic.main.fragment_diary.view.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class DiaryFragment : Fragment() {
    companion object {
        fun newInstance(): DiaryFragment {
            return DiaryFragment()
        }

        lateinit var containtLayout: View
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        (containtLayout.recyclerView.adapter as MyAdapter).onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containtLayout = inflater?.inflate(R.layout.fragment_diary, container, false)

        val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(activity)
        //val recyclerViewAdapter = RecyclerViewAdapter("2017년 8월 3일", "-띠용")

        containtLayout.recyclerView.layoutManager = layoutManager
        //containtLayout.recyclerView.adapter = recyclerViewAdapter

        var adapter = MyAdapter(activity as Context, initData())
        adapter.setParentClickableViewAnimationDefaultDuration()
        adapter.setParentAndIconExpandOnClick(true)

        containtLayout.recyclerView.adapter = adapter

        return containtLayout
    }

    private fun initData(): List<ParentObject> {
        // var titleCreator = TitleCreator.get(activity as Context)
        var titles: ArrayList<TitleParent> = ArrayList()
        titles.add(TitleParent("인", "생"))
        titles.add(TitleParent("으", "아"))

        var parentObject: ArrayList<ParentObject> = ArrayList()

        for(title in titles) {
            var item: ArrayList<ListViewItem> = ArrayList()
            item.add(ListViewItem("자고"))
            item.add(ListViewItem("싶다"))
            item.add(ListViewItem("정말"))

            var childList: ArrayList<TitleChild> = ArrayList()
            childList.add(TitleChild(item))

            title.mChildrenList = childList

            parentObject.add(title)
        }

        return parentObject
    }
}