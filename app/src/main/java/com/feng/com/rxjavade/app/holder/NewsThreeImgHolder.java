package com.feng.com.rxjavade.app.holder;

import android.content.Context;
import android.view.ViewGroup;

import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.base.BaseViewHolder;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsThreeImgHolder extends BaseViewHolder<DocsBean> {

    public NewsThreeImgHolder(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public void bindHolder(DocsBean dataModel) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
