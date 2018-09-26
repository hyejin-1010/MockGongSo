package com.emirim.hyejin.mokgongso.mandalart.page

import android.app.AlertDialog
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.activity_page_1.*
import kotlinx.android.synthetic.main.activity_page_2.view.*
import kotlinx.android.synthetic.main.dialog_input.view.*

class Page2 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
        lateinit var secondTitle: Array<TextView>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_page_2, container, false)

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        // Right And Left Button
        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 0
        }
        constraintLayout.rightArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 2
        }

        secondTitle = arrayOf<TextView>(constraintLayout.secondTitle1,constraintLayout.secondTitle2,constraintLayout.secondTitle3, constraintLayout.secondTitle4, constraintLayout.secondTitle5, constraintLayout.secondTitle6, constraintLayout.secondTitle7, constraintLayout.secondTitle8)

        for(i in 0..(secondTitle.size - 1)) {
            secondTitle[i].setOnClickListener {
                var inputDialog = LayoutInflater.from(context).inflate(R.layout.dialog_input, null)
                val mBuilder = AlertDialog.Builder(context)
                        .setView(inputDialog)

                val  mAlertDialog = mBuilder.show()

                inputDialog.cancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                inputDialog.delBtn.setOnClickListener {
                    Mandalart.subMandalartTitle[i] = inputDialog.smallEdit.text.toString()
                    secondTitle[i].text = "2"
                    secondTitle[i].setBackgroundResource(R.drawable.mandalart_box_1)

                    mAlertDialog.dismiss()
                }
            }
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            // 화면에 실제로 보일 때
            for(i in 0..(secondTitle.size - 1)) {
                if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(("")))) {
                    secondTitle[i].text = "2"
                    secondTitle[i].setBackgroundResource(R.drawable.mandalart_box_1)
                }
            }
        } else {
            // preload 될 때 (전 페이지에 있을 때)
        }
    }
}