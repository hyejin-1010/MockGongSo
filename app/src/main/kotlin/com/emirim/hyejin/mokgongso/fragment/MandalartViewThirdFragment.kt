package com.emirim.hyejin.mokgongso.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment;
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.fragment_mandalart.view.*
import kotlinx.android.synthetic.main.fragment_mandalartviewthird.view.*
import java.time.Clock

class MandalartViewThirdFragment : Fragment() {
    companion object {
        fun newInstance(): MandalartViewThirdFragment {
            return MandalartViewThirdFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val containtLayout: View = inflater?.inflate(R.layout.fragment_mandalartviewthird, container, false)

        containtLayout.wave.progressValue = 0

        var mHandler = Handler()

        containtLayout.wave.setOnTouchListener(View.OnTouchListener { v, event ->
            mHandler.postDelayed({
                if(containtLayout.wave.progressValue < 100) {
                    containtLayout.wave.progressValue = containtLayout.wave.progressValue + 5
                    containtLayout.wave.centerTitle = containtLayout.wave.progressValue.toString()
                }
            }, 1000)

            return@OnTouchListener true
        })

        MandalartActivity.rightButtonImageView.setImageResource(R.drawable.confirm)

        MandalartActivity.rightButtonImageView.setOnClickListener {
            if(Mandalart.viewer == 2){

            } else {

            }
        }

        return containtLayout
    }
}