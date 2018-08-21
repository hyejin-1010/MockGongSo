package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("id", "ss")
        startActivity(intent)
    }
}
