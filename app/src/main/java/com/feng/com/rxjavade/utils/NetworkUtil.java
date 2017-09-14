package com.feng.com.rxjavade.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feng.com.rxjavade.listener.OnWebViewUIExecuteListener;

import java.util.List;


/**
 * Created by hlyd on 14-1-19.
 */
public class NetworkUtil {
	private static OnWebViewUIExecuteListener mOnWebViewUIExecuteListener;

	public static void setOnWebViewUIExecuteListener(
			OnWebViewUIExecuteListener onWebViewUIExecuteListener) {
		mOnWebViewUIExecuteListener = onWebViewUIExecuteListener;
	}

	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 清理WebView缓存
	 */
	public static void clearWebViewAllCache(WebView webView) {
		if (webView == null) {
			return;
		}
		webView.clearCache(true);
		webView.clearHistory();
		webView.clearFormData();
	}

	/**
	 * 配置WebView在嵌入的视图框架中显示
	 */
	public static void configWebViewSettingsToEmbed(WebView webDetail) {
		webDetail.setWebViewClient(new CustomWebViewClient());
		// webDetail.getSettings().setPluginsEnabled(true);
		// 使能缩放
		webDetail.getSettings().setSupportZoom(true);
		webDetail.getSettings().setBuiltInZoomControls(true);
		// 使能JS
		webDetail.getSettings().setJavaScriptEnabled(true);
		webDetail.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webDetail.getSettings().setAllowFileAccess(true);
		webDetail.getSettings().setDomStorageEnabled(true);
		webDetail.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
	}

	/**
	 * 自定义WebViewClient
	 */
	public static class CustomWebViewClient extends WebViewClient {
		public CustomWebViewClient() {
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (mOnWebViewUIExecuteListener != null) {
				mOnWebViewUIExecuteListener.onUIFinishedExecute(view, url);
			}
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			if (mOnWebViewUIExecuteListener != null) {
				mOnWebViewUIExecuteListener.onUIStartedExecute(view, url);
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.endsWith(".mp4")) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.parse(url), "video/*");
				if (view != null && view.getContext() != null) {
					view.getContext().startActivity(intent);
				}
				return true;
			} else {
				return super.shouldOverrideUrlLoading(view, url);
			}
		}
	}

//	/**
//	 * 网络是否可用
//	 * 
//	 * @param activity
//	 * @return
//	 */
//	public static boolean isNetworkAvailable(Context context) {
//		ConnectivityManager connectivity = (ConnectivityManager) context
//				.getSystemService(Context.CONNECTIVITY_SERVICE);
//		if (connectivity == null) {
//		} else {
//			NetworkInfo[] info = connectivity.getAllNetworkInfo();
//			if (info != null) {
//				for (int i = 0; i < info.length; i++) {
//					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}

//	/**
//	 * 网络连接提示
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public static void networkStateTips(Context context) {
//		if (!isNetworkAvailable(context)) {
//			ToastUtil.showShortToast(context, "网络故障，请先检查网络连接");
//		}
//	}

	/**
	 * Gps是否打开
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager locationManager = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = locationManager.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * wifi是否打开
	 */
	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager mgrConn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
				.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	/**
	 * 判断当前网络是否是wifi网络
	 * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) { //判断3G网
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 判断当前网络是否是3G网络
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
	 * 
	 * @param context
	 * @return
	 */
	public static int isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return 0;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return 1;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							String extraInfo = netWorkInfo.getExtraInfo();
							if ("cmwap".equalsIgnoreCase(extraInfo)
									|| "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
								return 2;
							}
							return 3;
						}
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * 检测网络是否连接
	 * @param context
	 * @return
	 */
	public static boolean checkNetWork(Context context){
		// 1.获得连接设备管理器
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null){
			return false;
		}
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if(ni == null || !ni.isAvailable()){
			return false;
		}
		return true;
	}
}
