package com.emirim.hyejin.mokgongso.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_setting.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.locks.Lock

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
        /*signOutBtn.setOnClickListener {
            var editor = LoginActivity.appData!!.edit()

            editor.clear()
            editor.commit()

            signOut()
        }*/

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
        //val startDay = sdf.parse(LoginActivity.appData!!.getString("startDay", ""))
        val today = Date()

        val startCal = GregorianCalendar()
        val toCal = GregorianCalendar()

        //startCal.time = startDay
        //startCal.time = today

        layout.username.text = LoginActivity.appData!!.getString("name", "")
        //layout.day

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