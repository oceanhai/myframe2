package com.wuhai.myframe2.presenter;

import android.app.Dialog;

import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.contract.IRetrofitRxJavaRxLifecycleContract;
import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.myframe2.presenter.base.BasePresenter;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RequestNetCallBack;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.ResponseError;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RootResponse;
import com.wuhai.retrofit.utils.LogUtil;


/**
 * Created by wuhai on 2018/7/12.
 * 首页 P
 */

public class RetrofitRxJavaRxLifecyclePresenter extends BasePresenter
        implements IRetrofitRxJavaRxLifecycleContract.Presenter{

    private IRetrofitRxJavaRxLifecycleContract.View mView;
    private ServiceProvider serviceProvider;

    public RetrofitRxJavaRxLifecyclePresenter(IRetrofitRxJavaRxLifecycleContract.View mView){
        this.mView = mView;
        serviceProvider = new ServiceProvider();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void activityhome(final int refresh) {
        serviceProvider.activityhomeRxRl(new RequestNetCallBack<RootResponse<ActivityHomeEntity>>() {
            @Override
            public void onSuccess(RootResponse<ActivityHomeEntity> dataResponse) {

                if(dataResponse.getCode() == 1){
                    if(mView != null){
                        mView.setActivityhome(dataResponse.getResult());
                    }
                }else{
                    LogUtil.e(TAG, "errorMsg="+dataResponse.getMessage() );
                }
            }

            @Override
            public void onFailure(ResponseError responseError) {
                //TODO 自行封装 或者重写直接用父类  根据情况吧
            }

            @Override
            public Dialog baseGetLoadingDialog() {
                return null;
            }
        },mView.getLifecycleTransformer());

    }

}
