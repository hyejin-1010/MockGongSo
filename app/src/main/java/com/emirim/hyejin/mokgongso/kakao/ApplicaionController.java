package com.emirim.hyejin.mokgongso.kakao;

import android.app.Activity;
import android.app.Application;

import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class ApplicaionController extends Application {
    private static ApplicaionController instance = null;
    private static volatile Activity currentActivity = null;

    public static ApplicaionController getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicaionController.instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    public static Activity getCurrentActivity() { return currentActivity; }

    public static void setCurrentActivity(Activity currentActivity) {
        ApplicaionController.currentActivity = currentActivity;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        instance = null;
    }
}
