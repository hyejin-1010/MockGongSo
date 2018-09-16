package com.emirim.hyejin.mokgongso.lockScreen.util;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import com.emirim.hyejin.mokgongso.lockScreen.LockApp;

public class LockWinAccessService extends AccessibilityService{
    @Override
    protected boolean onKeyEvent(KeyEvent event) {

        LockScreen.getInstance().init(this);
        if(  ((LockApp) getApplication()).lockScreenShow ){
            // disable home
            if(event.getKeyCode()== KeyEvent.KEYCODE_HOME || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER){
                return true;
            }
        }

        return super.onKeyEvent(event);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        //Log.d("onAccessibilityEvent","onAccessibilityEvent");
    }

    @Override
    public void onInterrupt() {

    }
}
