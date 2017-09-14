package com.feng.com.rxjavade.app.fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.com.rxjavade.R;
import com.feng.com.rxjavade.app.config.AppConfig;
import com.feng.com.rxjavade.base.BaseFragment;
import com.feng.com.rxjavade.lock.LockService;
import com.feng.com.rxjavade.review.SwitchView;
import com.feng.com.rxjavade.utils.BroadCastUtil;
import com.feng.com.rxjavade.utils.SpUtil;
import com.voler.cutlass.Cutlass;
import com.voler.cutlass.annotation.InjectField;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.feng.com.rxjavade.app.config.AppConfig.AL_NOTIFICATION;
import static com.feng.com.rxjavade.app.config.SPKey.LOCK_SWITCH;
import static com.feng.com.rxjavade.app.config.SPKey.LOCK_TYPE;
import static com.feng.com.rxjavade.app.config.SPKey.NOTIFY_SWITCH;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class PersonalFragment extends BaseFragment {

    @InjectField
    String id;

    @InjectField
    String position;
    @Bind(R.id.sw_lock)
    SwitchView swLock;
    @Bind(R.id.sw_notification)
    SwitchView swNotification;
    @Bind(R.id.sw_type)
    SwitchView swType;
    private NotificationManager mNotificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cutlass.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_personal_page;
    }

    @Override
    protected void initData() {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        swLock.toggleSwitch(SpUtil.getBoolean(LOCK_SWITCH, true));
        swNotification.toggleSwitch(SpUtil.getBoolean(NOTIFY_SWITCH, true));
        swType.toggleSwitch(SpUtil.getBoolean(LOCK_TYPE, false));

        swNotification.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true);
                SpUtil.saveValue(NOTIFY_SWITCH, true);
                BroadCastUtil.postBroadcast(mContext,AL_NOTIFICATION);
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false);
                SpUtil.saveValue(NOTIFY_SWITCH, false);
                mNotificationManager.cancel(AppConfig.NOTIFY_ID);
            }
        });
        swLock.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true);
                SpUtil.saveValue(LOCK_SWITCH, true);
                mContext.startService(new Intent(mContext, LockService.class));
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false);
                SpUtil.saveValue(LOCK_SWITCH, false);
                mContext.stopService(new Intent(mContext, LockService.class));
            }
        });
        swType.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true);
                SpUtil.saveValue(LOCK_TYPE, true);
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false);
                SpUtil.saveValue(LOCK_TYPE, false);
            }
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
}
