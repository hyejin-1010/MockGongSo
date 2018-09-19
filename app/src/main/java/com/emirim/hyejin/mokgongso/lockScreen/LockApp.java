package com.emirim.hyejin.mokgongso.lockScreen;

import android.app.Application;
public class LockApp extends Application {
    public boolean lockScreenShow=false;
    public int notificationId=1989;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}