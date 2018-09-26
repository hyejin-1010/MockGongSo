package com.emirim.hyejin.mokgongso.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.fragment_mandalart.view.*

class MandalartFragment : Fragment() {
    companion object {
        fun newInstance(): MandalartFragment {
            return MandalartFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val containtLayout: View = inflater?.inflate(R.layout.fragment_mandalart, container, false)

        containtLayout.createBtn.setOnClickListener {
            var intent = Intent(activity, CreateMandalart::class.java)
            startActivity(intent)
        }

        return containtLayout
    }
}