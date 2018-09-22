package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_terms.*

class Terms: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        allRadio.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                radio1.isChecked = true
                radio2.isChecked = true
            } else {
                radio1.isChecked = false
                radio2.isChecked = false
            }
        }

        agree.setOnClickListener {
            if(radio1.isChecked && radio2.isChecked) {
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