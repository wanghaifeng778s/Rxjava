package com.feng.com.rxjavade.utils;

import android.content.Context;
import android.provider.Settings;

import com.feng.com.rxjavade.app.MyApplication;

/**
 * Created by WHF.Javas on 2017/9/14.
 */

public enum  GetPhoneInfoUtil {
    INSTANCE;
    private String androidId;
    private String networkType;
    private Context mContext;

    GetPhoneInfoUtil(){
        initPhoneInfo();
    }

    private void initPhoneInfo() {
        mContext = MyApplication.getInstance();
        androidId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public String getAndroidId() {
        return androidId;
    }
}
