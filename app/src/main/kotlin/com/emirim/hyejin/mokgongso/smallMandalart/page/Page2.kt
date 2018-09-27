package com.emirim.hyejin.mokgongso.smallMandalart.page

import android.app.AlertDialog
import android.os.Bundle
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
import com.emirim.hyejin.mokgongso.View.TriangleView
import com.emirim.hyejin.mokgongso.smallMandalart.CreateMandalart
import kotlinx.android.synthetic.main.activity_small_page_2.view.*
import kotlinx.android.synthetic.main.dialog_input.view.*

class Page2 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
        lateinit var mandalartSub: Array<TriangleView>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_small_page_2, container, false)

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 0
        }

        mandalartSub = arrayOf<TriangleView>(constraintLayout.topTriangle,constraintLayout.leftTriangle,constraintLayout.rightTriangle)

        constraintLayout.rightArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 2
        }

        for(i in 0..2) {
            mandalartSub[i].setOnClickListener {
                if(Mandalart.subMandalartTitle[i] == null || Mandalart.subMandalartTitle[i].equals("")) {
                    var inputDialog = LayoutInflater.from(context).inflate(R.layout.dialog_input, null)
                    val mBuilder = AlertDialog.Builder(context)
                            .setView(inputDialog)

                    val mAlertDialog = mBuilder.show()

                    mandalartSub[i].setColor(R.color.white)

                    inputDialog.cancel.setOnClickListener {
                        Mandalart.subMandalartTitle[i] = ""
                        mandalartSub[i].setColor(R.color.colorPrimary)

                        mAlertDialog.dismiss()

                        Mandalart.subMandalartTitle[i] = ""
                        mandalartSub[i].setColor(R.color.colorPrimary)
                    }
                    inputDialog.delBtn.setOnClickListener {
                        Mandalart.subMandalartTitle[i] = inputDialog.smallEdit.text.toString()
                        mandalartSub[i].setColor(R.color.white)

                        mAlertDialog.dismiss()

                        Mandalart.subMandalartTitle[i] = inputDialog.smallEdit.text.toString()
                        mandalartSub[i].setColor(R.color.white)
                    }

                    mAlertDialog.setOnDismissListener {
                        for(i in 0..2) {
                            if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                                mandalartSub[i].setColor(R.color.white)
                            } else {
                                mandalartSub[i].setColor(R.color.colorPrimary)
                            }
                        }
                    }
                } else {
                    Mandalart.subMandalartTitle[i] = ""
                    mandalartSub[i].setColor(R.color.colorPrimary)

                    for(j in 0..2) {
                        if(Mandalart.subMandalartTitle[j] != null && !(Mandalart.subMandalartTitle[j].equals(""))) {
                            mandalartSub[j].setColor(R.color.white)
                        } else {
                            mandalartSub[j].setColor(R.color.colorPrimary)
                        }
                    }

                    for(j in 0..2) {
                        Mandalart.thirdContent[i][j] = ""
                    }
                }
            }
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            // 화면에 실제로 보일 때
            for(i in 0..2) {
                if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                    mandalartSub[i].setColor(R.color.white)
                } else {
                    mandalartSub[i].setColor(R.color.colorPrimary)
                }
            }
        } else {
            // preload 될 때 (전 페이지에 있을 때)
        }
    }
}