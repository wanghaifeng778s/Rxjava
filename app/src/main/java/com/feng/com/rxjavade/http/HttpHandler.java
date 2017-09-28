package com.feng.com.rxjavade.http;

import android.util.Log;

import com.feng.com.rxjavade.utils.SignUtil;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HttpHandler {
    private Request request;
    private HttpUrl.Builder authorizedUrlBuilder;

    private static final String X_CA_REQUEST_MODE = "X-Ca-Request-Mode";
    private static final String X_Ca_Stage = "X-Ca-Stage";
    private static final String X_Ca_Key = "X-Ca-Key";
    private static final String Accept = "Accept";
    private static final String X_Ca_Timestamp = "X-Ca-Timestamp";
    private static final String X_Ca_Signature = "X-Ca-Signature";
    private static final String X_Ca_Signature_Headers = "X-Ca-Signature-Headers";
    public static final String TAG = "HttpHandler-----";
    private final static String APP_SECRET = "adedf24243b6ae4e0e0bee109593d193";

    private static final String h1="debug";
    private static final String h2="TEST";
    private static final String h3="24634617";
    private static final String h4="application/json";
    private static  String h5="";
    private static final String h6=" X-Ca-Timestamp,X-Ca-Request-Mode,X-Ca-Stage,X-Ca-Key";


    Response onResponse(String httpResult, Interceptor.Chain chain, Response response) {
        return response;
    }

    Response onRequest(Interceptor.Chain chain) throws IOException {
        h5=String.valueOf(System.currentTimeMillis());
        request = chain.request();
        authorizedUrlBuilder = request.url().newBuilder()
                .addQueryParameter("mos", "1")
                .addQueryParameter("mver", "2.0.9.070104.111")
                .addQueryParameter("net", "1")
                .addQueryParameter("appname", "RozBuzzPlus")
                .addQueryParameter("dcid", "1000")
                .addQueryParameter("uid", "0ee65010de574771b83734d75694a9c2")
                .addQueryParameter("lang", "0")
                .addQueryParameter("cid", "700")
                .scheme(request.url().scheme())
                .host(request.url().host());

        request = request.newBuilder()
                .header(X_CA_REQUEST_MODE, h1)
                .header(X_Ca_Stage, h2)
                .header(X_Ca_Key, h3)
                .header(Accept, h4)
                .header(X_Ca_Timestamp, h5)
                .header(X_Ca_Signature_Headers, h6)
                .header(X_Ca_Signature, getSign())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(request);
    }

    private String getSign() {
        URI uri = authorizedUrlBuilder.build().uri();
        HashMap<String, String> urlParams = getUrlParams(uri.toString());
        HashMap<String, String> headerParams = getHeaderParams();
        String path = authorizedUrlBuilder.build().encodedPath();
        HashMap<String, String> bodyParams = getBodyParams(request);
        String sign = SignUtil.sign(APP_SECRET, request.method(),path, headerParams, urlParams, bodyParams, signHeaderPrefixList());
        if (sign!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(sign);
            sign = m.replaceAll("");
        }
        Log.d(TAG, "getSign-----------------" + sign+"time------"+h5+"****"+path);
        return sign;
    }

    private HashMap<String, String> getHeaderParams() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(X_CA_REQUEST_MODE, h1);
        hashMap.put(X_Ca_Stage, h2);
        hashMap.put(X_Ca_Key, h3);
        hashMap.put(Accept, h4);
        hashMap.put(X_Ca_Timestamp, h5);
        Log.d(TAG, "getHeaderParams------------" + hashMap.size());
        return hashMap;
    }

    private HashMap<String, String> getUrlParams(String url) {
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        HashMap<String, String> map = new HashMap<>();
        for (String str : keyValue) {
            String[] split = str.split("=");
            map.put(split[0], split[1]);
            Log.d(TAG, "getUrlParams------------" + str);
        }
        return map;
    }

    private List<String> signHeaderPrefixList(){
        List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();
        CUSTOM_HEADERS_TO_SIGN_PREFIX.clear();
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("X-Ca-Request-Mode");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("X-Ca-Stage");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("X-Ca-Key");
        return CUSTOM_HEADERS_TO_SIGN_PREFIX;
    }
    private HashMap<String ,String > getBodyParams(Request request) {
        HashMap<String ,String > body_params=new HashMap<>();
        FormBody body_s = (FormBody)request.body();
        if (body_s.size()>0){
            for (int i = 0; i < body_s.size(); i++) {
                body_params.put(body_s.encodedName(i),body_s.encodedValue(i));
            }
            return body_params;
        }else {
            return null;
        }

    }
}
