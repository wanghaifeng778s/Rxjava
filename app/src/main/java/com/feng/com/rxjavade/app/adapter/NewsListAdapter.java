package com.feng.com.rxjavade.app.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.app.holder.NewsOneImgHolder;
import com.feng.com.rxjavade.base.BaseAdapter;
import com.feng.com.rxjavade.base.BaseViewHolder;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsListAdapter extends BaseAdapter<DocsBean> {

    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public BaseViewHolder getHolder(int position, ViewGroup parent) {
        BaseViewHolder baseViewHolder=new NewsOneImgHolder(mContext,parent);
        return baseViewHolder;
    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        DocsBean docsBean = data.get(position);
//        String dType = docsBean.getDType();
//        if ("v".equals(dType)) {
//            return TYPE_1;//视频
//        } else {
//            return TYPE_4;//无图
//        }
//    }
}
