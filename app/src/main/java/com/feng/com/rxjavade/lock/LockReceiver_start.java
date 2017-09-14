package com.feng.com.rxjavade.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.feng.com.rxjavade.utils.BroadCastUtil;
import com.feng.com.rxjavade.utils.SpUtil;
import com.feng.com.rxjavade.utils.ToastUtil;

import static com.feng.com.rxjavade.app.config.AppConfig.AL_NOTIFICATION;
import static com.feng.com.rxjavade.app.config.SPKey.LOCK_SWITCH;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class LockReceiver_start extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.showNormalLongToast("LockReceiver_start--1---"+intent.getAction());
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            if (SpUtil.getBoolean(LOCK_SWITCH,true)) {
                Intent LockIntent = new Intent(context, MyLockScreenActivity.class);
                LockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(LockIntent);
            }
            BroadCastUtil.postBroadcast(context,AL_NOTIFICATION);
            context.startService(new Intent(context, LockService.class));
        } else  if (Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
            System.out.println("关机....");
            context.startService(new Intent(context, LockService.class));
        }else if(Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            context.startService(new Intent(context, LockService.class));
        }
    }
}
