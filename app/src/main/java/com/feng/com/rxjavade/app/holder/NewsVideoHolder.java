package com.feng.com.rxjavade.app.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.base.BaseViewHolder;

import butterknife.Bind;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsVideoHolder extends BaseViewHolder<DocsBean> {

    @Bind(R.id.item_video_img)
    SimpleDraweeView itemVideoImg;
    @Bind(R.id.item_video_com)
    TextView itemVideoCom;

    public NewsVideoHolder(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public void bindHolder(DocsBean dataModel) {

//        dataModel.getVideo_url()
        itemVideoImg.getHierarchy().setPlaceholderImage(R.mipmap.ic_launcher);

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_video_holder;
    }
}
