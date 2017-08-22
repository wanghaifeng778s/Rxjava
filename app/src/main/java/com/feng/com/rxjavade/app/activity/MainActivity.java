package com.feng.com.rxjavade.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends BaseActivity {

    @Bind(R.id.vp_horizontal_ntb)
    ViewPager vpHorizontalNtb;
    @Bind(R.id.ntb_horizontal)
    NavigationTabBar ntbHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();

    }

    private void initUI() {


        View view_home = LayoutInflater.from(getBaseContext()).inflate(R.layout.fragment_home_page, null, false);
        View view_video = LayoutInflater.from(getBaseContext()).inflate(R.layout.fragment_video_page, null, false);
        View view_personal= LayoutInflater.from(getBaseContext()).inflate(R.layout.fragment_personal_page, null, false);
        ArrayList<View> fragment_list = new ArrayList<>();
        fragment_list.add(view_home);
        fragment_list.add(view_video);
        fragment_list.add(view_personal);
        vpHorizontalNtb.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(fragment_list.get(position));
                return fragment_list.get(position);
            }
        });
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
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("VIDEO")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
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
}
