package com.feng.com.rxjavade.app.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.base.BaseFragment;
import com.feng.com.rxjavade.http.Api;
import com.feng.com.rxjavade.utils.ToastUtil;
import com.voler.cutlass.Cutlass;
import com.voler.cutlass.annotation.InjectField;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class HomeFragment extends BaseFragment {
    @InjectField
    String id;
    @InjectField
    int position;
    @Bind(R.id.click)
    Button click;
    @Bind(R.id.context)
    TextView context;

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

    private void getData() {
        Api.getComApi().getSth()
                .subscribeOn(Schedulers.io())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
//                    Log.d("*********",responseBody.toString()+"");
                    ToastUtil.showNormalLongToast("ooooooooooooo");
                }, throwable -> {
                    Log.d("********", "getData: "+throwable.getMessage());
                    ToastUtil.showNormalLongToast(throwable.getMessage() + "********");
                });
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

    @OnClick(R.id.click)
    public void onClick() {
        getData();
    }
}
