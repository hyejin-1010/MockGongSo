package com.emirim.hyejin.mokgongso.lockScreen

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class LockScreenService: Service() {
    var mReceiver: LockScreenReceiver? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        mReceiver = LockScreenReceiver()
        var filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(mReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        if(intent != null) {
            if(intent.action == null) {
                if(mReceiver == null) {
                    mReceiver = LockScreenReceiver()
                    var filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
                    registerReceiver(mReceiver, filter)
                }
            }
        }

        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReceiver != null) {
            unregisterReceiver(mReceiver)
        }
    }
}