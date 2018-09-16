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

class ReportFragment : Fragment() {
    companion object {
        fun newInstance(): ReportFragment {
            return ReportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val containtLayout: View = inflater?.inflate(R.layout.fragment_report, container, false)

        return containtLayout
    }
}