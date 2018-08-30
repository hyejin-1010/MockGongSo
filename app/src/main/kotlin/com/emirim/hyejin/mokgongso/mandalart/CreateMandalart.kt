package com.emirim.hyejin.mokgongso.mandalart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.page.Page1
import com.emirim.hyejin.mokgongso.mandalart.page.Page2
import com.emirim.hyejin.mokgongso.mandalart.page.Page3
import com.emirim.hyejin.mokgongso.mandalart.page.Page4
import kotlinx.android.synthetic.main.activity_mandalart_create.*

class CreateMandalart : AppCompatActivity() {
    companion object {
        val MAX_PAGE = 4
        var curFragment = Fragment()
        lateinit var mViewPager: CustomViewPager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandalart_create)

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
    }

    class adapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            if(position < 0 || MAX_PAGE <= position)
                return curFragment

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