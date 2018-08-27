package com.emirim.hyejin.mokgongso

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.emirim.hyejin.mokgongso.fragment.MandalartFragment
import com.emirim.hyejin.mokgongso.fragment.SettingFragment
import com.emirim.hyejin.mokgongso.helper.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_mandalart.*

class MandalartActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var currentFragment: Fragment? = null
    var ft: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandalart)

        /*
        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frameLayout, MandalartFragment.newInstance())
                    .commit()
        }
        */

        initBottomNavigation()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, MandalartFragment.newInstance())
                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_mandalart -> {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.frameLayout, MandalartFragment.newInstance())
                        .commit()
                return true
            }
            R.id.action_smaill_mandalart -> {
                return true
            }
            R.id.action_store -> {
                return true
            }
            R.id.action_setting -> {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.frameLayout, SettingFragment.newInstance())
                        .commit()
                return true
            }
        }

        return true
    }

    private fun initBottomNavigation() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }
 }