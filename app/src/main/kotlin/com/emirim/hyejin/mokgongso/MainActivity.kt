package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emirim.hyejin.mokgongso.lockScreen.LockScreenService

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redirectLoginActivity()
    }

    private fun redirectLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
