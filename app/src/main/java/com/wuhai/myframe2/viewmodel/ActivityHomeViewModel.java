package com.wuhai.myframe2.viewmodel;

import android.app.Activity;

import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.bean.ActivityHomeResult;
import com.wuhai.myframe2.databinding.AcMvvmBinding;
import com.wuhai.myframe2.network.APICallBack;
import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.retrofit.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Response;

public class ActivityHomeViewModel {

    public Activity activity;

    public ActivityHomeEntity entity;

    public AcMvvmBinding acMvvmBinding;

    private ServiceProvider mServiceProvider;

    public ActivityHomeViewModel(Activity activity, AcMvvmBinding acMvvmBinding) {
        this.activity = activity;
        this.acMvvmBinding = acMvvmBinding;

        mServiceProvider = new ServiceProvider();

        init();

    }

    private void init() {
        mServiceProvider.activityhome(new APICallBack<ActivityHomeResult>() {
            @Override
            public void success(Call<ActivityHomeResult> call, Response<ActivityHomeResult> response) {
                entity = response.body().getResult();
                acMvvmBinding.setHome(entity);
            }

            @Override
            public void error(int code, String errorMsg) {
                LogUtil.e(ServiceProvider.TAG, "code="+code+",errorMsg="+errorMsg );
            }

            @Override
            public void networkFailure(String networkFailureMsg) {
                LogUtil.e(ServiceProvider.TAG, "networkFailureMsg="+networkFailureMsg );
            }
        });
    }

}
