package com.feng.com.rxjavade.listener;

import android.webkit.WebView;

/**
 * Created by hlyd on 14-1-27.
 */
public interface OnWebViewUIExecuteListener {

    public void onUIStartedExecute(WebView webView, String url);

    public void onUIFinishedExecute(WebView webView, String url);
}
