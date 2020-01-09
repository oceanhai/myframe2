package com.wuhai.lotteryticket.presenter;

import android.app.Dialog;

import com.wuhai.lotteryticket.config.network.RequestNetCallBack;
import com.wuhai.lotteryticket.config.network.RequestNetCallBack2;
import com.wuhai.lotteryticket.config.network.ResponseError;
import com.wuhai.lotteryticket.config.network.RootResponse;
import com.wuhai.lotteryticket.config.network.ServiceProvider;
import com.wuhai.lotteryticket.contract.IHomeContract;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.lotteryticket.presenter.base.BasePresenter;
import com.wuhai.retrofit.utils.LogUtil;


/**
 * Created by wuhai on 2018/7/12.
 * 首页 P
 */

public class HomePresenter extends BasePresenter
        implements IHomeContract.Presenter{

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
    public void lotteryQuerySsq(String key, String lottery_id, String lottery_no) {
        serviceProvider.lotteryQuery2(key, lottery_id, lottery_no,
                new RequestNetCallBack2<RootResponse<LotteryQueryEntity>>() {

                    @Override
                    public void onSuccess(RootResponse<LotteryQueryEntity> dataResponse) {

                        if(dataResponse.getError_code() == 0){
                            if(mView != null){
                                mView.setLotteryQuerySsq(dataResponse.getResult());
                            }
                        }else{
                            LogUtil.e(TAG, "errorMsg="+dataResponse.getReason() );
                        }
                    }

                    @Override
                    public void onFailure(ResponseError responseError) {
                        //TODO 自行封装 或者重写直接用父类  根据情况吧
                    }

                    @Override
                    public void showLoadingDialog() {
                        if(mView != null){
                            mView.showLoading();
                        }
                    }

                    @Override
                    public void disMissLoading() {
                        if(mView != null){
                            mView.dimssLoading();
                        }
                    }
                },mView.getLifecycleTransformer());
    }

    @Override
    public void lotteryQueryDlt(String key, String lottery_id, String lottery_no) {
        serviceProvider.lotteryQuery(key, lottery_id, lottery_no,
                new RequestNetCallBack<RootResponse<LotteryQueryEntity>>() {
                    @Override
                    public void onSuccess(RootResponse<LotteryQueryEntity> dataResponse) {

                        if(dataResponse.getError_code() == 0){
                            if(mView != null){
                                mView.setLotteryQueryDlt(dataResponse.getResult());
                            }
                        }else{
                            LogUtil.e(TAG, "errorMsg="+dataResponse.getReason() );
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
