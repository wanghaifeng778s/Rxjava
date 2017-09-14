package com.feng.com.rxjavade.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.feng.com.rxjavade.app.activity.SinglePixelActivity;
import com.feng.com.rxjavade.lock.MyLockScreenActivity;

import java.lang.ref.WeakReference;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class ScreenManager {
    private Context mContext;

    private WeakReference<Activity> mActivityWref;

    public static ScreenManager gDefualt;

    public static ScreenManager getInstance(Context pContext) {
        if (gDefualt == null) {
            gDefualt = new ScreenManager(pContext.getApplicationContext());
        }
        return gDefualt;
    }
    private ScreenManager(Context pContext) {
        this.mContext = pContext;
    }

    public void setActivity(Activity pActivity) {
        mActivityWref = new WeakReference<Activity>(pActivity);
    }

    public void startActivity() {
        SinglePixelActivity.actionToSinglePixelActivity(mContext);
    }

    public void finishActivity() {
        //结束掉SinglePixelActivity
        if (mActivityWref != null) {
            Activity activity = mActivityWref.get();
            if (activity != null) {
                Intent LockIntent = new Intent(mContext, MyLockScreenActivity.class);
                LockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(LockIntent);
                activity.finish();
            }
        }
    }

}
