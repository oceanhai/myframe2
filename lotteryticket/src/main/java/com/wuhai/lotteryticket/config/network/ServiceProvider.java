package com.wuhai.lotteryticket.config.network;


import android.util.Log;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.retrofit.retrofit.RxUtil;

import rx.Observable;

/**
 * Created by wuhai on 2018/5/16.
 */

public class ServiceProvider extends APIBaseService implements IServiceProvider {

    public static final String TAG = "qjj_network";

    @Override
    public void lotteryQuery(String key, String lottery_id, String lottery_no,RequestNetCallBack<RootResponse<LotteryQueryEntity>> callBack, LifecycleTransformer lifecycleTransformer) {
        Log.i(TAG,"<------ make lotteryQuery request ------>");
        ApiParams getApiParams = new ApiParams(ApiParams.Method.GET_PARMS)
                .with("key", key)
                .with("lottery_id", lottery_id)
                .with("lottery_no", lottery_no)
                .print();

        Observable<RootResponse<LotteryQueryEntity>> observable =  api.
                lotteryQuery(getApiParams);
        observable.compose(RxUtil.<RootResponse>normalSchedulers())
                .compose(lifecycleTransformer)
                .subscribe(callBack);
    }
}
