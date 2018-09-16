package com.emirim.hyejin.mokgongso.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.Mandalart
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_setting.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {
    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val containtLayout: View = inflater?.inflate(R.layout.fragment_main, container, false)

        containtLayout.mandalartPercent.text = com.emirim.hyejin.mokgongso.mandalart.Mandalart.achievement.toString()

        if(com.emirim.hyejin.mokgongso.mandalart.Mandalart.achievement < 24) {
            containtLayout.mandalartImg.setBackgroundResource(R.drawable.step_1)
        } else if(com.emirim.hyejin.mokgongso.mandalart.Mandalart.achievement < 49) {
            containtLayout.mandalartImg.setBackgroundResource(R.drawable.step_2)
        } else if(com.emirim.hyejin.mokgongso.mandalart.Mandalart.achievement < 49) {
            containtLayout.mandalartImg.setBackgroundResource(R.drawable.step_3)
        } else {
            containtLayout.mandalartImg.setBackgroundResource(R.drawable.step_4)
        }

        Log.d("start main", "${LoginActivity.appData!!.getString("startday", "")}")

        if(LoginActivity.appData!!.getString("startday", "").isNotEmpty() || !(LoginActivity.appData!!.getString("startday", "").equals(""))) {
            val startDay = LoginActivity.appData!!.getString("startday", "")

            if(startDay.equals("")) {
                containtLayout.date.text = "0"
            } else {
                val startDate = SimpleDateFormat("yyyy-MM-dd").parse(startDay)

                val today = Date()

                var diff = today.time - startDate.time
                var diffDays = (diff / (24 * 60 * 60 * 1000)) + 1

                containtLayout.date.text = diffDays.toString()
            }
        } else {
            containtLayout.date.text = "0"
        }

        return containtLayout
    }
}