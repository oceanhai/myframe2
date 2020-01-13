package com.wuhai.lotteryticket.presenter;

import com.wuhai.lotteryticket.config.network.RequestNetCallBack2;
import com.wuhai.lotteryticket.config.network.ResponseError;
import com.wuhai.lotteryticket.config.network.RootResponse;
import com.wuhai.lotteryticket.config.network.ServiceProvider;
import com.wuhai.lotteryticket.contract.ILotteryHistoryContract;
import com.wuhai.lotteryticket.model.bean.LotteryHistoryEntity;
import com.wuhai.lotteryticket.presenter.base.BasePresenter;
import com.wuhai.retrofit.utils.LogUtil;


/**
 * Created by wuhai on 2018/7/12.
 * 首页 P
 */

public class LotteryHistoryPresenter extends BasePresenter
        implements ILotteryHistoryContract.Presenter{

    private ILotteryHistoryContract.View mView;
    private ServiceProvider serviceProvider;

    public LotteryHistoryPresenter(ILotteryHistoryContract.View mView){
        this.mView = mView;
        serviceProvider = new ServiceProvider();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void lotteryHistory(String lottery_id, int page, int page_size) {
        serviceProvider.lotteryHistory(lottery_id, page, page_size,
                new RequestNetCallBack2<RootResponse<LotteryHistoryEntity>>() {

                    @Override
                    public void onSuccess(RootResponse<LotteryHistoryEntity> dataResponse) {

                        if(dataResponse.getError_code() == 0){
                            if(mView != null){
                                mView.setLotteryHistory(dataResponse.getResult());
                            }
                        }else{
                            LogUtil.e(TAG, "errorMsg="+dataResponse.getReason() );
                        }
                    }

                    @Override
                    public void onFailure(ResponseError responseError) {
                        super.onFailure(responseError);

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

}
