package com.emirim.hyejin.mokgongso.lockScreen

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import com.emirim.hyejin.mokgongso.R
import kotlinx.android.synthetic.main.activity_lockscreen.*

class LockScreenActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)

        setContentView(R.layout.activity_lockscreen)

        btn.setOnClickListener {
            finish()
        }
    }
}
