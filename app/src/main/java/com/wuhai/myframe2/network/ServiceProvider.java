package com.wuhai.myframe2.network;


import com.trello.rxlifecycle.LifecycleTransformer;
import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.bean.ActivityHomeResult;
import com.wuhai.myframe2.bean.UserDetailEntity;
import com.wuhai.myframe2.ui.retrofit.base.APIBaseService;
import com.wuhai.myframe2.ui.retrofit.base.ApiParams;
import com.wuhai.myframe2.ui.retrofit.base.IServiceProvider;
import com.wuhai.myframe2.ui.retrofit.bean.BannerResult;
import com.wuhai.myframe2.ui.retrofit.networknormal.APICallBack;
import com.wuhai.myframe2.ui.retrofit.networknormal.ProxyCallBack;
import com.wuhai.myframe2.ui.rxjava.RxJavaActivity;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RequestNetCallBack;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RootResponse;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RxUtil;
import com.wuhai.retrofit.utils.LogUtil;

import retrofit2.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuhai on 2018/5/16.
 */

public class ServiceProvider extends APIBaseService implements IServiceProvider {

    public static final String TAG = "qjj_network";

    @Override
    public void activityhome(APICallBack<ActivityHomeResult> callback) {
        LogUtil.i(TAG,"<------ make activityhome request ------>");
        ApiParams getApiParams = new ApiParams(ApiParams.Method.GET_PARMS)
//                .with("token", "")//TODO 我们试着在拦截器添加token
                .print();

        Call<ActivityHomeResult> call =  api.
                activityhome(getApiParams);
        call.enqueue(new ProxyCallBack<ActivityHomeResult>().setApiCallBack(callback));
    }

    @Override
    public void activityhomeRx(RequestNetCallBack callBack) {
        LogUtil.i(TAG,"<------ make activityhomeRx request ------>");
        ApiParams getApiParams = new ApiParams(ApiParams.Method.GET_PARMS)
//                .with("token", "")//TODO 我们试着在拦截器添加token
                .print();

        Observable<RootResponse<ActivityHomeEntity>> observable =  api.
                activityhomeRx(getApiParams);

        observable.compose(RxUtil.<RootResponse>normalSchedulers())
                .subscribe(callBack);
    }

    @Override
    public void activityhomeRx2() {
        LogUtil.i(TAG,"<------ make activityhomeRx2 request ------>");
        ApiParams getApiParams = new ApiParams(ApiParams.Method.GET_PARMS)
//                .with("token", "")//TODO 我们试着在拦截器添加token
                .print();

        Observable<ActivityHomeResult> observable =  api.
                activityhomeRx2(getApiParams);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ActivityHomeResult>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(RxJavaActivity.TAG, "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(RxJavaActivity.TAG, "onError():"+e.getMessage());
                    }

                    @Override
                    public void onNext(ActivityHomeResult activityHomeResult) {
                        LogUtil.e(RxJavaActivity.TAG, "onNext():activityHomeResult="+activityHomeResult.toString());
                    }
                });
    }

    /**
     * 相较于上面的activityhomeRx  多了个rl
     */
    @Override
    public void activityhomeRxRl(RequestNetCallBack<RootResponse<ActivityHomeEntity>> callBack, LifecycleTransformer lifecycleTransformer) {
        LogUtil.i(TAG,"<------ make activityhomeRx request ------>");
        ApiParams getApiParams = new ApiParams(ApiParams.Method.GET_PARMS)
//                .with("token", "")//TODO 我们试着在拦截器添加token
                .print();

        Observable<RootResponse<ActivityHomeEntity>> observable =  api.
                activityhomeRx(getApiParams);

        observable.compose(RxUtil.<RootResponse>normalSchedulers())
                .compose(lifecycleTransformer)
                .subscribe(callBack);
    }

    @Override
    public void detail(RequestNetCallBack<RootResponse<UserDetailEntity>> callBack, LifecycleTransformer lifecycleTransformer) {
        LogUtil.i(TAG,"<------ make detail request ------>");
        ApiParams getApiParams = new ApiParams(ApiParams.Method.GET_PARMS)
//                .with("token", "")//TODO 我们试着在拦截器添加token
                .print();

        Observable<RootResponse<UserDetailEntity>> observable =  api.
                detail(getApiParams);

        observable.compose(RxUtil.<RootResponse>normalSchedulers())
                .compose(lifecycleTransformer)
                .subscribe(callBack);
    }

    @Override
    public void getBanners(Subscriber subscriber, LifecycleTransformer lifecycleTransformer) {
        Observable<BannerResult> observable = api.getBanners();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleTransformer)
                .subscribe(subscriber);
    }

}
