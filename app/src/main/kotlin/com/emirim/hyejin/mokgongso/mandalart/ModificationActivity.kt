package com.emirim.hyejin.mokgongso.mandalart

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.tutorial_modification.*

class ModificationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_modification)

        coordinatorLayout.setOnClickListener {
            finish()
        }
    }
}