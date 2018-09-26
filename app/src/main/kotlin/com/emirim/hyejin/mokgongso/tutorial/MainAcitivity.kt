package com.emirim.hyejin.mokgongso.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.tutorial.page.*
import kotlinx.android.synthetic.main.tutorial_main.*

class MainAcitivity: AppCompatActivity() {
    companion object {
        val MAX_PAGE = 6
        var cur_Fragment = Fragment()
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_main)

        context = this

        tutorialViewPager.adapter = adapter(supportFragmentManager)
    }

    class adapter: FragmentPagerAdapter {
        constructor(fm: FragmentManager): super(fm)

        override fun getItem(position: Int): Fragment {
            if(position < 0 || MAX_PAGE <= position)
                return cur_Fragment

            when(position) {
                0 -> {
                    cur_Fragment = Page1()
                }
                1 -> {
                    cur_Fragment = Page2()
                }
                2 -> {
                    cur_Fragment = Page3()
                }
                3 -> {
                    cur_Fragment = Page4()
                }
                4 ->  {
                    cur_Fragment = Page45()
                }
                5 -> {
                    cur_Fragment = Page5()
                }
            }

            return cur_Fragment
        }

        override fun getCount(): Int {
            return MAX_PAGE
        }
    }
}