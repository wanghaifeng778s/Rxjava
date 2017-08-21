package com.feng.com.rxjavade.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.utils.KeyboardUtil;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by WHF.Javas on 2017/8/21.
 */

public abstract class BaseFragment extends RxFragment {
    protected Activity mActivity;
    /**
     * 获得全局的，防止使用getActivity()为空
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 添加Fragment到堆栈
     * @param target 目标Fragment
     */
    public void addToBackStack(Fragment target) {
        KeyboardUtil.hideKeyBoard(getActivity());
        String className = target.getClass().getName();
        addToBackStack(getActivity(), className, target);
    }


    public  static void addToBackStack(FragmentActivity context, String fragmentTag, Fragment fragment) {
        disableFragmentView(true,context);
        FragmentManager manager = context.getSupportFragmentManager();

        manager.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_left_in,
                        R.anim.slide_right_out)
                .add(R.id.content_fragment, fragment,fragmentTag)
                .addToBackStack(fragmentTag).commitAllowingStateLoss();
        disableContentFragment(context, true);
    }

    public static void disableContentFragment(FragmentActivity context, boolean disable) {
        View view = context.findViewById(R.id.content_fragment);
        if(view!=null) { view.setClickable(disable); }
    }


    /**
     * add by renxiaomin  获取对顶部的fragment（当前激活的）
     * @param context
     * @return
     */
    public static BaseFragment getActiveFragment(Context context) {
        BaseFragment mActiveFragment = null;
        FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
        int nCount = manager.getBackStackEntryCount();

        if (nCount > 0) {
            String tag = manager.getBackStackEntryAt(nCount - 1).getName();
            mActiveFragment = (BaseFragment) manager
                    .findFragmentByTag(tag);
        }

        return mActiveFragment;
    }


    public  static void disableFragmentView(boolean disable, FragmentActivity context) {
        Fragment lastElement =  getActiveFragment(context);
        if(lastElement != null && lastElement.isAdded()) {
            View view = lastElement.getView();
            if(view != null) {
                view.setClickable(!disable);
            }
        }
    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();
}
