package com.emirim.hyejin.mokgongso.mandalart.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.emirim.hyejin.mokgongso.model.*
import kotlinx.android.synthetic.main.activity_page_3.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

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

        constraintLayout.createButton.setOnClickListener {
            val middle: ArrayList<Middle> = ArrayList()
            var first = false

            for(i in 1..8) {
                middle.add(Middle(Mandalart.subMandalartTitle[i - 1], Mandalart.thirdContent[i - 1].toList()))
            }

            var call3: Call<Re> = APIRequestManager.getInstance().requestServer().getMandal(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

            call3.enqueue(object: Callback<Re> {
                override fun onResponse(call3: Call<Re>, response: Response<Re>) {
                    when(response.code()) {
                        200 -> {
                            first = false
                        }
                        401 -> {
                            first = true
                        }
                        404 -> {
                            first = true
                        }
                    }
                }
                override fun onFailure(call3: Call<Re>, t: Throwable) {
                    Log.e("ServerMandal", "에러: " + t.message)
                    t.printStackTrace()
                }
            })

            com.emirim.hyejin.mokgongso.Mandalart.makemandalart = com.emirim.hyejin.mokgongso.model.Mandalart(Mandalart.title.toString(), LoginActivity.appData!!.getString("ID", ""), middle)

            for(i in 1..middle.size) {
                Log.d("Page3", com.emirim.hyejin.mokgongso.Mandalart.makemandalart.middle[i - 1].title)
                Log.d("Page3", com.emirim.hyejin.mokgongso.Mandalart.makemandalart.middle[i - 1].small.toList().toString())
            }

            val call: Call<Message> = APIRequestManager.getInstance().requestServer().make(com.emirim.hyejin.mokgongso.Mandalart.makemandalart)

            call.enqueue(object: Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    when(response.code()) {
                        200 -> {
                            Log.d("Page4", "성공")

                            if(first) {
                                val date = Date()
                                val sdf = SimpleDateFormat("yyyy-MM-dd")

                                MandalartActivity.mandalBoolean = true

                                com.emirim.hyejin.mokgongso.Mandalart.addDay = AddDay(LoginActivity.appData!!.getString("ID", ""), sdf.format(date).toString())

                                var call4: Call<Message> = APIRequestManager.getInstance().requestServer().addDay(com.emirim.hyejin.mokgongso.Mandalart.addDay)

                                call4.enqueue(object: Callback<Message>{
                                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                                        when(response.code()) {
                                            200 -> {
                                                Log.d("Add Day", sdf.format(date).toString())
                                                val editor = LoginActivity.appData!!.edit()
                                                editor.putString("startday", sdf.format(date).toString())
                                            }
                                            500 -> {
                                                Log.d("Add Day", "500")
                                            }
                                        }
                                    }

                                    override fun onFailure(call: Call<Message>, t: Throwable) {
                                        Log.e("Page4", "실패: " + t.message)
                                        t.printStackTrace()
                                    }
                                })

                                // auto
                                var token: String = LoginActivity.appData!!.getString("ID", "")
                                com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(token)

                                var callAuto: Call<SignInMessage> = APIRequestManager.getInstance().requestServer().auto(token)

                                callAuto.enqueue(object: Callback<SignInMessage> {
                                    override fun onResponse(call: Call<SignInMessage>, response: Response<SignInMessage>) {
                                        when(response.code()) {
                                            200 -> {
                                                val message: SignInMessage = response.body() as SignInMessage
                                                val editor = LoginActivity.appData!!.edit()

                                                editor.putString("ID", message.data.token.trim())
                                                editor.putString("name", message.data.name.trim())
                                                editor.putString("startday", message.data.startDay.trim())

                                                Log.d("Login" ,message.data.toString())

                                                editor.apply()
                                            }
                                        }
                                    }
                                    override fun onFailure(call: Call<SignInMessage>, t: Throwable) {
                                        Log.e("Page4", "실패: " + t.message)
                                        t.printStackTrace()
                                    }
                                })
                            }

                            com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))

                            var call: Call<Re> = APIRequestManager.getInstance().requestServer().getMandal(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

                            call.enqueue(object: Callback<Re> {
                                override fun onResponse(call: Call<Re>, response: Response<Re>) {
                                    when(response.code()) {
                                        200 -> {
                                            val re: Re = response.body() as Re
                                            com.emirim.hyejin.mokgongso.Mandalart.re = re

                                            Log.d("getMandal", re.toString())
                                        }
                                        401 -> {

                                        }
                                        404 -> {

                                        }
                                    }
                                }
                                override fun onFailure(call: Call<Re>, t: Throwable) {
                                    Log.e("ServerMandal", "에러: " + t.message)
                                    t.printStackTrace()
                                }
                            })
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

            activity?.finish()
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            for(i in 0..7) {
                if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                    mandalartSub[i].text = "2"
                    mandalartSub[i].setBackgroundResource(R.drawable.mandalart_box_1)
                }
            }

            for (i in 1..8) {
                mandalartSub[i - 1].setOnClickListener {
                    if(Mandalart.subMandalartTitle[i - 1] != null && !(Mandalart.subMandalartTitle[i - 1].equals(""))) {
                        Mandalart.position = i
                        CreateMandalart.mViewPager.currentItem = 3
                    }
                }
            }
        }
    }
}