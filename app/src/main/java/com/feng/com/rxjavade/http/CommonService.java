package com.feng.com.rxjavade.http;

import com.feng.com.rxjavade.app.bean.NewsItemBean;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public interface CommonService {

    @GET("http://47.91.65.163:17621/recom/doc.do?appid=2&topDocKey=zxTopDoc")
    Observable<NewsItemBean> getNewsList(@Query("devid") String devid);

    @GET("https://irrational.cn/lolu/article/pullup")
    Observable<ResponseBody> getSth();
}
