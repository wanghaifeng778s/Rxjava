package com.feng.com.rxjavade.app.fragment;

import android.os.Bundle;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.base.BaseFragment;
import com.voler.cutlass.Cutlass;
import com.voler.cutlass.annotation.InjectField;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class HomeFragment extends BaseFragment{
    @InjectField
    String id;
    @InjectField
    int position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cutlass.inject(this);
//        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
