package com.emirim.hyejin.mokgongso.smallMandalart.page

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.View.TriangleView
import com.emirim.hyejin.mokgongso.smallMandalart.CreateMandalart
import kotlinx.android.synthetic.main.activity_small_page_4.view.*
import kotlinx.android.synthetic.main.dialog_input2.view.*

class Page4 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
        lateinit var mandalartSub: Array<TriangleView>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_small_page_4, container, false)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        mandalartSub = arrayOf<TriangleView>(constraintLayout.topTriangle,constraintLayout.leftTriangle,constraintLayout.rightTriangle)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 2
        }

        for(i in 0..2) {
            mandalartSub[i].setOnClickListener {
                if(Mandalart.thirdContent[Mandalart.position - 1][i] == null || Mandalart.thirdContent[Mandalart.position - 1][i].equals("")) {
                    mandalartSub[i].setColor(R.color.white)

                    var inputDialog = LayoutInflater.from(context).inflate(R.layout.dialog_input2, null)
                    val mBuilder = AlertDialog.Builder(context)
                            .setView(inputDialog)

                    val mAlertDialog = mBuilder.show()

                    inputDialog.cancel.setOnClickListener {
                        Mandalart.thirdContent[Mandalart.position - 1][i] = ""
                        mandalartSub[i].setColor(R.color.colorPrimary)

                        mAlertDialog.dismiss()

                        Mandalart.thirdContent[Mandalart.position - 1][i] = ""
                        mandalartSub[i].setColor(R.color.colorPrimary)
                    }
                    inputDialog.delBtn.setOnClickListener {
                        Mandalart.thirdContent[Mandalart.position - 1][i] = inputDialog.smallEdit.text.toString()
                        mandalartSub[i].setColor(R.color.white)

                        mAlertDialog.dismiss()

                        Mandalart.thirdContent[Mandalart.position - 1][i] = inputDialog.smallEdit.text.toString()
                        mandalartSub[i].setColor(R.color.white)
                    }

                    mAlertDialog.setOnDismissListener {
                        for (i in 0..2) {
                            if (Mandalart.thirdContent[Mandalart.position - 1][i] != null && !(Mandalart.thirdContent[Mandalart.position - 1][i].equals(""))) {
                                mandalartSub[i].setColor(R.color.white)
                            } else {
                                mandalartSub[i].setColor(R.color.colorPrimary)
                            }
                        }
                    }
                } else {
                    Mandalart.thirdContent[Mandalart.position - 1][i] = ""
                    mandalartSub[i].setColor(R.color.colorPrimary)

                    for (i in 0..2) {
                        if (Mandalart.thirdContent[Mandalart.position - 1][i] != null && !(Mandalart.thirdContent[Mandalart.position - 1][i].equals(""))) {
                            mandalartSub[i].setColor(R.color.white)
                        } else {
                            mandalartSub[i].setColor(R.color.colorPrimary)
                        }
                    }
                }
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
            for(i in 0..2) {
                if(Mandalart.thirdContent[Mandalart.position - 1][i] != null && !(Mandalart.thirdContent[Mandalart.position - 1][i].equals(""))) {
                    mandalartSub[i].setColor(R.color.white)
                }
            }
        }
    }
}