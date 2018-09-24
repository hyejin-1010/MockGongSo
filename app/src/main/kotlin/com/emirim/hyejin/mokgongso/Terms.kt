package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_terms.*

class Terms: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        var r1 = false
        var r2 = false
        var r3 = false

        content.movementMethod = ScrollingMovementMethod.getInstance()

        var bool = intent.getBooleanExtra("bool", false)

        if(bool) {
            allRadio.isChecked = true
            radio1.isChecked = true
            radio2.isChecked = true

            r1 = true
            r2 = true
            r3 = true
        }

        allRadio.setOnClickListener {
            if(r1) {
                allRadio.isChecked = false
                r1 = false
            }
            else {
                allRadio.isChecked = true
                r1 = true
            }
        }
        radio1.setOnClickListener {
            if(r2) {
                radio1.isChecked = false
                r2 = false
            }
            else {
                radio1.isChecked = true
                r2 = true
            }
        }
        radio2.setOnClickListener {
            if(r3) {
                radio2.isChecked = false
                r3 = false
            }
            else {
                radio2.isChecked = true
                r3 = true
            }
        }

        allRadio.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                radio1.isChecked = true
                radio2.isChecked = true
                r2 = true
                r3 = true
            } else {
                radio1.isChecked = false
                radio2.isChecked = false
                r2 = false
                r3 = false
            }
        }

        agree.setOnClickListener {
            if(radio1.isChecked) {
                startActivity(Intent(this, SignUpActivity::class.java))
                finish()
            }
        }
        notagree.setOnClickListener {
            finish()
        }
        leftArrow.setOnClickListener {
            finish()
        }
    }
}