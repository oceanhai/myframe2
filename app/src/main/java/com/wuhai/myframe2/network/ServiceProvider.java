package com.wuhai.myframe2.network;


import com.wuhai.myframe2.bean.ActivityHomeResult;
import com.wuhai.myframe2.network.retrofit.APIBaseService;
import com.wuhai.retrofit.utils.LogUtil;

import retrofit2.Call;

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

}
