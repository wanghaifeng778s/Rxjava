package com.feng.com.rxjavade.utils;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feng.com.rxjavade.R;

import java.util.Random;

/**
 * Created by WHF.Javas on 2017/9/11.
 */

public class GetNotifityDataArtUtil {
    public static String getNextAtr(Context mContext) {
        JSONArray content  = FileUtils.getInstance().getLocalJsonArray(mContext, R.raw.article);
        JSONObject jsonObject = content.getJSONObject(new Random().nextInt(9));
        JSONObject articleDetail = jsonObject.getJSONObject("articleDetail");
        return articleDetail.toString();
    }
    public static JSONArray getLocalAtr(Context mContext) {
        JSONArray content  = FileUtils.getInstance().getLocalJsonArray(mContext, R.raw.myarticle);
        return content;
    }
}
