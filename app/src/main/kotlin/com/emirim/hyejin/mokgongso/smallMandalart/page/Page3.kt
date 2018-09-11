package com.emirim.hyejin.mokgongso.smallMandalart.page

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.View.TriangleView
import com.emirim.hyejin.mokgongso.smallMandalart.CreateMandalart
import kotlinx.android.synthetic.main.activity_small_page_3.view.*

class Page3 : Fragment() {
    companion object {
        lateinit var mandalartSub: Array<TriangleView>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val constraintLayout = inflater.inflate(R.layout.activity_small_page_3, container, false)

        mandalartSub = arrayOf(constraintLayout.leftTriangle, constraintLayout.topTriangle, constraintLayout.rightTriangle)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 1
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        for(i in 1..(Mandalart.count - 1)) {
            mandalartSub[i - 1].setColor(R.color.white)
        }

        for(i in 1..(Mandalart.count - 1)){
            mandalartSub[i - 1].setOnClickListener {
                Mandalart.position = i
                CreateMandalart.mViewPager.currentItem = 3
            }
        }
    }
}