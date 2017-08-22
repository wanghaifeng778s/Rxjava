package com.feng.com.rxjavade.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    protected OnViewClickListener mOnItemClickListener = null;
    protected List<T> data;
    protected Context mContext;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
        data = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract BaseViewHolder getHolder(int position, ViewGroup parent);

    public void changeData(T bean) {
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T dataModel = data.get(position);
        changeData(dataModel);
        BaseViewHolder holder;
        Boolean mode = false;
        if (convertView != null) {
            holder = (BaseViewHolder) convertView.getTag();
            if (holder.isNight() ^ mode) {
                holder.checkMode(mode);
            }
        } else {
            holder = getHolder(position, parent);
            holder.checkMode(mode);
        }
        holder.setNight(mode);

        holder.setOnItemClickListener(mOnItemClickListener);
        return holder.getConvertView(dataModel, position);
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> list) {
        data = list;
    }

    public interface OnViewClickListener {
        void onViewClick(View view, int position);
    }

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    /**
     * 更新列表
     */
    public void notifyDataSetChanged(int controlData, List<T> list) {
        if (list == null) {
            return;
        }
        if (controlData < 0) {
            list.addAll(this.data);
            this.data = list;
        } else if (controlData == 0) {
            this.data = list;
        } else {
            this.data.addAll(list);
        }
        super.notifyDataSetChanged();
    }


}
