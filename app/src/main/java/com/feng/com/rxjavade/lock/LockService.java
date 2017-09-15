package com.feng.com.rxjavade.lock;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.feng.com.rxjavade.app.config.AppConfig;
import com.feng.com.rxjavade.utils.BroadCastUtil;
import com.feng.com.rxjavade.utils.SpUtil;
import com.feng.com.rxjavade.utils.WindowUtils;

import static com.feng.com.rxjavade.app.config.AppConfig.AL_NOTIFICATION;
import static com.feng.com.rxjavade.app.config.SPKey.LOCK_SWITCH;
import static com.feng.com.rxjavade.app.config.SPKey.LOCK_TYPE;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class LockService extends Service {
    private String TAG = this.getClass().getSimpleName();

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("-----","start");
        IntentFilter mScreenOnFilter = new IntentFilter();
        mScreenOnFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mScreenOnFilter.addAction(Intent.ACTION_SCREEN_ON);
        LockService.this.registerReceiver(mScreenActionReceiver, mScreenOnFilter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (AppConfig.AL_NOTIFICATION_RE.equals(intent.getAction())){
            new Handler().postDelayed(() -> {
                BroadCastUtil.postBroadcast(this,AL_NOTIFICATION);
            }, 4000);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mScreenActionReceiver);
        // 在此重新启动,使服务常驻内存
//        Log.e("-----","destroy");
//        startService(new Intent(this, LockService.class));

    }


    private BroadcastReceiver mScreenActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("-----","get");
            if (action.equals(Intent.ACTION_SCREEN_ON)) {
            } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                if (SpUtil.getBoolean(LOCK_SWITCH,true)) {
                    if (SpUtil.getBoolean(LOCK_TYPE,false)) {
                        Intent LockIntent = new Intent(context, MyLockScreenActivity.class);
                        LockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(LockIntent);
                    }else {
                        Intent LockIntent = new Intent(context, MyLockScreenTwoActivity.class);
                        LockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(LockIntent);
                    }
                }else {
                    WindowUtils.init(context);
                }
            }
        }
    };
}
