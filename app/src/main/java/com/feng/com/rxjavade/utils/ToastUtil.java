package com.feng.com.rxjavade.utils;

import android.widget.Toast;

import com.feng.com.rxjavade.app.MyApplication;


/**
 * 吐司
 * @author yangshenghui
 *
 */
public class ToastUtil {


	public static void showNormalShortToast(CharSequence content) {
		Toast.makeText(MyApplication.getInstance(), content, Toast.LENGTH_SHORT).show();
	}

	public static void showNormalLongToast( CharSequence content) {
		Toast.makeText(MyApplication.getInstance(), content, Toast.LENGTH_LONG).show();
	}

	public static void showNormalShortToast( int resid) {
		Toast.makeText(MyApplication.getInstance(), resid, Toast.LENGTH_SHORT).show();
	}

	public static void showNormalLongToast(int resid) {
		Toast.makeText(MyApplication.getInstance(), resid, Toast.LENGTH_LONG).show();
	}
	


}
