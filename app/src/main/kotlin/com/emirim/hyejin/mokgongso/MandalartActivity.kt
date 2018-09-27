package com.emirim.hyejin.mokgongso

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.fragment.*
import com.emirim.hyejin.mokgongso.fragment.innerrecyclerview.InnerAdapter
import com.emirim.hyejin.mokgongso.fragment.innerrecyclerview.InnterItem
import com.emirim.hyejin.mokgongso.fragment.outerrecycler.BasicAdapter
import com.emirim.hyejin.mokgongso.fragment.outerrecycler.ViewHolder
import com.emirim.hyejin.mokgongso.helper.BottomNavigationViewHelper
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.emirim.hyejin.mokgongso.model.*
import kotlinx.android.synthetic.main.activity_mandalart.*
import kotlinx.android.synthetic.main.dialog_del.view.*
import kotlinx.android.synthetic.main.fab_layout.*
import kotlinx.android.synthetic.main.fab_layout.view.*
import kotlinx.android.synthetic.main.fragment_diary.*
import kotlinx.android.synthetic.main.fragment_diary.view.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MandalartActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var currentFragment: Fragment? = null
    var ft: FragmentTransaction? = null

    companion object {
        lateinit var rightButtonImageView: ImageView
        var smallBoolean = false
        var mandalBoolean: Boolean = false
        var position = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandalart)

        initBottomNavigation()

        var token: String = LoginActivity.appData!!.getString("ID", "")

        Log.d("Login token" ,token)

        if(token.isNotEmpty()) {
            // auto
            com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(token)

            var callAuto: Call<SignInMessage> = APIRequestManager.getInstance().requestServer().auto(token)

            callAuto.enqueue(object : Callback<SignInMessage> {
                override fun onResponse(call: Call<SignInMessage>, response: Response<SignInMessage>) {
                    when (response.code()) {
                        200 -> {
                            val message: SignInMessage = response.body() as SignInMessage
                            val editor = LoginActivity.appData!!.edit()

                            editor.putString("ID", message.data.token.trim())
                            editor.putString("name", message.data.name.trim())
                            User.startDay = message.data.startDay.trim()

                            Log.d("Login", message.data.toString())

                            editor.apply()
                        }
                    }
                }

                override fun onFailure(call: Call<SignInMessage>, t: Throwable) {
                    Log.e("Page4", "실패: " + t.message)
                    t.printStackTrace()
                }
            })
        }

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
                        com.emirim.hyejin.mokgongso.mandalart.Mandalart.achievement = re.re.achievement.toInt()

                        Log.d("getMandal", re.toString())

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

                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdContent[i] = strArray
                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdAchievement[i] = intArray
                        }

                        mandalBoolean = true
                        Log.d("ServerMandal", response.body().toString())

                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.frameLayout, MainFragment.newInstance())
                                .commit()
                        position = -1
                        titlebartxt.text = "메인"
                        rightButtonImageView.setImageResource(0)
                    }
                    401 -> {
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.frameLayout, MainFragment.newInstance())
                                .commit()
                        position = -1
                        titlebartxt.text = "메인"
                        rightButtonImageView.setImageResource(0)
                    }
                    404 -> {
                        Log.d("ServerMandal", "실패")
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.frameLayout, MainFragment.newInstance())
                                .commit()
                        position = -1
                        titlebartxt.text = "메인"
                        rightButtonImageView.setImageResource(0)
                    }
                }
            }
            override fun onFailure(call: Call<Re>, t: Throwable) {
                Log.e("ServerMandal", "에러: " + t.message)
                t.printStackTrace()

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MainFragment.newInstance())
                        .commit()
                position = -1
                titlebartxt.text = "메인"
                rightButtonImageView.setImageResource(0)
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

                        for(i in 0..2) {
                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.subMandalartTitle[tRe.re.mandal[i].order] = tRe.re.mandal[i].middleTitle

                            var strArray = Array<String>(3, {""})
                            strArray[0] = tRe.re.mandal[i].smallMandalArt1.title
                            strArray[1] = tRe.re.mandal[i].smallMandalArt2.title
                            strArray[2] = tRe.re.mandal[i].smallMandalArt3.title

                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdContent[i] = strArray
                        }

                        smallBoolean = true

                        Log.d("TMandal", response.body().toString())
                    }
                    401 -> {
                    }
                    404 -> {
                        Log.d("TMandal", "실패")
                    }
                }
            }
            override fun onFailure(call: Call<TRe>, t: Throwable) {
                Log.e("TMandal", "에러: " + t.message)
                t.printStackTrace()
            }
        })

        rightButtonImageView.setOnClickListener {
            if(position == 2) {
                // 작은 만다라트
                delDialog()
            } else if(position == 5) {
                if(rightButtonImageView.tag.equals(R.drawable.trash)) {
                    if(BasicAdapter.mExpandedPosition >= 0) {
                        (DiaryFragment.containtLayout.diaryRecyclerView.adapter as BasicAdapter).viewCheckBox()

                        rightButtonImageView.setImageResource(R.drawable.confirm)
                        rightButtonImageView.tag = R.drawable.confirm
                    }
                } else {
                    var cancelDialog = LayoutInflater.from(this).inflate(R.layout.dialog_deldiary, null)
                    val mBuilder = AlertDialog.Builder(this)
                            .setView(cancelDialog)

                    val  mAlertDialog = mBuilder.show()

                    cancelDialog.cancel.setOnClickListener {
                        (DiaryFragment.containtLayout.diaryRecyclerView.adapter as BasicAdapter).goneCheckBoxNotDel()
                        mAlertDialog.dismiss()
                    }
                    cancelDialog.delBtn.setOnClickListener {
                        (DiaryFragment.containtLayout.diaryRecyclerView.adapter as BasicAdapter).goneCheckBox()

                        rightButtonImageView.setImageResource(R.drawable.trash)
                        rightButtonImageView.tag = R.drawable.trash

                        mAlertDialog.dismiss()
                    }
                }
            }
            else {
                if(Mandalart.viewer == 2){
                    com.emirim.hyejin.mokgongso.Mandalart.setLow = SetLow(LoginActivity.appData!!.getString("ID", ""), Mandalart.secondSelect, Mandalart.thirdSelect, Mandalart.tmpAchievement)

                    var call: Call<Achievement> = APIRequestManager.getInstance().requestServer().setLow(com.emirim.hyejin.mokgongso.Mandalart.setLow)

                    call.enqueue(object: Callback<Achievement> {
                        override fun onResponse(call: Call<Achievement>, response: Response<Achievement>) {
                            when(response.code()) {
                                200 -> {
                                    Log.d("ThirdMandalart", response.body().toString())
                                    Log.d("ThirdMandalart", "${Mandalart.secondSelect}, ${Mandalart.thirdSelect}, ${Mandalart.tmpAchievement}")

                                    var achievement = response.body() as Achievement
                                    Mandalart.achievement = achievement.achievement.toDouble().toInt()

                                    Mandalart.thirdAchievement[Mandalart.secondSelect][Mandalart.thirdSelect] = Mandalart.tmpAchievement

                                    Mandalart.viewer = 1
                                    Mandalart.thirdSelect = -1

                                    supportFragmentManager
                                            .beginTransaction()
                                            .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                                            .commit()


                                    MandalartActivity.rightButtonImageView.setImageResource(R.drawable.pencil)
                                }
                                404 -> {
                                }
                                500 -> {
                                    Log.d("ThirdMandalart", "실패")
                                }
                            }
                        }
                        override fun onFailure(call: Call<Achievement>, t: Throwable) {
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
                position = -3
            } else {
                subFloating.visibility = View.GONE
                fabBtn.setImageResource(R.drawable.floating_1)
            }
        }

        fabBtn.setOnLongClickListener {
            fabVisible()

            return@setOnLongClickListener true
        }

        fab1.setOnClickListener {
            subFloating.visibility = View.GONE
            fabBtn.setImageResource(R.drawable.floating_1)

            titlebartxt.text = "일기"
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, DiaryFragment.newInstance())
                    .commit()

            rightButtonImageView.setImageResource(R.drawable.trash)
            rightButtonImageView.tag = R.drawable.trash
            position = 5
        }
        fab2.setOnClickListener {
            subFloating.visibility = View.GONE
            fabBtn.setImageResource(R.drawable.floating_1)

            titlebartxt.text = "보고서"
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, ReportFragment.newInstance())
                    .commit()

            rightButtonImageView.setImageResource(0)
            position = 6
        }
    }

    fun fabVisible() {
        if(subFloating.visibility == View.GONE) {
            subFloating.visibility = View.VISIBLE
            fabBtn.setImageResource(R.drawable.floating_2)
        } else {
            subFloating.visibility = View.GONE
            fabBtn.setImageResource(R.drawable.floating_1)
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
                    rightButtonImageView.setImageResource(R.drawable.pencil)

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
                    rightButtonImageView.setImageResource(R.drawable.trash)
                    rightButtonImageView.tag = R.drawable.trash

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
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, StoreFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(0)

                position = 3
                return true
            }
            R.id.action_setting -> {
                titlebartxt.text = "환경설정"
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, SettingFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(0)

                position = 4
                return true
            }
            R.id.action -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MainFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(0)

                position = -1

                titlebartxt.text = "메인"
            }
            else -> {
                /*supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MainFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(0)

                position = -1
                titlebartxt.text = "메인"*/
            }
        }

        return true
    }

    private fun initBottomNavigation() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        bottomNavigation.itemTextAppearanceActive = R.style.bottomBarTextView
        bottomNavigation.itemTextAppearanceInactive = R.style.bottomBarTextView
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))

        var call: Call<Re> = APIRequestManager.getInstance().requestServer().getMandal(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

        call.enqueue(object: Callback<Re> {
            override fun onResponse(call: Call<Re>, response: Response<Re>) {
                when(response.code()) {
                    200 -> {
                        val re: Re = response.body() as Re
                        com.emirim.hyejin.mokgongso.Mandalart.re = re
                        com.emirim.hyejin.mokgongso.mandalart.Mandalart.title = re.re.title
                        com.emirim.hyejin.mokgongso.mandalart.Mandalart.achievement = re.re.achievement.toInt()

                        Log.d("getMandal", re.toString())

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

                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdContent[i] = strArray
                            com.emirim.hyejin.mokgongso.mandalart.Mandalart.thirdAchievement[i] = intArray
                        }

                        mandalBoolean = true
                        Log.d("ServerMandal", response.body().toString())
                    }
                    401 -> {
                    }
                    404 -> {
                        Log.d("ServerMandal", "실패")
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

                        for(i in 0..2) {
                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.subMandalartTitle[tRe.re.mandal[i].order] = tRe.re.mandal[i].middleTitle

                            var strArray = Array<String>(3, {""})
                            strArray[0] = tRe.re.mandal[i].smallMandalArt1.title
                            strArray[1] = tRe.re.mandal[i].smallMandalArt2.title
                            strArray[2] = tRe.re.mandal[i].smallMandalArt3.title

                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdContent[i] = strArray
                        }

                        smallBoolean = true

                        Log.d("TMandal", response.body().toString())
                    }
                    401 -> {
                    }
                    404 -> {
                        Log.d("TMandal", "실패")
                    }
                }
            }
            override fun onFailure(call: Call<TRe>, t: Throwable) {
                Log.e("TMandal", "에러: " + t.message)
                t.printStackTrace()
            }
        })

        super.onWindowFocusChanged(hasFocus)

        if(hasFocus) {
            com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))
            var call5: Call<GetDiary> = APIRequestManager.getInstance().requestServer().getDiary(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

            call5.enqueue(object: Callback<GetDiary> {
                override fun onResponse(call: Call<GetDiary>, response: Response<GetDiary>) {
                    when(response.code()) {
                        200 -> {
                            com.emirim.hyejin.mokgongso.Diary.getDiary = response.body() as GetDiary
                        }
                    }
                }
                override fun onFailure(call: Call<GetDiary>, t: Throwable) {
                    Log.e("ServerMandal", "에러: " + t.message)
                    t.printStackTrace()
                }
            })

            if((position == 1 || position == 0) && Mandalart.title != null) {
                if (Mandalart.title != null) {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                            .commit()
                    position = 1
                    rightButtonImageView.setImageResource(R.drawable.pencil)
                }
            } else if(position == 2 || position == -2) {
                if (com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.title != null) {
                    position = 2
                    smallBoolean = true
                    rightButtonImageView.setImageResource(R.drawable.trash)
                    rightButtonImageView.tag = R.drawable.trash
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
                rightButtonImageView.setImageResource(R.drawable.pencil)
            } else if (Mandalart.viewer == 2) {
                Mandalart.viewer = 1
                Mandalart.thirdSelect = -1
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MandalartViewFragment.newInstance())
                        .commit()
                rightButtonImageView.setImageResource(R.drawable.pencil)
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

                rightButtonImageView.setImageResource(R.drawable.pencil)
            }else if(com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer == 2) {
                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer = 1
                com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdSelect = -1

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, SmallMandalartFragment.newInstance())
                        .commit()

                rightButtonImageView.setImageResource(R.drawable.pencil)
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

    fun delDialog() {
        var cancelDialog = LayoutInflater.from(this).inflate(R.layout.dialog_del, null)
        val mBuilder = AlertDialog.Builder(this)
                .setView(cancelDialog)

        val  mAlertDialog = mBuilder.show()

        cancelDialog.cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
        cancelDialog.delBtn.setOnClickListener {
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

                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.position = 1
                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.secondSelect = -1
                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdSelect = -1
                            com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer = 0

                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.frameLayout, SmallMandalartBeforeFragment.newInstance())
                                    .commit()

                            mAlertDialog.dismiss()
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
        }
    }
 }