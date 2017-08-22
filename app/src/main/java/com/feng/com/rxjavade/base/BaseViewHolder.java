package com.feng.com.rxjavade.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import butterknife.ButterKnife;


public abstract class BaseViewHolder<T> {
    protected static final int MIN_CLICK_DELAY_TIME = 1000;
    protected long lastClickTime = 0;
    protected Context mContext;
    protected View mConvertView;
    protected BaseAdapter.OnViewClickListener mOnViewClickListener = null;
    protected int position;
    protected boolean isNight;

    public BaseViewHolder(Context context, ViewGroup parent) {
        mContext = context;
        mConvertView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        ButterKnife.bind(this, mConvertView);
        mConvertView.setTag(this);
    }

    public abstract void bindHolder(T dataModel);

    public abstract int getLayoutId();

    public View getConvertView(T dataModel, int position) {
        this.position = position;
        bindHolder(dataModel);
        return mConvertView;
    }

    public void setOnItemClickListener(BaseAdapter.OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

    public void onViewClick(View view) {
        if (mOnViewClickListener != null) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME){
                lastClickTime = currentTime;
                mOnViewClickListener.onViewClick(view, position);
            }
        }
    }

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }

    public void checkMode(boolean mode) {
        if (mode) {
            nightMode();
        } else {
            dayMode();
        }
    }

    protected void nightMode() {}

    protected void dayMode() {}
}
