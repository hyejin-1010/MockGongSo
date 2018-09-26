package com.emirim.hyejin.mokgongso.fragment

import android.os.Bundle
import android.support.v4.app.Fragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.View.TriangleView
import com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart
import kotlinx.android.synthetic.main.fragment_smallmandalart.view.*

class SmallMandalartFragment : Fragment() {
    companion object {
        fun newInstance(): SmallMandalartFragment {
            return SmallMandalartFragment()
        }

        lateinit var secondSelector: Array<TriangleView>
        lateinit var constraintLayout: View
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater?.inflate(R.layout.fragment_smallmandalart, container, false)

        constraintLayout.title.text = Mandalart.title

        secondSelector = arrayOf(constraintLayout.topTriangle, constraintLayout.leftTriangle, constraintLayout.rightTriangle)

        if(Mandalart.viewer == 0) {
            secondInit()
        } else {
            thirdInit()
        }

        for(i in 0..2) {
            secondSelector[i].setOnClickListener {
                if(Mandalart.viewer == 0) {
                    secondClick(i)
                } else if(Mandalart.viewer == 1) {
                    thirdClick(i)
                }
            }
        }

        /*constraintLayout.centerTriangle.setOnClickListener {
            if(Mandalart.viewer == 1) {
                Mandalart.thirdSelect = -1
                Mandalart.viewer = 0
                secondInit()
            }
        }*/

        return constraintLayout
    }

    fun secondInit() {
        for (i in 0..2) {
            if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                secondSelector[i].setColor(R.color.white)
            } else {
                secondSelector[i].setColor(R.color.colorPrimary)
            }
        }
    }

    fun thirdInit() {
        for (j in 0..2) {
            secondSelector[j].setColor(R.color.colorPrimaryDark)
        }

        for(j in 0..2) {
            if(Mandalart.thirdContent[Mandalart.secondSelect][j] != null && !(Mandalart.thirdContent[Mandalart.secondSelect][j].equals(""))) {
                secondSelector[j].setColor(R.color.white)
            }
        }
    }

    fun secondClick(i: Int) { // 4
        if(Mandalart.secondSelect == i) {
            Mandalart.viewer = 1

            /*for (j in 0..(Mandalart.count - 2)) {
                secondSelector[j].setColor(R.color.colorPrimaryDark)
            }

            for(j in 0..(Mandalart.thirdCout[Mandalart.secondSelect] - 1)) {
                secondSelector[i].setColor(R.color.white)
            }*/

            thirdInit()
        } else {
            if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                if(Mandalart.secondSelect != -1) {
                    secondSelector[Mandalart.secondSelect].setColor(R.color.white)
                }

                secondSelector[i].setColor(R.color.colorAccent)
                Mandalart.secondSelect = i
                constraintLayout.title.text = Mandalart.subMandalartTitle[i]
            }
        }
    }

    fun thirdClick(i: Int) {
        if(Mandalart.thirdSelect == i) {
            // 이동
        } else if(Mandalart.thirdSelect != -1) {
            if(Mandalart.thirdContent[Mandalart.secondSelect][i] != null && !(Mandalart.thirdContent[Mandalart.secondSelect][i].equals(""))) {
                secondSelector[Mandalart.thirdSelect].setColor(R.color.white)
            }
        }

        if(Mandalart.thirdContent[Mandalart.secondSelect][i] != null && !(Mandalart.thirdContent[Mandalart.secondSelect][i].equals(""))) {
            secondSelector[i].setColor(R.color.colorAccent)
            Mandalart.thirdSelect = i
            constraintLayout.title.text = Mandalart.thirdContent[Mandalart.secondSelect][i]
        }
    }
}