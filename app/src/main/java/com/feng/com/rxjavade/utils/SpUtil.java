package com.feng.com.rxjavade.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.feng.com.rxjavade.app.MyApplication;
import com.feng.com.rxjavade.app.config.SPKey;


public class SpUtil {

    public static final String DEFAULT = "config";


    public static SharedPreferences getDefault(){
        return MyApplication.getInstance().getSharedPreferences(DEFAULT, Context.MODE_MULTI_PROCESS);
    }

    public static SharedPreferences.Editor getDefaultEditor(){
        return MyApplication.getInstance().getSharedPreferences(DEFAULT, Context.MODE_MULTI_PROCESS).edit();
    }


//    /**
//     * 向config中存储  JSONObject  数据
//     *
//     * @param name 数据key
//     * @param
//     */
//    public static void saveVale(String name, JSONObject jsonObject) {
//        getDefaultEditor()
//                .putString(name, jsonObject.toJSONString())
//                .commit();
//    }

//    public static JSONObject getJsonObject(Context context, String name) {
//        String jsonObject = context.getSharedPreferences(DEFAULT, Context.MODE_PRIVATE).getString(name, "");
//        return JSON.parseObject(jsonObject);
//    }

    /**
     * 向config中存储boolean型数据
     *
     * @param name         数据key
     * @param valueboolean 数据value
     */

    public static void saveValue(@SPKey.SPConfig String name, Boolean valueboolean) {
        getDefaultEditor()
                .putBoolean(name, valueboolean)
                .commit();
    }

    /**
     * 向config中存储int型数据
     *
     * @param name 数据key
     */
    public static void saveValue(@SPKey.SPConfig String name, int valueint) {
        getDefaultEditor()
                .putInt(name, valueint)
                .commit();
    }

    public static void saveValue(@SPKey.SPConfig String name, long valueint) {
        getDefaultEditor()
                .putLong(name, valueint)
                .commit();
    }

    /**
     * 向config中存储string型数据
     *
     * @param name 数据key
     */
    public static void saveValue(@SPKey.SPConfig String name, String valuestring) {
        getDefaultEditor()
                .putString(name, valuestring)
                .commit();
    }

    /**
     * 从config中得到boolean型数据
     *
     * @param name     数据key
     * @param defValue 默认boolean值
     */
    public static Boolean getBoolean(@SPKey.SPConfig String name, Boolean defValue) {
        return getDefault().getBoolean(name, defValue);
    }

    /**
     * 从config中得到int型数据
     *
     * @param name 数据key
     */
    public static int getInt(@SPKey.SPConfig String name) {
        return getDefault().getInt(name, 0);
    }

    public static long getLong(@SPKey.SPConfig String name) {
        return getDefault().getLong(name, 0l);
    }

    /**
     * 从config中得到string型数据
     *
     * @param name
     * @return
     */
    public static String getString(@SPKey.SPConfig String name) {
        return getDefault().getString(name, "");
    }

    public static String getString(@SPKey.SPConfig String name, String defortString) {
        return getDefault().getString(name, defortString);
    }

    public static void delKeyValue(@SPKey.SPConfig String key) {
        getDefaultEditor().remove(key).commit();

    }

}
