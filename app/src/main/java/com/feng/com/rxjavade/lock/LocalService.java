package com.feng.com.rxjavade.lock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Author: river
 * Date: 2016/6/1 17:36
 * Description: 远程服务
 */
public class LocalService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
