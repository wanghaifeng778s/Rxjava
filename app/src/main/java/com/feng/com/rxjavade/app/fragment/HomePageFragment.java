package com.feng.com.rxjavade.app.fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.base.BaseFragment;
import com.voler.cutlass.Cutlass;
import com.voler.cutlass.annotation.InjectField;

import de.greenrobot.event.EventBus;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class HomePageFragment extends BaseFragment {

    @InjectField
    String id;
    @InjectField
    int position;

    TextView context;
    Button click;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cutlass.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initData() {
    }



}
