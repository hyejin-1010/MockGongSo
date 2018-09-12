package com.emirim.hyejin.mokgongso.lockScreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LockScreenReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action.equals(Intent.ACTION_SCREEN_OFF)) {
            var i = Intent(context, LockScreenActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(i)
        }
    }
}