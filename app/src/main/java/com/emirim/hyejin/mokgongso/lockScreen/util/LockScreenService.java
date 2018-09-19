package com.emirim.hyejin.mokgongso.lockScreen.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.emirim.hyejin.mokgongso.MainActivity;
import com.emirim.hyejin.mokgongso.R;

public class LockScreenService extends Service {

    private final String TAG = "LockscreenService";
    private int mServiceStartId = 1989;
    //TODO 원래 0이었어
    private Context mContext = null;
    private NotificationManager mNM;
    private BroadcastReceiver mLockscreenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != context) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    startLockscreenActivity();
                }
            }
        }
    };
    private void stateRecever(boolean isStartRecever) {
        if (isStartRecever) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            registerReceiver(mLockscreenReceiver, filter);
        } else {
            if (null != mLockscreenReceiver) {
                unregisterReceiver(mLockscreenReceiver);
                //TODO
            }
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        showNotification();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mServiceStartId = startId;
        stateRecever(true);
        Intent bundleIntet = intent;
        if (null != bundleIntet) {
            // startLockscreenActivity();
            Log.d(TAG, TAG + " onStartCommand intent  existed");
        } else {
            Log.d(TAG, TAG + " onStartCommand intent NOT existed");
        }
        return LockScreenService.START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //TODO
    @Override
    public void onDestroy() {
        stateRecever(false);
        mNM.cancel(1989);
    }
    private void startLockscreenActivity() {
        Intent startLockscreenActIntent = new Intent(mContext, MainActivity.class);
        //TODO 원래 메인, 락스크린이어야 할까요?
        startLockscreenActIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startLockscreenActIntent);
    }
    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        CharSequence text = "Running";
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        //TODO 여기도 원래 메인액티비디였음
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(text)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(getText(R.string.app_name))
                .setContentText(text)
                .setContentIntent(contentIntent)
                .setOngoing(true)
                .build();
        mNM.notify(1989, notification);
    }
}