package com.feng.com.rxjavade.http;

import android.os.Build;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HttpHandler {
    Response onResponse(String httpResult, Interceptor.Chain chain, Response response) {
        return response;
    }

    Response onRequest(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .header("Server-Version", "2.0")
                .header("User-Token", "")
                .header("App-Version", "")
                .header("Access-Token", "")
                .header("User-Agent", "Android")
                .header("Unit-Type", Build.MODEL)
                .header("Sys-Version", Build.VERSION.RELEASE+"")
                .build();
        return chain.proceed(request);
    }
}
