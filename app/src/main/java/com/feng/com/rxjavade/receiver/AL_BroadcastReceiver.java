package com.feng.com.rxjavade.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.alibaba.fastjson.JSONObject;
import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.activity.SplashActivity;
import com.feng.com.rxjavade.app.bean.LocalNewsItemBean;
import com.feng.com.rxjavade.app.bean.NotificationBean;
import com.feng.com.rxjavade.app.config.AppConfig;
import com.feng.com.rxjavade.lock.LockService;
import com.feng.com.rxjavade.utils.GetNotifityDataArtUtil;
import com.feng.com.rxjavade.utils.NetworkUtil;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by WHF on 2017-06-26.
 */

public class AL_BroadcastReceiver extends BroadcastReceiver {

    private RemoteViews remoteView;
    private NotificationCompat.Builder notifyBuilder;
    private NotificationBean notificationBean;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(AppConfig.AL_NOTIFICATION)) {
            NotificationBean getdata = getdata(1, context);
            String bean_url = "";
            if (getdata.getRect_thumb_meta() != null && !getdata.getRect_thumb_meta().isEmpty()) {
                bean_url = getdata.getRect_thumb_meta().get(0);
            }
            String nextAtr = GetNotifityDataArtUtil.getNextAtr(context);
            LocalNewsItemBean localNewsItemBean = JSONObject.parseObject(nextAtr, LocalNewsItemBean.class);

            Intent detailIntent = new Intent(context, SplashActivity.class);
            detailIntent.putExtra("isalnotifity", true);
            detailIntent.putExtra("news_type", "9");
            detailIntent.putExtra("id", getdata.getId());
            if (!NetworkUtil.checkNetWork(context)) {
                detailIntent.putExtra("articleDetail", nextAtr);
            }

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            intent.setAction(AppConfig.AL_NOTIFICATION);
            intent.putExtra("is_notification",true);

            PendingIntent btIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


            Intent no= new Intent(context, LockService.class);
            no.setAction(AppConfig.AL_NOTIFICATION_RE);
            PendingIntent deleteIntent = PendingIntent.getService(context, 0, no, PendingIntent.FLAG_UPDATE_CURRENT);


            remoteView = new RemoteViews(context.getPackageName(), R.layout.notify_layout);
            remoteView.setOnClickPendingIntent(R.id.notify_refresh, btIntent);
            remoteView.setTextViewText(R.id.notifi_txt_content, localNewsItemBean.getTitle());
            InputStream open = null;
            try {
                open = context.getAssets().open("localpicture/" + localNewsItemBean.getRect_thumb_meta().get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            remoteView.setImageViewBitmap(R.id.notification_img, bitmap);
            notifyBuilder = new NotificationCompat.Builder(context);
            notifyBuilder.setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(false)
                    .setContentIntent(pendingIntent)
                    // 将Ongoing设为true 那么notification将不能滑动删除
                    .setOngoing(true)
                    .setContent(remoteView)
                    // 从Android4.1开始，可以通过以下方法，设置notification的优先级，优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setTicker(context.getString(R.string.app_name))
                    .setDeleteIntent(deleteIntent);
            ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(AppConfig.NOTIFY_ID, notifyBuilder.build());
        }
    }

    private NotificationBean getdata(int count, Context context) {
        String nextAtr = GetNotifityDataArtUtil.getNextAtr(context);
        LocalNewsItemBean localNewsItemBean = JSONObject.parseObject(nextAtr, LocalNewsItemBean.class);
        notificationBean = new NotificationBean();
        notificationBean.setTitle(localNewsItemBean.getTitle());
        notificationBean.setRect_thumb_meta(localNewsItemBean.getRect_thumb_meta());
        notificationBean.setId(localNewsItemBean.getId());
        return notificationBean;
    }
}
