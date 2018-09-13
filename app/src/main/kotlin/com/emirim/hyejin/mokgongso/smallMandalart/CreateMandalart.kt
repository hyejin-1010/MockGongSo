package com.emirim.hyejin.mokgongso.smallMandalart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.mandalart.CustomViewPager
import com.emirim.hyejin.mokgongso.model.Message
import com.emirim.hyejin.mokgongso.model.TMiddle
import com.emirim.hyejin.mokgongso.smallMandalart.page.*
import kotlinx.android.synthetic.main.activity_mandalart_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

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
            // 데이터 전송
            val middle: ArrayList<TMiddle> = ArrayList()

            for(i in 1..(Mandalart.count - 1)) {
                middle.add(TMiddle(Mandalart.subMandalartTitle[i - 1], Mandalart.thirdContent[i - 1].toList()))
            }
            for(i in Mandalart.count .. 3) {
                middle.add(TMiddle("", Mandalart.thirdContent[i - 1].toList()))
            }

            val date = Date()
            val sdf = SimpleDateFormat("yyyy-mm-dd")

            var appData = this.getSharedPreferences("Mandalart", 0)
            com.emirim.hyejin.mokgongso.Mandalart.tMake = com.emirim.hyejin.mokgongso.model.TMake(Mandalart.title.toString(), appData.getString("ID", ""), sdf.format(date).toString(), middle)

            val call: Call<Message> = APIRequestManager.getInstance().requestServer().tmake(com.emirim.hyejin.mokgongso.Mandalart.tMake)

            call.enqueue(object: Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    when(response.code()) {
                        200 -> {
                            Log.d("Tmake", "성공")
                        }
                        500 -> {
                            Log.d("Page4", "실패")
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