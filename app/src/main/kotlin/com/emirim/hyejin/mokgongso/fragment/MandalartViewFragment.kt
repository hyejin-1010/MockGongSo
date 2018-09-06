package com.emirim.hyejin.mokgongso.fragment

import android.os.Bundle
import android.support.v4.app.Fragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.fragment_mandalartview.view.*

class MandalartViewFragment : Fragment() {
    companion object {
        fun newInstance(): MandalartViewFragment {
            return MandalartViewFragment()
        }

        lateinit var secondSelector: Array<TextView>
        lateinit var constraintLayout: View
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater?.inflate(R.layout.fragment_mandalartview, container, false)

        secondSelector = arrayOf(constraintLayout.secondTitle1, constraintLayout.secondTitle2, constraintLayout.secondTitle3, constraintLayout.secondTitle4, constraintLayout.secondTitle5, constraintLayout.secondTitle6, constraintLayout.secondTitle7, constraintLayout.secondTitle8)

        for (i in 0..(Mandalart.count - 2)) {
            secondSelector[i].setBackgroundResource(R.drawable.mandalart_box_1)
        }

        for(i in 0..(Mandalart.count - 2)) {
            secondSelector[i].setOnClickListener {
                if(Mandalart.viewer == 0) {
                    secondClick(i)
                } else if(Mandalart.viewer == 1) {
                    thirdClick(i)
                }
            }
        }

        return constraintLayout
    }

    fun secondClick(i: Int) {
        if(Mandalart.secondSelect == i) {
            Mandalart.viewer = 1
        } else {
            if(Mandalart.secondSelect != -1) {
                secondSelector[Mandalart.secondSelect].setBackgroundResource(R.drawable.mandalart_box_1)
            }

            secondSelector[i].setBackgroundResource(R.drawable.mandalart_box_3)
            Mandalart.secondSelect = i
            constraintLayout.title.text = Mandalart.subMandalartTitle[i]
            constraintLayout.subTitle.text = Mandalart.title
        }
    }

    fun thirdClick(i: Int) {
        if(Mandalart.thirdSelect != -1) {
            secondSelector[Mandalart.thirdSelect].setBackgroundResource(R.drawable.mandalart_box_1)
        }

        secondSelector[i].setBackgroundResource(R.drawable.mandalart_box_3)
        Mandalart.thirdSelect = i
        constraintLayout.title.text = Mandalart.thirdContent[Mandalart.secondSelect][i]
        constraintLayout.subTitle.text = Mandalart.subMandalartTitle[Mandalart.secondSelect]
    }
}