package com.emirim.hyejin.mokgongso.mandalart.page

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.activity_page_4.view.*

class Page4 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_page_4, container, false)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 2
        }

        var mandalartSub = arrayOf<EditText>(constraintLayout.mandalartSub1,constraintLayout.mandalartSub2,constraintLayout.mandalartSub3, constraintLayout.mandalartSub4, constraintLayout.mandalartSub5, constraintLayout.mandalartSub6, constraintLayout.mandalartSub7, constraintLayout.mandalartSub8)

        constraintLayout.mandalartAddBtn.setOnClickListener {
            mandalartSub[Mandalart.thirdCout[Mandalart.position - 1]].visibility = View.VISIBLE
            if(Mandalart.count == 7)
                constraintLayout.mandalartAddBtn.visibility = View.GONE

            constraintLayout.rightArrow.visibility = View.GONE

            Mandalart.thirdCout[Mandalart.position - 1] ++
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            val subTitle: TextView = constraintLayout.findViewById(R.id.mandalartTitle)

            subTitle.text = Mandalart.subMandalartTitle[Mandalart.position - 1]
        }
    }
}