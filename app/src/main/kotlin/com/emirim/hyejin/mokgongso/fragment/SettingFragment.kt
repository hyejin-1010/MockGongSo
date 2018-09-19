package com.emirim.hyejin.mokgongso.fragment

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
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.MainActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.lockScreen.util.LockScreen
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_setting.view.*
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
        val signOutBtn = layout.findViewById<TextView>(R.id.signOutBtn)
        val lockSwitch = layout.findViewById<Switch>(R.id.lockSwitch)

        var gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity as MandalartActivity, gso)

        mAuth = FirebaseAuth.getInstance()


        layout.logoutlayout.setOnClickListener {
            var editor = LoginActivity.appData!!.edit()

            editor.clear()
            editor.commit()

            signOut()
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

        /*signOutBtn.setOnClickListener {
            var editor = LoginActivity.appData!!.edit()

            editor.clear()
            editor.commit()

            signOut()
        }*/

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
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun intentMain() {
        var intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }
}