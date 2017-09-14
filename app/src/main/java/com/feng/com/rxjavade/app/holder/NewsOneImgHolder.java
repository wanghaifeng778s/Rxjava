package com.feng.com.rxjavade.app.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.base.BaseViewHolder;

import butterknife.Bind;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsOneImgHolder extends BaseViewHolder<DocsBean> {

    @Bind(R.id.image1)
    SimpleDraweeView image1;
    @Bind(R.id.title1)
    TextView title1;
    @Bind(R.id.bottom_view_style1)
    TextView bottomViewStyle1;
    @Bind(R.id.style1)
    LinearLayout style1;

    public NewsOneImgHolder(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public void bindHolder(DocsBean dataModel) {
        title1.setText(dataModel.getTitle());
        image1.setImageURI(dataModel.getImg_url().get(0).toString());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_news_one_image;
    }
}
