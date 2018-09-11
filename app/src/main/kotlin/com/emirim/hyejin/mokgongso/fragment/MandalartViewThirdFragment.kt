package com.emirim.hyejin.mokgongso.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment;
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.emirim.hyejin.mokgongso.model.Message
import com.emirim.hyejin.mokgongso.model.SetLow
import kotlinx.android.synthetic.main.fragment_mandalart.view.*
import kotlinx.android.synthetic.main.fragment_mandalartviewthird.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Clock

class MandalartViewThirdFragment : Fragment() {
    companion object {
        fun newInstance(): MandalartViewThirdFragment {
            return MandalartViewThirdFragment()
        }

        lateinit var containtLayout: View
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containtLayout = inflater?.inflate(R.layout.fragment_mandalartviewthird, container, false)

        containtLayout.wave.progressValue = Mandalart.thirdAchievement[Mandalart.secondSelect][Mandalart.thirdSelect]

        containtLayout.title.text = Mandalart.thirdContent[Mandalart.secondSelect][Mandalart.thirdSelect]
        containtLayout.subTitle.text = Mandalart.thirdAchievement[Mandalart.secondSelect][Mandalart.thirdSelect].toString()


        var mHandler = Handler()

        containtLayout.wave.setOnTouchListener(View.OnTouchListener { v, event ->
            mHandler.postDelayed({
                if(containtLayout.wave.progressValue < 100) {
                    containtLayout.wave.progressValue = containtLayout.wave.progressValue + 1
                    Mandalart.tmpAchievement = containtLayout.wave.progressValue
                    containtLayout.wave.centerTitle = containtLayout.wave.progressValue.toString()

                    /*try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException){
                        e.printStackTrace()
                    }*/
                }
            }, 0)

            return@OnTouchListener true
        })

        MandalartActivity.rightButtonImageView.setImageResource(R.drawable.confirm)

        return containtLayout
    }
}