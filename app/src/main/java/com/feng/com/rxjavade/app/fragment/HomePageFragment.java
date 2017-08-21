package com.feng.com.rxjavade.app.fragment;

import android.os.Bundle;
import android.view.View;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.base.BaseFragment;
import com.voler.cutlass.Cutlass;
import com.voler.cutlass.annotation.InjectField;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class HomePageFragment extends BaseFragment {

    @InjectField
    String id;
    @InjectField
    int position;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Cutlass.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
