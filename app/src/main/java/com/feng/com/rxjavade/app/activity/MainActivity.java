package com.feng.com.rxjavade.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.adapter.MyFragmentAdapter;
import com.feng.com.rxjavade.app.config.SPKey;
import com.feng.com.rxjavade.app.fragment.HomeFragment;
import com.feng.com.rxjavade.app.fragment.PersonalFragment;
import com.feng.com.rxjavade.app.fragment.VideoFragment;
import com.feng.com.rxjavade.base.BaseActivity;
import com.feng.com.rxjavade.utils.BroadCastUtil;
import com.feng.com.rxjavade.utils.SpUtil;
import com.feng.com.rxjavade.utils.ToastUtil;
import com.voler.cutlass.FragmentFactory;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

import static com.feng.com.rxjavade.app.config.AppConfig.AL_NOTIFICATION;

public class MainActivity extends BaseActivity {

    @Bind(R.id.vp_horizontal_ntb)
    ViewPager vpHorizontalNtb;
    @Bind(R.id.ntb_horizontal)
    NavigationTabBar ntbHorizontal;
    private MyFragmentAdapter fragmentAdapter;
    private long mExitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
        if (SpUtil.getBoolean(SPKey.NOTIFY_SWITCH, true)) {
            new Handler().postDelayed(() -> {
                BroadCastUtil.postBroadcast(this,AL_NOTIFICATION);
            }, 5000);
        }
    }

    private void initUI() {
        fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        HomeFragment homeFragment = FragmentFactory.createHomeFragment("12",9);
        VideoFragment videoFragment =FragmentFactory.createVideoFragment("3","2");
        PersonalFragment personalFragment =FragmentFactory.createPersonalFragment("11","wer");
        fragmentAdapter.addFragment(homeFragment);
        fragmentAdapter.addFragment(videoFragment);
        fragmentAdapter.addFragment(personalFragment);
        vpHorizontalNtb.setAdapter(fragmentAdapter);
        final String[] colors = getResources().getStringArray(R.array.default_preview);
        ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("HOME")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("VIDEO")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("PERSONAL")
                        .badgeTitle("icon")
                        .build()
        );
        ntbHorizontal.setModels(models);
        ntbHorizontal.setViewPager(vpHorizontalNtb, 0);
        ntbHorizontal.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ntbHorizontal.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ntbHorizontal.postDelayed(() -> {
            for (int i = 0; i < ntbHorizontal.getModels().size(); i++) {
                final NavigationTabBar.Model model = ntbHorizontal.getModels().get(i);
                ntbHorizontal.postDelayed(() -> {
                    model.showBadge();
                }, i * 100);
            }
        },500);
    }

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtil.showNormalShortToast(getResources().getString(R.string.click_two_exit_app));
                mExitTime = System.currentTimeMillis();

            } else {
                //退出应用
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startMain);
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
