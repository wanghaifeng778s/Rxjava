package com.feng.com.rxjavade.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.fragment.HomePageFragment;
import com.feng.com.rxjavade.app.fragment.VideoPageFragment;
import com.feng.com.rxjavade.base.BaseActivity;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.DKGRAY,Color.RED)
                .addTabItem("第一项", R.mipmap.ic_launcher, HomePageFragment.class)
                .addTabItem("第二项", R.mipmap.ic_launcher, HomePageFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(false);
    }

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
