package com.emirim.hyejin.mokgongso.mandalart.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.emirim.hyejin.mokgongso.model.Message
import com.emirim.hyejin.mokgongso.model.Middle
import kotlinx.android.synthetic.main.activity_page_3.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Page3 : Fragment() {
    companion object {
        lateinit var mandalartSub: Array<TextView>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val constraintLayout = inflater.inflate(R.layout.activity_page_3, container, false)

        mandalartSub = arrayOf(constraintLayout.secondTitle1, constraintLayout.secondTitle2, constraintLayout.secondTitle3, constraintLayout.secondTitle4, constraintLayout.secondTitle5, constraintLayout.secondTitle6, constraintLayout.secondTitle7,constraintLayout.secondTitle8)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 1
        }

        constraintLayout.rightArrow.setOnClickListener {
            // var test: Array<String> = Array<String>(8, {""})
            // var middleArray: Array<Middle> = Array<Middle>(8, {Middle("", test)})
            var middle: ArrayList<Middle> = ArrayList()

            for(i in 1..(Mandalart.count - 1)) {
                middle.add(Middle(Mandalart.subMandalartTitle[i - 1], Mandalart.thirdContent[i - 1].toList().toString()))
            }

            // middleArray = middle.toArray(middleArray)

            com.emirim.hyejin.mokgongso.Mandalart.makemandalart = com.emirim.hyejin.mokgongso.model.Mandalart(Mandalart.title.toString(), middle)

            for(i in 1..middle.size) {
                Log.d("Page3", com.emirim.hyejin.mokgongso.Mandalart.makemandalart.middle[i - 1].title)
                Log.d("Page3", com.emirim.hyejin.mokgongso.Mandalart.makemandalart.middle[i - 1].small)
            }

            var call: Call<Message> = APIRequestManager.getInstance().requestServer().make(com.emirim.hyejin.mokgongso.Mandalart.makemandalart)

            call.enqueue(object: Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    when(response.code()) {
                        200 -> {
                            Log.d("Page4", "성공")
                        }
                        404 -> {
                            Log.d("Page4", "반성공")
                        }
                    }
                }

                override fun onFailure(call: Call<Message>, t: Throwable) {
                    Log.e("Page4", "실패: " + t.message)
                    t.printStackTrace()
                }
            })
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        for(i in 1..(Mandalart.count - 1)) {
            mandalartSub[i - 1].setText("2")
            mandalartSub[i - 1].setBackgroundResource(R.drawable.mandalart_box_1)
        }

        for(i in 1..(Mandalart.count - 1)){
            mandalartSub[i - 1].setOnClickListener {
                Mandalart.position = i
                CreateMandalart.mViewPager.currentItem = 3
            }
        }
    }
}