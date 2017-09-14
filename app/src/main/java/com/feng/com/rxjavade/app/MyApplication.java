package com.feng.com.rxjavade.app;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.feng.com.rxjavade.lock.LocalService;
import com.feng.com.rxjavade.lock.LockReceiver;
import com.feng.com.rxjavade.lock.LockReceiver_;
import com.feng.com.rxjavade.lock.LockService;
import com.marswin89.marsdaemon.DaemonApplication;
import com.marswin89.marsdaemon.DaemonConfigurations;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class MyApplication extends DaemonApplication {

    public static MyApplication instance;
    

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
    }
    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected DaemonConfigurations getDaemonConfigurations() {
        DaemonConfigurations.DaemonConfiguration configuration1 = new DaemonConfigurations.DaemonConfiguration(
                "com.feng.com.rxjavade:process1",
                LockService.class.getCanonicalName(),
                LockReceiver.class.getCanonicalName());

        DaemonConfigurations.DaemonConfiguration configuration2 = new DaemonConfigurations.DaemonConfiguration(
                "com.feng.com.rxjavade:process2",
                LocalService.class.getCanonicalName(),
                LockReceiver_.class.getCanonicalName());

        DaemonConfigurations.DaemonListener listener = new MyDaemonListener();
        //return new DaemonConfigurations(configuration1, configuration2);//listener can be null
        return new DaemonConfigurations(configuration1, configuration2, listener);
    }
    class MyDaemonListener implements DaemonConfigurations.DaemonListener{
        @Override
        public void onPersistentStart(Context context) {
        }

        @Override
        public void onDaemonAssistantStart(Context context) {
        }

        @Override
        public void onWatchDaemonDaed() {
        }
    }
}
