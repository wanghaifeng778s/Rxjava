package com.feng.com.rxjavade.app.config;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/4/5.
 */

public interface SPKey {


    @StringDef({CREATE_TIME, MODE, LOGIN_USER_ID, INSTANCE_TIME, SAVE_DATA, NIGHT_MODE,
            TITLEARRAY, ISMAN, USER_GUIDE, USER_IMEI, NOTIC, VIDEO_TITLE, FIRST_UPDATE, TITLE_SIZE, WEBVIEW_SIZE,
            REFRESH_TIME, COUNT, DOWNPUSH, NOTICETIME, MY_TEXTVIEW_SIZE,IS_DOWNLOAD,EXAMINATION,HAVE_POINT,EVERYDAY,OSN_TOKEN,FB_TOKEN,
            IS_SHORTCUT, LAST_OPEN,REFRESH_TIMES,MYLISTINDEX,SEARCH_HISTORY, LOCK_NEWS, LOCK_READ_ITEM, LOCK_UPDATE_TIME,LOCK_SWITCH
    ,SPLASH_ADV, ADV_END_TIME, ADV_REDIRECT, ADV_TYPE,SHOW_CONTENT,NOTIFICATION_COUNT, NOTIFICATION_CLICK_COUNT,
            NOTIFY_SWITCH,LOCK_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SPConfig {
    }


    String CREATE_TIME = "create_time";
    String NOTIFICATION_COUNT = "notification_count";
    String NOTIFICATION_CLICK_COUNT = "notification_click_count";
    String NOTIFY_SWITCH = "notify_switch";
    String FB_TOKEN = "fb_token";
    String OSN_TOKEN = "osn_token";
    String EXAMINATION = "Examination";
    String MYLISTINDEX = "mylistIndex";
    String IS_DOWNLOAD = "isdownload";
    String EVERYDAY = "everyday";
    String SHOW_CONTENT = "show_content";
    String MODE = "mode";
    // al String CLICK = "click";
// al String FIRST_OPEN = "first_open";
    String LOGIN_USER_ID = "login_user_id";
    String INSTANCE_TIME = "instance_time";
    String SAVE_DATA = "save_data";
    String NIGHT_MODE = "night_mode";
    String TITLEARRAY = "titlearray";
    String ISMAN = "isMan";
    String USER_GUIDE = "user_guide";
    String USER_IMEI = "user_imei";
    String NOTIC = "notic";
    String VIDEO_TITLE = "video_title";
    String FIRST_UPDATE = "first_update";
    String TITLE_SIZE = "title_size";
    String WEBVIEW_SIZE = "webview_size";
    String REFRESH_TIME = "refresh_time";
    String COUNT = "count";
    String DOWNPUSH = "downpush";
    String NOTICETIME = "noticetime";
    String MY_TEXTVIEW_SIZE = "my_textview_size";
    String HAVE_POINT = "have_point";
    String SEARCH_HISTORY = "search_history";
    String IS_SHORTCUT = "is_shortcut";
    String LAST_OPEN = "last_open_day";
    String REFRESH_TIMES = "refresh_times";

    String LOCK_UPDATE_TIME = "lock_update_time";
    String LOCK_NEWS = "lock_news";
    String LOCK_READ_ITEM = "lock_read_item";
    String LOCK_SWITCH = "lock_switch";
    String LOCK_TYPE = "lock_type";

    String SPLASH_ADV = "splash_adv";
    String ADV_END_TIME = "adv_end_time";
    String ADV_REDIRECT = "adv_redirect";
    String ADV_TYPE = "adv_type";
}
