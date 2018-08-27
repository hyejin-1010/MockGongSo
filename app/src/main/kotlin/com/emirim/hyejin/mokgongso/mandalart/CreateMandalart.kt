package com.emirim.hyejin.mokgongso.mandalart

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.page.Page1
import com.emirim.hyejin.mokgongso.mandalart.page.Page2
import com.emirim.hyejin.mokgongso.mandalart.page.Page3
import kotlinx.android.synthetic.main.activity_mandalart_create.*

class CreateMandalart : AppCompatActivity() {
    companion object {
        val MAX_PAGE = 3
        var curFragment = Fragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandalart_create)

        // viewPager.adapter = adapter(supportFragmentManager)
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
            }

            return curFragment
        }

        override fun getCount(): Int {
            return MAX_PAGE
        }
    }
}