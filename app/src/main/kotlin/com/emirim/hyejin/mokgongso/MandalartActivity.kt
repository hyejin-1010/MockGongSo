package com.emirim.hyejin.mokgongso

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.fragment.MandalartFragment
import com.emirim.hyejin.mokgongso.fragment.MandalartViewFragment
import com.emirim.hyejin.mokgongso.fragment.SettingFragment
import com.emirim.hyejin.mokgongso.fragment.SmallMandalartFragment
import com.emirim.hyejin.mokgongso.helper.BottomNavigationViewHelper
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.emirim.hyejin.mokgongso.model.MandalChk
import com.emirim.hyejin.mokgongso.model.Re
import kotlinx.android.synthetic.main.activity_mandalart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MandalartActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var currentFragment: Fragment? = null
    var ft: FragmentTransaction? = null
    var mandalBoolean: Boolean = false

    companion object {
        lateinit var rightButtonImageView: ImageView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandalart)

        initBottomNavigation()

        rightButtonImageView = findViewById(R.id.rightButton)

        com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))

        var call: Call<Re> = APIRequestManager.getInstance().requestServer().getMandal(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

        call.enqueue(object: Callback<Re> {
            override fun onResponse(call: Call<Re>, response: Response<Re>) {
                when(response.code()) {
                    200 -> {
                        val re: Re = response.body() as Re
                        com.emirim.hyejin.mokgongso.Mandalart.re = re
                        com.emirim.hyejin.mokgongso.mandalart.Mandalart.title = re.re.title
                        com.emirim.hyejin.mokgongso.mandalart.Mandalart.achievement = re.re.achievement

                        com.emirim.hyejin.mokgongso.mandalart.Mandalart.count = 1

                        for(i in 0..7){
                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.subMandalartTitle[re.re.mandal[i].order] = re.re.mandal[i].middleTitle

                            var strArray = Array<String>(8, {""})
                            strArray[0] = re.re.mandal[i].smallMandalArt1.title
                            strArray[1] = re.re.mandal[i].smallMandalArt2.title
                            strArray[2] = re.re.mandal[i].smallMandalArt3.title
                            strArray[3] = re.re.mandal[i].smallMandalArt4.title
                            strArray[4] = re.re.mandal[i].smallMandalArt5.title
                            strArray[5] = re.re.mandal[i].smallMandalArt6.title
                            strArray[6] = re.re.mandal[i].smallMandalArt7.title
                            strArray[7] = re.re.mandal[i].smallMandalArt8.title

                            var intArray = IntArray(8, {0})
                            intArray[0] = re.re.mandal[i].smallMandalArt1.achievement
                            intArray[1] = re.re.mandal[i].smallMandalArt2.achievement
                            intArray[2] = re.re.mandal[i].smallMandalArt3.achievement
                            intArray[3] = re.re.mandal[i].smallMandalArt4.achievement
                            intArray[4] = re.re.mandal[i].smallMandalArt5.achievement
                            intArray[5] = re.re.mandal[i].smallMandalArt6.achievement
                            intArray[6] = re.re.mandal[i].smallMandalArt7.achievement
                            intArray[7] = re.re.mandal[i].smallMandalArt8.achievement

                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdCout[i] = 0

                            for(j in 0..7){
                                if(strArray[j].isNotEmpty()) com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdCout[i] ++
                                else break
                            }

                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdContent[i] = strArray
                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdAchievement[i] = intArray

                            if(re.re.mandal[i].middleTitle.isNotEmpty()) {
                                com.emirim.hyejin.mokgongso.mandalart.Mandalart.count ++
                            }
                        }

                        mandalBoolean = true

                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartViewFragment.newInstance())
                                .commit()

                        Log.d("ServerMandal", response.body().toString())
                    }
                    401 -> {
                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartFragment.newInstance())
                                .commit()
                    }
                    404 -> {
                        Log.d("ServerMandal", "실패")
                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartFragment.newInstance())
                                .commit()
                    }
                }
            }
            override fun onFailure(call: Call<Re>, t: Throwable) {
                Log.e("ServerMandal", "에러: " + t.message)
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

        rightButtonImageView.setOnClickListener {
            if(Mandalart.viewer == 2){

            } else {

            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_mandalart -> {
                if(mandalBoolean){
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                            .commit()
                    rightButtonImageView.setImageResource(R.mipmap.pencil)
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
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, SmallMandalartFragment.newInstance())
                        .commit()

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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if(hasFocus) {
            if(Mandalart.title != null) {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                        .commit()
                rightButtonImageView.setImageResource(R.mipmap.pencil)
            }


        }
    }

    override fun onBackPressed() {
        if(Mandalart.viewer == 1) {
            Mandalart.viewer = 0
            Mandalart.secondSelect = -1
            Mandalart.thirdSelect = -1
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                    .commit()
            rightButtonImageView.setImageResource(R.mipmap.pencil)
        } else if(Mandalart.viewer == 2) {
            Mandalart.viewer = 1
            Mandalart.thirdSelect = -1
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                    .commit()
            rightButtonImageView.setImageResource(R.mipmap.pencil)
        } else {
            super.onBackPressed()
        }
    }
 }