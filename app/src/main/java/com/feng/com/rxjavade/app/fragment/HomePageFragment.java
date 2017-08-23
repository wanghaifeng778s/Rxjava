package com.feng.com.rxjavade.app.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.bean.DocsBean;
import com.feng.com.rxjavade.app.bean.NewsItemBean;
import com.feng.com.rxjavade.base.BaseFragment;
import com.feng.com.rxjavade.http.Api;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.voler.cutlass.Cutlass;
import com.voler.cutlass.annotation.InjectField;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public class HomePageFragment extends BaseFragment implements OnRefreshListener, OnRefreshLoadmoreListener {

    @InjectField
    String id;
    @InjectField
    int position;
    @Bind(R.id.list_data)
    ListView listData;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


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
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        getData();
    }

    private void getData() {
        Api.getComApi().getNewsList("123123")
                .subscribeOn(Schedulers.io())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    List<DocsBean> docs = ((NewsItemBean) o).getDocs();
                    Log.d("RRRR",docs.get(0).getShareUrl());
                },Throwable::printStackTrace);
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(2000);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(2000);
    }
}
