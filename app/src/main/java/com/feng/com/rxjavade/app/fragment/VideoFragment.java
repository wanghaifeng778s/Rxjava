package com.feng.com.rxjavade.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.base.BaseFragment;
import com.voler.cutlass.Cutlass;
import com.voler.cutlass.annotation.InjectField;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class VideoFragment extends BaseFragment {

    @InjectField
    String id;

    @InjectField
    String position;
    @Bind(R.id.btn_show)
    TextView btnShow;

    View inflate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cutlass.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video_page;
    }

    @Override
    protected void initData() {
         inflate = mContext.getLayoutInflater().inflate(R.layout.pop_test, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_show)
    public void onClick() {
//        SmartPopupWindow.Builder.build(mContext,inflate)
//                .se
    }
}
