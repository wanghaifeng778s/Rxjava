package com.feng.com.rxjavade.app.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.base.BaseAdapter;
import com.feng.com.rxjavade.base.BaseViewHolder;

import java.util.List;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsListAdapter extends BaseAdapter<DocsBean> {

    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;


    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public BaseViewHolder getHolder(int position, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        BaseViewHolder baseViewHolder=null;
        switch (itemViewType){
            case TYPE_1:

                break;
            case TYPE_2:

                break;
            case TYPE_3:

                break;
            case TYPE_4:

                break;
        }

        return baseViewHolder;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        DocsBean docsBean = data.get(position);
        String dType = docsBean.getDType();
        List<String> img_url = docsBean.getImg_url();
        if ("v".equals(dType)) {
            return TYPE_1;//视频
        } else {
            if (img_url != null && img_url.size() > 0) {
                if (img_url.size() > 2) {
                    return TYPE_2;//三图
                } else {
                    return TYPE_4;//单图
                }
            } else {
                return TYPE_3;//无图
            }
        }
    }
}
