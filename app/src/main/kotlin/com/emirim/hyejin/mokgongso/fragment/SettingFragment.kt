package com.emirim.hyejin.mokgongso.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import com.emirim.hyejin.mokgongso.*
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.lockScreen.util.LockScreen
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.emirim.hyejin.mokgongso.model.DelUser
import com.emirim.hyejin.mokgongso.model.Message
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog_del.view.*
import kotlinx.android.synthetic.main.fragment_setting.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SettingFragment : Fragment() {
    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }

    private var mAuth: FirebaseAuth? = null

    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout: View = inflater?.inflate(R.layout.fragment_setting, container, false)
        val lockSwitch = layout.findViewById<Switch>(R.id.lockSwitch)

        var gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity as MandalartActivity, gso)

        mAuth = FirebaseAuth.getInstance()

        // 로그아웃
        layout.logoutlayout.setOnClickListener {
            var editor = LoginActivity.appData!!.edit()

            editor.clear()
            editor.commit()

            var cancelDialog = LayoutInflater.from(activity).inflate(R.layout.dialog_logout, null)
            val mBuilder = AlertDialog.Builder(activity)
                    .setView(cancelDialog)

            val  mAlertDialog = mBuilder.show()

            cancelDialog.cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
            cancelDialog.delBtn.setOnClickListener {
                signOut()
                mAlertDialog.dismiss()
            }
        }

        // 회원탈퇴
        layout.secessionlayout.setOnClickListener {
            var cancelDialog = LayoutInflater.from(activity).inflate(R.layout.dialog_secession, null)
            val mBuilder = AlertDialog.Builder(activity)
                    .setView(cancelDialog)

            val  mAlertDialog = mBuilder.show()

            cancelDialog.cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
            cancelDialog.delBtn.setOnClickListener {
                User.delUser = DelUser(LoginActivity.appData!!.getString("ID", ""))

                Log.d("dleUser token", LoginActivity.appData!!.getString("ID", ""))

                var callDel: Call<Message> = APIRequestManager.getInstance().requestServer().delUser(User.delUser)

                callDel.enqueue(object : Callback<Message> {
                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                        when (response.code()) {
                            200 -> {
                                val message: Message = response.body() as Message
                                var editor = LoginActivity.appData!!.edit()

                                editor.clear()
                                editor.commit()

                                mAlertDialog.dismiss()
                                initSmallMandalart()
                                initMandalart()

                                signOut()
                            }
                            500 -> {
                                // 실패
                                mAlertDialog.dismiss()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Message>, t: Throwable) {
                        Log.e("SignUpActivity", "에러: " + t.message)
                        t.printStackTrace()
                        mAlertDialog.dismiss()
                    }
                })
            }
        }

        /*Lock Screen switch*/
        /* if Lock Screen already activate? */
        LockScreen.getInstance().init(context,true)
        lockSwitch.isChecked = LockScreen.getInstance().isActive()
        lockSwitch.setOnCheckedChangeListener{buttonview: CompoundButton?, isChecked: Boolean ->
            if(isChecked) LockScreen.getInstance().active()
            else LockScreen.getInstance().deactivate()
            //layout.findViewById<TextView>(R.id.locklock).text = "checked"
            //layout.findViewById<TextView>(R.id.locklock).text = "unchecked"
        }

        val sdf = SimpleDateFormat("yyyy-mm-dd")
        val today = Date()

        Log.d("startDay", "${LoginActivity.appData!!.getString("startday", "")} startday")

        if(LoginActivity.appData!!.getString("startday", "").isNotEmpty() || LoginActivity.appData!!.getString("startday", "").equals("")) {
            val startDay = LoginActivity.appData!!.getString("startday", "")
            if(startDay.equals("")) {
                layout.day.text = "0"
            } else {
                val startDate = SimpleDateFormat("yyyy-MM-dd").parse(startDay)

                val today = Date()

                var diff = today.time - startDate.time
                var diffDays = (diff / (24 * 60 * 60 * 1000)) + 1

                layout.day.text = diffDays.toString()
            }
        } else {
            layout.day.text = "0"
        }

        layout.username.text = LoginActivity.appData!!.getString("name", "")

        if(Mandalart.achievement < 25) {
            layout.profileIcon.setImageResource(R.drawable.mypage_1)
        } else if(Mandalart.achievement < 50) {
            layout.profileIcon.setImageResource(R.drawable.mypage_2)
        } else if(Mandalart.achievement < 75) {
            layout.profileIcon.setImageResource(R.drawable.mypage_3)
        } else {
            layout.profileIcon.setImageResource(R.drawable.mypage_4)
        }

        return layout
    }

    private fun signOut() {
        mAuth!!.signOut()
        LoginManager.getInstance().logOut()

        mGoogleSignInClient!!.signOut().addOnCompleteListener {
            initMandalart()
            initSmallMandalart()
            intentMain()
        }
    }

    private fun intentMain() {
        var intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    fun initMandalart() {
        Mandalart.title = null
        Mandalart.achievement = 0
        Mandalart.subMandalartTitle = Array<String>(8, {""})
        Mandalart.thirdContent = Array(8, { Array<String>(8, {""}) })
        Mandalart.thirdAchievement = Array(8, { IntArray(8) })
        Mandalart.thirdCout = IntArray(8)
        Mandalart.count = 1
        Mandalart.position = 1
        Mandalart.secondSelect = -1
        Mandalart.thirdSelect = -1
        Mandalart.viewer = 0
        Mandalart.tmpAchievement = 0
    }

    fun initSmallMandalart() {
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.title = null
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.achievement = 0
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.subMandalartTitle = Array<String>(3, {""})
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdContent = Array(3, {Array<String>(3, {""})})
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdCout = IntArray(3)
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.count = 1
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.position = 1
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.secondSelect = -1
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.thirdSelect = -1
        com.emirim.hyejin.mokgongso.smallMandalart.page.Mandalart.viewer = 0
    }
}