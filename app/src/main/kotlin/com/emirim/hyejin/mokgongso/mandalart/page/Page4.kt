package com.emirim.hyejin.mokgongso.mandalart.page

import android.app.AlertDialog
import android.os.Bundle
import com.emirim.hyejin.mokgongso.model.Message
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.activity_page_4.view.*
import kotlinx.android.synthetic.main.dialog_input2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Page4 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
        lateinit var mandalartSub: Array<TextView>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_page_4, container, false)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        mandalartSub = arrayOf<TextView>(constraintLayout.secondTitle1,constraintLayout.secondTitle2,constraintLayout.secondTitle3, constraintLayout.secondTitle4, constraintLayout.secondTitle5, constraintLayout.secondTitle6, constraintLayout.secondTitle7, constraintLayout.secondTitle8)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 2
        }

        for(i in 0..(mandalartSub.size - 1)) {
            mandalartSub[i].setOnClickListener {
                var inputDialog = LayoutInflater.from(context).inflate(R.layout.dialog_input2, null)
                val mBuilder = AlertDialog.Builder(context)
                        .setView(inputDialog)

                val  mAlertDialog = mBuilder.show()

                inputDialog.cancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                inputDialog.delBtn.setOnClickListener {
                    Mandalart.thirdContent[Mandalart.position - 1][i] = inputDialog.smallEdit.text.toString()
                    mandalartSub[i].text = "3"
                    mandalartSub[i].setBackgroundResource(R.drawable.mandalart_box_1)

                    mAlertDialog.dismiss()
                }
            }
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            for(i in 0..(mandalartSub.size - 1)) {
                mandalartSub[i].text = ""
                mandalartSub[i].setBackgroundResource(R.drawable.mandalart_box_2)
                if(Mandalart.thirdContent[Mandalart.position - 1][i] != null && !(Mandalart.thirdContent[Mandalart.position - 1][i].equals(""))) {
                    mandalartSub[i].text = "2"
                    mandalartSub[i].setBackgroundResource(R.drawable.mandalart_box_1)
                }
            }
        }
    }
}