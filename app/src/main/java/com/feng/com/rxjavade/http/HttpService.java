package com.feng.com.rxjavade.http;

import com.feng.com.rxjavade.app.config.Config;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class HttpService {

    public static final String BASE_URL= Config.HOST;

    public static final int TIME_OUT=10;

    private static Retrofit retrofit;

    private static void getRetrofit(){
        Timber.plant(new Timber.DebugTree());
        OkHttpClient client= null;
        client = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(chain -> new HttpHandler().onRequest(chain))
                .addNetworkInterceptor(new RequestInterceptor(new HttpHandler()))
//                    .sslSocketFactory(MySSLSocketFactory.getSSLSocketFactory())
                .sslSocketFactory(MySSLSocketFactory.getSSLSocketFactoryT())
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public static <T> T create(Class<T> service){
        if (retrofit == null) {
            getRetrofit();
        }
        return retrofit.create(service);
    }
}
