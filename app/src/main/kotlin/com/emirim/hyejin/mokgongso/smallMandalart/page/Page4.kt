package com.emirim.hyejin.mokgongso.smallMandalart.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.smallMandalart.CreateMandalart
import kotlinx.android.synthetic.main.activity_small_page_4.view.*

class Page4 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
        lateinit var mandalartSub: Array<EditText>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_small_page_4, container, false)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        mandalartSub = arrayOf<EditText>(constraintLayout.mandalartSub1,constraintLayout.mandalartSub2,constraintLayout.mandalartSub3)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 2

            for(i in 0..2) {
                if(i < Mandalart.thirdCout[Mandalart.position - 1]) {
                    Mandalart.thirdContent[Mandalart.position - 1][i] = mandalartSub[i].text.toString()
                }
            }
        }

        constraintLayout.mandalartAddBtn.setOnClickListener {
            mandalartSub[Mandalart.thirdCout[Mandalart.position - 1]].visibility = View.VISIBLE
            mandalartSub[Mandalart.thirdCout[Mandalart.position - 1]].setText("")
            if(Mandalart.thirdCout[Mandalart.position - 1] == 2)
                constraintLayout.mandalartAddBtn.visibility = View.GONE

            else {
                constraintLayout.rightArrow.visibility = View.GONE

                Mandalart.thirdCout[Mandalart.position - 1]++
            }
        }

        /*constraintLayout.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK) {
                CreateMandalart.mViewPager.currentItem = 2
                Toast.makeText(activity, "있잖아", Toast.LENGTH_LONG).show()

                false
            } else {
                return@OnKeyListener true
            }
        })*/

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            val subTitle: TextView = constraintLayout.findViewById(R.id.mandalartTitle)

            subTitle.text = Mandalart.subMandalartTitle[Mandalart.position - 1]

            for(i in 0..2) {
                mandalartSub[i].visibility = View.GONE

                if(i < Mandalart.thirdCout[Mandalart.position - 1]) {
                    mandalartSub[i].visibility = View.VISIBLE
                    mandalartSub[i].setText(Mandalart.thirdContent[Mandalart.position - 1][i])
                    //mandalartSub[i].setText("s")
                }

                if(Mandalart.thirdCout[Mandalart.position - 1] == 7) {
                    constraintLayout.mandalartAddBtn.visibility = View.GONE
                } else {
                    constraintLayout.mandalartAddBtn.visibility = View.VISIBLE
                }
            }
        }
    }
}