package com.feng.com.rxjavade.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.utils.ScreenManager;

import static android.content.ContentValues.TAG;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class SinglePixelActivity extends Activity {

    public static void actionToSinglePixelActivity(Context pContext) {
        Intent intent = new Intent(pContext, SinglePixelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_singlepixel);
        Window window = getWindow();
        //放在左上角
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        //宽高设计为1个像素
        attributes.width = 1;
        attributes.height = 1;
        //起始坐标
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        ScreenManager.getInstance(this).setActivity(this);
    }
}
