package com.emirim.hyejin.mokgongso

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.fragment.MandalartFragment
import com.emirim.hyejin.mokgongso.fragment.SettingFragment
import com.emirim.hyejin.mokgongso.helper.BottomNavigationViewHelper
import com.emirim.hyejin.mokgongso.model.MandalChk
import com.emirim.hyejin.mokgongso.model.MandalChkMessage
import kotlinx.android.synthetic.main.activity_mandalart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MandalartActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var currentFragment: Fragment? = null
    var ft: FragmentTransaction? = null
    var mandalBoolean: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandalart)

        initBottomNavigation()

        Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))

        var call: Call<MandalChkMessage> = APIRequestManager.getInstance().requestServer().mandalChk(Mandalart.mandalChk)

        call.enqueue(object: Callback<MandalChkMessage> {
            override fun onResponse(call: Call<MandalChkMessage>, response: Response<MandalChkMessage>) {
                when (response.code()) {
                    200 -> {
                        val message: MandalChkMessage = response.body() as MandalChkMessage
                        Log.d("MandalartActivity", "만다라트 유무 ${message.mandal.title}")
                        mandalBoolean = true
                    }
                    204 -> {
                        Log.d("MandalartActivity", "만다라트 없음")
                        mandalBoolean = false
                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartFragment.newInstance())
                                .commit()
                    }
                    404-> {
                        Log.d("MandalartActivity", "만다라트 유무 실패")
                        mandalBoolean = false
                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartFragment.newInstance())
                                .commit()
                    }
                }
            }

            override fun onFailure(call: Call<MandalChkMessage>, t: Throwable) {
                Log.e("MandalartActivity", "에러: " + t.message)
                t.printStackTrace()
            }
        })

        /*
        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frameLayout, MandalartFragment.newInstance())
                    .commit()
        }
        */
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_mandalart -> {
                if(mandalBoolean){
                }
                else {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, MandalartFragment.newInstance())
                            .commit()
                }
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
                        .replace(R.id.frameLayout, SettingFragment.newInstance())
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