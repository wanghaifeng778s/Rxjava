package com.feng.com.rxjavade.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.http.Api;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WHF.Javas on 2017/9/15.
 */

public class WindowUtils {
    private static final String LOG_TAG = "WindowUtils";
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    public static Boolean isShown = false;
    private static ArrayList<DocsBean> mList;


    public static void init(final Context context){
        mList=new ArrayList<>();
        loadMore(context);
    }

    /**
     * 显示弹出框
     *
     * @param context
     */
    private static void showPopupWindow(final Context context) {
        if (isShown) {
            Log.i(LOG_TAG, "return cause already shown");
            return;
        }
        isShown = true;
        Log.i(LOG_TAG, "showPopupWindow");
        // 获取应用的Context
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        mView = setUpView(context);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 类型
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        // 设置flag
        int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mView, params);
        Log.i(LOG_TAG, "add view");
    }

    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
        Log.i(LOG_TAG, "hide " + isShown + ", " + mView);
        if (isShown && null != mView) {
            Log.i(LOG_TAG, "hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }
    private static View setUpView(final Context context) {
        Log.i(LOG_TAG, "setUp view");
        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
        ImageView imgClose = (ImageView)view.findViewById(R.id.img_close);
        SimpleDraweeView ivImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvCommentCount = (TextView) view.findViewById(R.id.tv_comment_count);
        DocsBean docsBean = mList.get(0);
        if (docsBean.getImg_url().size()>0&&docsBean.getImg_url()!=null) {
            String image = docsBean.getImg_url().get(0);
            ivImage.setImageURI(Uri.parse(image));
        }

        tvTitle.setText(docsBean.getTitle());
        tvContent.setText(docsBean.getContent());
        tvCommentCount.setText(docsBean.getComment_count());
        imgClose.setOnClickListener(v -> {
            WindowUtils.hidePopupWindow();
        });

//        // 点击窗口外部区域可消除
//        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
//        // 所以点击内容区域外部视为点击悬浮窗外部
//        final View popupWindowView = view.findViewById(R.id.popup_window);// 非透明的内容区域
//        view.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                LogUtil.i(LOG_TAG, "onTouch");
//                int x = (int) event.getX();
//                int y = (int) event.getY();
//                Rect rect = new Rect();
//                popupWindowView.getGlobalVisibleRect(rect);
//                if (!rect.contains(x, y)) {
//                    WindowUtils.hidePopupWindow();
//                }
//                LogUtil.i(LOG_TAG, "onTouch : " + x + ", " + y + ", rect: "
//                        + rect);
//                return false;
//            }
//        });
        // 点击back键可消除
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        WindowUtils.hidePopupWindow();
                        return true;
                    default:
                        return false;
                }
            }
        });
        return view;
    }
    private static void loadMore(Context context) {
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
                    showPopupWindow(context);

                },Throwable::printStackTrace);
    }

}
