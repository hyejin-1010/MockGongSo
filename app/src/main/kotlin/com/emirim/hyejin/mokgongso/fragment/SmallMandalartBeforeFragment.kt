package com.emirim.hyejin.mokgongso.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.smallMandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart
import kotlinx.android.synthetic.main.fragment_mandalart.view.*
import kotlinx.android.synthetic.main.fragment_smallmandalart.view.*

class SmallMandalartBeforeFragment : Fragment() {
    companion object {
        fun newInstance(): SmallMandalartBeforeFragment {
            return SmallMandalartBeforeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val containtLayout: View = inflater?.inflate(R.layout.fragment_mandalart, container, false)

        containtLayout.createBtn.setOnClickListener {
            Mandalart.count = 1

            var intent = Intent(activity, CreateMandalart::class.java)
            startActivity(intent)
        }

        return containtLayout
    }
}