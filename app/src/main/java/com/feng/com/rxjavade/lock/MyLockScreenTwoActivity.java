package com.feng.com.rxjavade.lock;

import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.adapter.NewsListAdapter;
import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.base.BaseActivity;
import com.feng.com.rxjavade.utils.GetNotifityDataArtUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class MyLockScreenTwoActivity extends BaseActivity {

    private TextView tvData;
    private ListView vpNews;
    private List<DocsBean> mList;
    private SimpleDraweeView ivFinish;
    private float initX;
    private ImageView ivSetting;
    private int item;
    private RelativeLayout rlFinish;
    private NewsListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_lock_two);
        tvData = (TextView) findViewById(R.id.tv_data);
        vpNews = (ListView) findViewById(R.id.vp_news);
        ivSetting = (ImageView) findViewById(R.id.iv_setting);
        ivFinish = (SimpleDraweeView) findViewById(R.id.iv_finish);
        rlFinish = (RelativeLayout) findViewById(R.id.rl_finish);
        listAdapter=new NewsListAdapter(this);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res://"+getPackageName()+"/"+ R.drawable.lock))
                .setAutoPlayAnimations(true)
                .build();
        ivFinish.setController(draweeController);
        rlFinish.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        moveX = event.getX() - initX;
//                        requestLayout();
//                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (initX - event.getX() > 100) {
                            MyLockScreenTwoActivity.this.finish();
                        }
                        break;
                }
                return true;
            }
        });
        mList = new ArrayList<>();

        JSONArray localAtr = GetNotifityDataArtUtil.getLocalAtr(this);
        String s = localAtr.toJSONString();
        List<DocsBean> docs = JSONArray.parseArray(s, DocsBean.class);
        docs.addAll(0,docs);
        listAdapter.notifyDataSetChanged(0,docs);
        vpNews.setAdapter(listAdapter);
    }

    public String formatDateStampString() {
        long when = System.currentTimeMillis();
        int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT
                | DateUtils.FORMAT_ABBREV_ALL
                | DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_SHOW_WEEKDAY;
        return DateUtils.formatDateTime(this, when, format_flags);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvData.setText(formatDateStampString());
    }
}
