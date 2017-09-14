package com.feng.com.rxjavade.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class BroadCastUtil {
    public static void postBroadcast(Context context,String flag) {
        Intent intent = new Intent();
        intent.putExtra("is_notification",false);
        intent.setAction(flag);
        context.sendOrderedBroadcast(intent,null);
    }
}
