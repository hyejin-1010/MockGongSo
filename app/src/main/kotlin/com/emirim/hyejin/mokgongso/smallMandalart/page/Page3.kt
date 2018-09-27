package com.emirim.hyejin.mokgongso.smallMandalart.page

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.View.TriangleView
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.model.MandalChk
import com.emirim.hyejin.mokgongso.model.Message
import com.emirim.hyejin.mokgongso.model.TMiddle
import com.emirim.hyejin.mokgongso.model.TRe
import com.emirim.hyejin.mokgongso.smallMandalart.CreateMandalart
import kotlinx.android.synthetic.main.activity_small_page_3.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Page3 : Fragment() {
    companion object {
        lateinit var mandalartSub: Array<TriangleView>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val constraintLayout = inflater.inflate(R.layout.activity_small_page_3, container, false)

        mandalartSub = arrayOf(constraintLayout.topTriangle, constraintLayout.leftTriangle, constraintLayout.rightTriangle)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 1
        }

        constraintLayout.createButton.setOnClickListener {
            val middle: ArrayList<TMiddle> = ArrayList()

            for(i in 1..3) {
                middle.add(TMiddle(Mandalart.subMandalartTitle[i - 1], Mandalart.thirdContent[i - 1].toList()))
            }

            val date = Date()
            val sdf = SimpleDateFormat("yyyy-mm-dd")

            com.emirim.hyejin.mokgongso.Mandalart.tMake = com.emirim.hyejin.mokgongso.model.TMake(LoginActivity.appData!!.getString("ID", ""), Mandalart.title.toString(), sdf.format(date).toString(), middle)

            val call: Call<Message> = APIRequestManager.getInstance().requestServer().tmake(com.emirim.hyejin.mokgongso.Mandalart.tMake)

            call.enqueue(object: Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    when(response.code()) {
                        200 -> {
                            Log.d("Tmake", "성공 ${middle.toString()}")
                        }
                        500 -> {
                            Log.d("Tmake", "실패")
                        }
                    }
                }
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    Log.e("Tmake", "실패: " + t.message)
                    t.printStackTrace()
                }
            })

            com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))

            val call2: Call<TRe> = APIRequestManager.getInstance().requestServer().getTMandalChk(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

            call2.enqueue(object: Callback<TRe> {
                override fun onResponse(call: Call<TRe>, response: Response<TRe>) {
                    when(response.code()) {
                        200 -> {
                            val tRe: TRe = response.body() as TRe
                            com.emirim.hyejin.mokgongso.Mandalart.tRe = tRe
                            Log.d("TMandal", response.body().toString())
                            MandalartActivity.smallBoolean = true
                        }
                        401 -> {
                            Log.d("TMandal", "401")

                        }
                        404 -> {
                            Log.d("TMandal", "404")

                        }
                    }
                }
                override fun onFailure(call: Call<TRe>, t: Throwable) {
                    Log.e("TMandal", "에러: " + t.message)
                    t.printStackTrace()
                }
            })

            MandalartActivity.rightButtonImageView.setImageResource(R.drawable.trash)

            activity?.finish()
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            for(i in 0..2) {
                if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                    mandalartSub[i].setColor(R.color.white)
                } else {
                    mandalartSub[i].setColor(R.color.colorPrimary)
                }
            }

            for (i in 1..3) {
                if(Mandalart.subMandalartTitle[i - 1] != null && !(Mandalart.subMandalartTitle[i - 1].equals(""))) {
                    mandalartSub[i - 1].setOnClickListener {
                        Mandalart.position = i
                        CreateMandalart.mViewPager.currentItem = 3
                    }
                }
            }
        }
    }
}