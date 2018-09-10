package com.emirim.hyejin.mokgongso.mandalart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import com.emirim.hyejin.mokgongso.LoginActivity

import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.fragment.MandalartFragment
import com.emirim.hyejin.mokgongso.fragment.MandalartViewFragment
import com.emirim.hyejin.mokgongso.mandalart.page.Page1
import com.emirim.hyejin.mokgongso.mandalart.page.Page2
import com.emirim.hyejin.mokgongso.mandalart.page.Page3
import com.emirim.hyejin.mokgongso.mandalart.page.Page4
import com.emirim.hyejin.mokgongso.model.MandalChk
import com.emirim.hyejin.mokgongso.model.Message
import com.emirim.hyejin.mokgongso.model.Middle
import com.emirim.hyejin.mokgongso.model.Re
import kotlinx.android.synthetic.main.activity_mandalart_create.*
import kotlinx.android.synthetic.main.fab_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateMandalart : AppCompatActivity() {
    companion object {
        val MAX_PAGE = 4
        var curFragment = Fragment()
        lateinit var mViewPager: CustomViewPager
        var boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandalart_create)

        // getWindow().setSoftInputMode(WindowManager.LayoutParams.Soft_INPUT_ADJUST_RESIZE);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        mViewPager = viewPager
        mViewPager.adapter = adapter(supportFragmentManager)

        dot1.setImageResource(R.drawable.selected_dot)
        dot2.setImageResource(R.drawable.default_dot)
        dot3.setImageResource(R.drawable.default_dot)

        mViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> {
                        dot1.setImageResource(R.drawable.selected_dot)
                        dot2.setImageResource(R.drawable.default_dot)
                        dot3.setImageResource(R.drawable.default_dot)
                    }
                    1 -> {
                        dot1.setImageResource(R.drawable.default_dot)
                        dot2.setImageResource(R.drawable.selected_dot)
                        dot3.setImageResource(R.drawable.default_dot)
                    }
                    2 -> {
                        dot1.setImageResource(R.drawable.default_dot)
                        dot2.setImageResource(R.drawable.default_dot)
                        dot3.setImageResource(R.drawable.selected_dot)
                    }
                    3 -> {
                        dot1.setImageResource(R.drawable.default_dot)
                        dot2.setImageResource(R.drawable.default_dot)
                        dot3.setImageResource(R.drawable.selected_dot)
                    }
                }
            }
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
        })

        /*curFragment.view?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            Toast.makeText(this, "있잖아", Toast.LENGTH_LONG).show()

            if(keyCode == KeyEvent.KEYCODE_BACK) {
                CreateMandalart.mViewPager.currentItem = 2
                Toast.makeText(this, "있잖아", Toast.LENGTH_LONG).show()

                return@OnKeyListener true
            } else {
                false
            }
        })*/
    }

    override fun onBackPressed() {
        if(CreateMandalart.mViewPager.currentItem == 3) {
            CreateMandalart.mViewPager.currentItem = 2
        } else {
            val middle: ArrayList<Middle> = ArrayList()

            for(i in 1..(Mandalart.count - 1)) {
                middle.add(Middle(Mandalart.subMandalartTitle[i - 1], Mandalart.thirdContent[i - 1].toList()))
            }
            for(i in Mandalart.count .. 8) {
                middle.add(Middle("", Mandalart.thirdContent[i - 1].toList()))
            }

            var appData = this.getSharedPreferences("Mandalart", 0)
            com.emirim.hyejin.mokgongso.Mandalart.makemandalart = com.emirim.hyejin.mokgongso.model.Mandalart(Mandalart.title.toString(), appData.getString("ID", ""), middle)

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

                            com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))

                            var call: Call<Re> = APIRequestManager.getInstance().requestServer().getMandal(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

                            call.enqueue(object: Callback<Re> {
                                override fun onResponse(call: Call<Re>, response: Response<Re>) {
                                    when(response.code()) {
                                        200 -> {
                                            val re: Re = response.body() as Re
                                            com.emirim.hyejin.mokgongso.Mandalart.re = re
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

            super.onBackPressed()
        }
    }

    class adapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
        var position = 0

        override fun getItem(position: Int): Fragment {
            if(position < 0 || MAX_PAGE <= position)
                return curFragment

            this.position = position

            Log.d("Mandalart", position.toString())

            when(position) {
                0 -> {
                    curFragment = Page1()
                    return curFragment
                }
                1 -> {
                    curFragment = Page2()
                    return curFragment
                }
                2 -> {
                    curFragment = Page3()
                    return curFragment
                }
                3 -> {
                    curFragment = Page4()
                    return curFragment
                }
            }

            return curFragment
        }

        override fun getCount(): Int {
            return MAX_PAGE
        }
    }
}