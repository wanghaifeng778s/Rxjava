package com.feng.com.rxjavade.utils;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.feng.com.rxjavade.R;


/**
 * 软键盘隐藏弹出
 * @author yangshenghui
 *
 */
public class KeyboardUtil {

	/** 键盘类型 */
	public static enum KeyType {
		IDENTY, QWERTY;
	}

	private KeyboardView mKeyboardView;
	/** 小写全键盘 */
	private Keyboard mKeyboradQWERTYLower;
	/** 大小全键盘 */
	private Keyboard mKeyboardQWERTYUpper;
	/** 身份证键盘 */
	private Keyboard mKeyboradIdenty;
	/** 当前使用中的键盘 */
	private Keyboard mCurrKeyborad;
	/** 键盘类型 */
	private KeyType mKeyType;
	/** 当前操作的输入框 */
	private EditText mEditText;

	// public boolean isnun = false;// 是否数据键盘
	/** 是否大写 */
	public boolean mIsUpper = false;

	public boolean mIsKeyBoardShowing = false;

	private OnKeyboardActionListener mOnKeyboardActionListener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onPress(int primaryCode) {
			switch (primaryCode) {
			case Keyboard.KEYCODE_CANCEL:
			case Keyboard.KEYCODE_DONE:
			case Keyboard.KEYCODE_DELETE:
			case 32:// space
			case 57421:// ->
			case 57419:// <-
				mKeyboardView.setPreviewEnabled(false);
				break;
			case 88: // X
				if (mKeyboardView.getKeyboard() == mKeyboradIdenty) {
					mKeyboardView.setPreviewEnabled(false);
					break;
				}
			default:
				mKeyboardView.setPreviewEnabled(true);
				break;
			}
		}

		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			Editable editable = mEditText.getText();
			int start = mEditText.getSelectionStart();
			switch (primaryCode) {
			case Keyboard.KEYCODE_CANCEL:
			case Keyboard.KEYCODE_DONE:
				hideKeyboard();
				break;
			case Keyboard.KEYCODE_DELETE:
				if (editable != null && editable.length() > 0) {
					if (start > 0) {
						editable.delete(start - 1, start);
					}
				}
				break;
			case Keyboard.KEYCODE_SHIFT:
				toggleShift();
				break;
			case 57421:// go right
				if (start < mEditText.length()) {
					mEditText.setSelection(start + 1);
				}
				break;
			case 57419:// go left
				if (start > 0) {
					mEditText.setSelection(start - 1);
				}
				break;
			default:
				editable.insert(start, Character.toString((char) primaryCode));
				break;
			}

		}
	};

	/**
	 * @param Context
	 * @param keyboardView
	 *            android.inputmethodservice.KeyboardView
	 * @param type
	 *            {@link KeyType}
	 */
	public KeyboardUtil(Context ctx, KeyboardView keyboardView, KeyType type) {
		mKeyType = type;
		mKeyboradIdenty = new Keyboard(ctx, R.xml.trip_keyboard_identy);
		mKeyboradQWERTYLower = new Keyboard(ctx,
				R.xml.trip_keyboard_qwerty_lower);
		mKeyboardQWERTYUpper = new Keyboard(ctx,
				R.xml.trip_keyboard_qwerty_upper);

		switch (type) {
		case IDENTY:
			mCurrKeyborad = mKeyboradIdenty;
			break;
		case QWERTY:
			mCurrKeyborad = mKeyboradQWERTYLower;
			break;
		}

		mKeyboardView = keyboardView;
		mKeyboardView.setKeyboard(mCurrKeyborad);
		mKeyboardView.setEnabled(true);
		mKeyboardView.setPreviewEnabled(true);
		mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
	}

	public boolean isShow() {
		return mKeyboardView.getVisibility() == View.VISIBLE;
	}

	public boolean isAysncShow() {
		synchronized (this) {
			if (mIsKeyBoardShowing) {
				return true;
			}
		}
		return mKeyboardView.getVisibility() == View.VISIBLE;
	}

	public void showKeyboard() {
		mKeyboardView.postDelayed(new Runnable() {
			@Override
			public void run() {
				int visibility = mKeyboardView.getVisibility();
				if (visibility == View.GONE || visibility == View.INVISIBLE) {
					mKeyboardView.setVisibility(View.VISIBLE);
					if (mIsUpper) {
						toggleShift();
					}
				}
			}
		}, 200);
	}

	/**
	 * 需要改变控制EditText时调用此方法<br>
	 * qwerty键盘第一次打开时始终是小写键盘
	 * 
	 * @param type
	 *            {@link KeyType}
	 * @param et
	 */
	public void showKeyboard(final KeyType type, final EditText et,
			final boolean isUpper) {
		mIsUpper = isUpper;
		mKeyboardView.postDelayed(new Runnable() {
			@Override
			public void run() {
				KeyboardUtil.this.mEditText = et;
				switch (type) {
				case IDENTY:
					mCurrKeyborad = mKeyboradIdenty;
					break;
				case QWERTY:
					if (mIsUpper) {
						mCurrKeyborad = mKeyboardQWERTYUpper;
					} else {
						mCurrKeyborad = mKeyboradQWERTYLower;
					}
					break;
				}

				mKeyboardView.setKeyboard(mCurrKeyborad);
				int visibility = mKeyboardView.getVisibility();
				if (visibility == View.GONE || visibility == View.INVISIBLE) {
					mKeyboardView.setVisibility(View.VISIBLE);
				}
			}
		}, 200);
	}

	public void showKeyboard(final KeyType type, final EditText et,
			final boolean isUpper, boolean immediately) {
		synchronized (this) {
			mIsKeyBoardShowing = true;
		}
		mIsUpper = isUpper;
		Runnable runable = new Runnable() {

			@Override
			public void run() {
				KeyboardUtil.this.mEditText = et;
				switch (type) {
				case IDENTY:
					mCurrKeyborad = mKeyboradIdenty;
					break;
				case QWERTY:
					if (mIsUpper) {
						mCurrKeyborad = mKeyboardQWERTYUpper;
					} else {
						mCurrKeyborad = mKeyboradQWERTYLower;
					}
					break;
				}

				mKeyboardView.setKeyboard(mCurrKeyborad);
				int visibility = mKeyboardView.getVisibility();
				if (visibility == View.GONE || visibility == View.INVISIBLE) {
					synchronized (this) {
						if (mIsKeyBoardShowing) {
							mKeyboardView.setVisibility(View.VISIBLE);
						}
					}
				}
			}
		};
		if (immediately) {
			runable.run();
		} else {
			mKeyboardView.postDelayed(runable, 200);// 异步会导致反复切换输入框焦点，从而出现两个输入框的
		}
	}

	/**
	 * 关闭键盘
	 */
	public void hideKeyboard() {
		int visibility = mKeyboardView.getVisibility();
		if (visibility == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
		}
		synchronized (this) {
			mIsKeyBoardShowing = false;
		}
	}

	public void destroy() {
		if (mKeyboardView != null) {
			mKeyboardView.closing();
		}
	}

	// private boolean isWord(String str) {
	// String wordstr = "abcdefghijklmnopqrstuvwxyz";
	// if (wordstr.indexOf(str.toLowerCase()) > -1) {
	// return true;
	// }
	// return false;
	// }

	/**
	 * 键盘大小写切换
	 */
	public void toggleShift() {
		if (mIsUpper) {
			mKeyboardView.setKeyboard(mKeyboradQWERTYLower);
			mCurrKeyborad = mKeyboradQWERTYLower;
		} else {
			mKeyboardView.setKeyboard(mKeyboardQWERTYUpper);
			mCurrKeyborad = mKeyboardQWERTYUpper;
		}
		mKeyboardView.setEnabled(true);
		mKeyboardView.setPreviewEnabled(true);
		mIsUpper = !mIsUpper;
	}

	public KeyType getKeyType() {
		return mKeyType;
	}


	public static void showKeyBoard(final EditText editText) {
		editText.postDelayed(new Runnable() {

			@Override
			public void run() {
				InputMethodManager inputManager =

				(InputMethodManager) editText.getContext().getSystemService(
						Context.INPUT_METHOD_SERVICE);

				inputManager.showSoftInput(editText, 0);
			}
		}, 200);
	}
	public static void showKeyBoardIme(final EditText editText) {
		InputMethodManager inputManager =
		(InputMethodManager) editText.getContext().getSystemService(
						Context.INPUT_METHOD_SERVICE);

				inputManager.showSoftInput(editText, 0);
	}
	public static void hideKeyBoard(Activity mAct){
		if(mAct!=null){
			View view = mAct.getWindow().peekDecorView();
	        if (view != null) {
	            InputMethodManager inputmanger = (InputMethodManager) mAct.getSystemService(Context.INPUT_METHOD_SERVICE);
	            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
	        }
		}
	}
	public static void showKeyBoard(Context context){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
	}
}
