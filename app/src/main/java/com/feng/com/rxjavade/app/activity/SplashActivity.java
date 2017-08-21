package com.feng.com.rxjavade.app.activity;

import android.os.Bundle;
import android.os.Handler;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class SplashActivity extends BaseActivity{

    private Subscription subscribe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        subscribe = Observable.timer(3300, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> {
                    goToMainActivity();
                });
//        new Handler().postDelayed(() -> {
//            goToMainActivity();
//        },2000);
    }

    private void goToMainActivity() {
        if (subscribe != null && !subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
        }
        MainActivity.startActivity(this);
        finish();
    }
}
