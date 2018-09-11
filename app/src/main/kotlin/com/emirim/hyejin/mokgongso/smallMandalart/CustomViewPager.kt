package com.emirim.hyejin.mokgongso.smallMandalart.page

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class CustomViewPager: ViewPager {
    companion object {
        var enabled: Boolean = true
    }

    constructor(context: Context): super(context) {
        enabled = true
    }
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        enabled = true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(enabled) {
            return super.onInterceptTouchEvent(ev)
        }

        return true
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {

        return true
    }
}