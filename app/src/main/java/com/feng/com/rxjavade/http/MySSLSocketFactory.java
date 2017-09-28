package com.feng.com.rxjavade.http;

import android.util.Log;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.MyApplication;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import static android.content.ContentValues.TAG;

/**
 * Created by WHF.Javas on 2017/9/26.
 */

public class MySSLSocketFactory {

//    private static volatile MySSLSocketFactory instance;
//    private static Context context;
//    private MySSLSocketFactory(){}
//    public static MySSLSocketFactory getInstance(){
//        if (instance != null) {
//            synchronized (MySSLSocketFactory.class){
//                context=MyApplication.getInstance();
//                instance=new MySSLSocketFactory();
//            }
//        }
//        return instance;
//    }

    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext.getSocketFactory();
    }

    public static SSLSocketFactory getSSLSocketFactoryT()  {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509","BC");
            InputStream caInput = MyApplication.getInstance().getResources().openRawResource(R.raw.app);
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                caInput.close();
            }
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
            return context.getSocketFactory();
        }catch (Exception e){
            Log.d(TAG, "getSSLSocketFactoryT: "+e.getMessage());
            return null;
        }

    }


}
