package com.wuhai.myframe2.presenter;

import com.wuhai.myframe2.bean.ActivityHomeResult;
import com.wuhai.myframe2.contract.IHomeContract;
import com.wuhai.myframe2.network.APICallBack;
import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.myframe2.presenter.base.BasePresenter;
import com.wuhai.myframe2.utils.GsonUtils;
import com.wuhai.retrofit.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by wuhai on 2018/7/12.
 * 首页 P
 */

public class HomePresenter extends BasePresenter implements IHomeContract.Presenter{

    private IHomeContract.View mView;
    private ServiceProvider serviceProvider;

    public HomePresenter(IHomeContract.View mView){
        this.mView = mView;
        serviceProvider = new ServiceProvider();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void activityhome(final int refresh) {

        serviceProvider.activityhome(new APICallBack<ActivityHomeResult>() {
            @Override
            public void success(Call<ActivityHomeResult> call, Response<ActivityHomeResult> response) {
                ActivityHomeResult result = response.body();
                LogUtil.e(ServiceProvider.TAG,"onResponse:"+ GsonUtils.getInstance().toJson(result));

                if(result.getCode() == 1){
                    if(mView != null){
                        mView.setActivityhome(result.getResult());
                    }
                }else{
                    LogUtil.e(TAG, "errorMsg="+result.getMessage() );
                }
            }

            @Override
            public void error(int code, String errorMsg) {
                LogUtil.e(TAG, "code="+code+",errorMsg="+errorMsg );
            }

            @Override
            public void networkFailure(String networkFailureMsg) {
                LogUtil.e(TAG, "networkFailureMsg="+networkFailureMsg );
            }
        });

    }

}
