package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.fragment.*
import com.emirim.hyejin.mokgongso.helper.BottomNavigationViewHelper
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.emirim.hyejin.mokgongso.model.*
import kotlinx.android.synthetic.main.activity_mandalart.*
import kotlinx.android.synthetic.main.fab_layout.*
import kotlinx.android.synthetic.main.fab_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MandalartActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var currentFragment: Fragment? = null
    var ft: FragmentTransaction? = null
    var mandalBoolean: Boolean = false
    var position = 0

    companion object {
        lateinit var rightButtonImageView: ImageView
        var smallBoolean = false
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
                        position = 1

                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartViewFragment.newInstance())
                                .commit()

                        rightButtonImageView.setImageResource(R.mipmap.pencil)

                        Log.d("ServerMandal", response.body().toString())
                    }
                    401 -> {
                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartFragment.newInstance())
                                .commit()

                        position = 0
                    }
                    404 -> {
                        Log.d("ServerMandal", "실패")
                        supportFragmentManager
                                .beginTransaction()
                                .add(R.id.frameLayout, MandalartFragment.newInstance())
                                .commit()

                        position = 0
                    }
                }
            }
            override fun onFailure(call: Call<Re>, t: Throwable) {
                Log.e("ServerMandal", "에러: " + t.message)
                t.printStackTrace()
            }
        })

        // 작은 만다라트
        var call2: Call<TRe> = APIRequestManager.getInstance().requestServer().getTMandalChk(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

        call2.enqueue(object: Callback<TRe> {
            override fun onResponse(call: Call<TRe>, response: Response<TRe>) {
                when(response.code()) {
                    200 -> {
                        val tRe: TRe = response.body() as TRe
                        com.emirim.hyejin.mokgongso.Mandalart.tRe = tRe
                        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.title = tRe.re.title
                        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.achievement = tRe.re.achievement

                        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.count = 1

                        for(i in 0..2) {
                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.subMandalartTitle[tRe.re.mandal[i].order] = tRe.re.mandal[i].middleTitle

                            var strArray = Array<String>(3, {""})
                            strArray[0] = tRe.re.mandal[i].smallMandalArt1.title
                            strArray[1] = tRe.re.mandal[i].smallMandalArt2.title
                            strArray[2] = tRe.re.mandal[i].smallMandalArt3.title

                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdCout[i] = 0

                            for(j in 0..2) {
                                if(strArray[j].isNotEmpty()) com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdCout[i]++
                                else break
                            }

                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdContent[i] = strArray

                            if(tRe.re.mandal[i].middleTitle.isNotEmpty()) {
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.count ++
                            }
                        }

                        smallBoolean = true
                        position = 2

                        rightButtonImageView.setImageResource(R.mipmap.pencil)

                        Log.d("TMandal", response.body().toString())
                    }
                    401 -> {
                        rightButtonImageView.setImageResource(0)
                        position = -2
                    }
                    404 -> {
                        Log.d("TMandal", "실패")
                        rightButtonImageView.setImageResource(0)
                        position = -2
                    }
                }
            }
            override fun onFailure(call: Call<TRe>, t: Throwable) {
                Log.e("TMandal", "에러: " + t.message)
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
            if(position == 2) {
                // 작은 만다라트
                com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))

                var call: Call<Message> = APIRequestManager.getInstance().requestServer().delSub(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

                call.enqueue(object: Callback<Message> {
                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                        when(response.code()) {
                            200 -> {
                                Log.d("DelSub", "Success")

                                smallBoolean = false
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.title = null
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.achievement = 0
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdContent = Array(3, { Array<String>(3, {""}) })
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.subMandalartTitle = Array<String>(3, {""})

                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdCout = IntArray(3)
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.count = 1
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.position = 1
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.secondSelect = -1
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdSelect = -1
                                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer = 0

                                supportFragmentManager
                                        .beginTransaction()
                                        .replace(R.id.frameLayout, SmallMandalartBeforeFragment.newInstance())
                                        .commit()
                            }
                            500 -> {
                                Log.d("DelSub", "Failed")
                            }
                        }
                    }
                    override fun onFailure(call: Call<Message>, t: Throwable) {
                        Log.e("ThirdMandalart", "에러: " + t.message)
                        t.printStackTrace()
                    }
                })
            } else {
                if(Mandalart.viewer == 2){
                    com.emirim.hyejin.mokgongso.Mandalart.setLow = SetLow(LoginActivity.appData!!.getString("ID", ""), Mandalart.secondSelect, Mandalart.thirdSelect, Mandalart.tmpAchievement)

                    var call: Call<Message> = APIRequestManager.getInstance().requestServer().setLow(com.emirim.hyejin.mokgongso.Mandalart.setLow)

                    call.enqueue(object: Callback<Message> {
                        override fun onResponse(call: Call<Message>, response: Response<Message>) {
                            when(response.code()) {
                                200 -> {
                                    Log.d("ThirdMandalart", response.body().toString())

                                    Mandalart.thirdAchievement[Mandalart.secondSelect][Mandalart.thirdSelect] = Mandalart.tmpAchievement

                                    Mandalart.viewer = 1
                                    Mandalart.thirdSelect = -1

                                    supportFragmentManager
                                            .beginTransaction()
                                            .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                                            .commit()


                                    MandalartActivity.rightButtonImageView.setImageResource(R.mipmap.pencil)
                                }
                                404 -> {
                                }
                                500 -> {
                                    Log.d("ThirdMandalart", "실패")
                                }
                            }
                        }
                        override fun onFailure(call: Call<Message>, t: Throwable) {
                            Log.e("ThirdMandalart", "에러: " + t.message)
                            t.printStackTrace()
                        }
                    })
                } else {
                    var intent = Intent(this, CreateMandalart::class.java)
                    startActivity(intent)
                }
            }

        }

        fabBtn.setOnClickListener {
            if(subFloating.visibility == View.GONE) {
                titlebartxt.text = "메인"
                rightButtonImageView.setImageResource(0)
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MainFragment.newInstance())
                        .commit()
                position = -1
            } else {
                subFloating.visibility = View.GONE
                fabBtn.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)
            }
        }

        fabBtn.setOnLongClickListener {
            fabVisible()

            return@setOnLongClickListener true
        }

        fab1.setOnClickListener {
            subFloating.visibility = View.GONE
            fabBtn.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)

            titlebartxt.text = "일기"
            rightButtonImageView.setImageResource(0)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, DiaryFragment.newInstance())
                    .commit()
            position = 5
        }
        fab2.setOnClickListener {
            subFloating.visibility = View.GONE
            fabBtn.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)
        }
    }

    fun fabVisible() {
        if(subFloating.visibility == View.GONE) {
            subFloating.visibility = View.VISIBLE
            fabBtn.backgroundTintList = resources.getColorStateList(R.color.colorPrimaryDark)
        } else {
            subFloating.visibility = View.GONE
            fabBtn.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_mandalart -> {
                mandalartViewerInit()
                titlebartxt.text = "만다라트"

                if(mandalBoolean){
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                            .commit()
                    rightButtonImageView.setImageResource(R.mipmap.pencil)

                    position = 1
                }
                else {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, MandalartFragment.newInstance())
                            .commit()

                    rightButtonImageView.setImageResource(0)

                    position = 0
                }
                return true
            }
            R.id.action_smaill_mandalart -> {
                smallMandalartViewerInit()
                titlebartxt.text = "작은목표"

                if(smallBoolean){
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, SmallMandalartFragment.newInstance())
                            .commit()
                    rightButtonImageView.setImageResource(R.mipmap.pencil)

                    position = 2
                }
                else {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, SmallMandalartBeforeFragment.newInstance())
                            .commit()

                    rightButtonImageView.setImageResource(0)

                    position = -2
                }

                return true
            }
            R.id.action_store -> {
                titlebartxt.text = "상점"
                position = 3
                return true
            }
            R.id.action_setting -> {
                titlebartxt.text = "환경설정"
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, SettingFragment.newInstance())
                        .commit()
                position = 4
                return true
            }
            else -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MainFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(0)

                position = -1
                titlebartxt.text = "메인"
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
            if(position == 1) {
                if (Mandalart.title != null) {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                            .commit()
                    position = 1
                    rightButtonImageView.setImageResource(R.mipmap.pencil)
                }
            } else if(position == 2) {
                if (com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.title != null) {
                    position = 2
                    smallBoolean = true
                    rightButtonImageView.setImageResource(R.mipmap.pencil)
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, SmallMandalartFragment.newInstance())
                            .commit()
                }
            }

            Log.d("onWindowFocusChanged", "onWindowFocusChanged")
        }
    }

    override fun onBackPressed() {
        if(position == 1) {
            if (Mandalart.viewer == 1) {
                mandalartViewerInit()
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                        .commit()
                rightButtonImageView.setImageResource(R.mipmap.pencil)
            } else if (Mandalart.viewer == 2) {
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
        } else if(position == 2){
            if(com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer == 1){
                smallMandalartViewerInit()

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, SmallMandalartFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(R.mipmap.pencil)
            }else if(com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer == 2) {
                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer = 1
                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdSelect = -1

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, SmallMandalartFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(R.mipmap.pencil)
            }
        }
        else {
            super.onBackPressed()
        }
    }

    fun mandalartViewerInit() {
        Mandalart.viewer = 0
        Mandalart.secondSelect = -1
        Mandalart.thirdSelect = -1
    }

    fun smallMandalartViewerInit() {
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer = 0
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.secondSelect = -1
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdSelect = -1
    }
 }