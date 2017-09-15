package com.feng.com.rxjavade.lock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.base.BaseActivity;
import com.feng.com.rxjavade.http.Api;
import com.feng.com.rxjavade.utils.GetNotifityDataArtUtil;
import com.feng.com.rxjavade.utils.GetPhoneInfoUtil;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class MyLockScreenActivity extends BaseActivity {

    private TextView tvData;
    private ViewPager vpNews;
    private List<DocsBean> mList;
    private SimpleDraweeView ivFinish;
    private float initX;
    private ImageView ivSetting;
    private int item;
    private RelativeLayout rlFinish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_lock);
        tvData = (TextView) findViewById(R.id.tv_data);
        vpNews = (ViewPager) findViewById(R.id.vp_news);
        ivSetting = (ImageView) findViewById(R.id.iv_setting);
        ivFinish = (SimpleDraweeView) findViewById(R.id.iv_finish);
        rlFinish = (RelativeLayout) findViewById(R.id.rl_finish);

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res://"+getPackageName()+"/"+ R.drawable.lock))
                .setAutoPlayAnimations(true)
                .build();
        ivFinish.setController(draweeController);//"file:///android_asset/localpicture"
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
                            MyLockScreenActivity.this.finish();
                        }
                        break;
                }
                return true;
            }
        });
        mList = new ArrayList<>();
        vpNews.setOffscreenPageLimit(2);
        vpNews.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                item= position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        JSONArray localAtr = GetNotifityDataArtUtil.getLocalAtr(this);
        String s = localAtr.toJSONString();
        List<DocsBean> docs = JSONArray.parseArray(s, DocsBean.class);
        mList.addAll(0, docs);
        vpNews.setAdapter(new MyAdapter(this));
        vpNews.setCurrentItem(0);//jkhfs
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

    class MyAdapter extends PagerAdapter {


        private final Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
        }

        /**
         * PagerAdapter管理数据大小
         */
        @Override
        public int getCount() {
            return mList.size()+1;
        }

        /**
         * 关联key 与 obj是否相等，即是否为同一个对象
         */
        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj; // key
        }

        /**
         * 销毁当前page的相隔2个及2个以上的item时调用
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object); // 将view 类型 的object熊容器中移除,根据key
        }

        /**
         * 当前的page的前一页和后一页也会被调用，如果还没有调用或者已经调用了destroyItem
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view ;
            if (position==mList.size()){
                view = View.inflate(mContext, R.layout.item_lock_last, null);
                SimpleDraweeView load = (SimpleDraweeView) view.findViewById(R.id.img_load);
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse("res://"+getPackageName()+ "/"+R.drawable.img_new_loading))
                        .setAutoPlayAnimations(true)
                        .build();
                load.setController(draweeController);
                loadMore();
            }else{
                view = View.inflate(mContext, R.layout.item_lock_normal, null);
                SimpleDraweeView ivImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
                TextView tvCommentCount = (TextView) view.findViewById(R.id.tv_comment_count);
                DocsBean docsBean = mList.get(position);
                if (docsBean.getImg_url().size()>0&&docsBean.getImg_url()!=null) {
                    String image = docsBean.getImg_url().get(0);
                    ivImage.setImageURI(Uri.parse(image));
                }

                tvTitle.setText(docsBean.getTitle());
                tvContent.setText(docsBean.getContent());
                tvCommentCount.setText(docsBean.getComment_count());
            }
            container.addView(view);
            return view; // 返回该view对象，作为key
        }
    }

    private void loadMore() {
        Api.getComApi().getNewsList(GetPhoneInfoUtil.INSTANCE.getAndroidId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsItemBean -> {
                    List<DocsBean> docs = newsItemBean.getDocs();
                    for (DocsBean doc : docs) {
                        if ("n".equals(doc.getDType())) {
                            List<String> img_url = doc.getImg_url();
                            if (img_url != null && img_url.size() > 0) {
                                mList.add(doc);
                            }
                        }
                    }
                    vpNews.setAdapter(new MyAdapter(this));
                    vpNews.setCurrentItem(item);//jkhfs
                },Throwable::printStackTrace);
    }

}
