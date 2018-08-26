package com.emirim.hyejin.mokgongso.helper

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log

class BottomNavigationViewHelper {
    companion object {
        fun disableShiftMode(view: BottomNavigationView) {
            val menuView = view.getChildAt(0) as BottomNavigationMenuView

            try {
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false

                for (x in 0..menuView.childCount) {
                    val item = menuView.getChildAt(x) as BottomNavigationItemView
                    item.setShifting(false)
                    item.setPadding(0, 15, 0, 0)
                    item.setChecked(item.itemData.isChecked)
                }
            } catch (e: NoSuchFieldException) {
                Log.e("BNVHelper", "Unable to get shift mode field", e)
            } catch (e : IllegalAccessException) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e)
            }
        }
    }
}