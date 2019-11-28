package com.wuhai.myframe2.ui.retrofit.networknormal;


import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.retrofit.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wuhai on 2018/6/28.
 * 我们这做个代理，对返回数据进行一下封装
 */

public class ProxyCallBack<T> implements Callback<T>{

    protected static final String ERROR_SERVER = "服务器异常";
    protected static final String ERROR_SERVER_CODE_ERR = "服务器开小差了，请稍后重试。";
    protected static final String ERROR_RESPOND_NETWORK = "获取数据错误，请检查网络";

    //qpy回调
    private APICallBack<T> apiCallBack;

    /**
     * 设置回调接口
     * @param apiCallBack
     */
    public ProxyCallBack setApiCallBack(APICallBack apiCallBack){
        this.apiCallBack = apiCallBack;
        return this;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(apiCallBack == null){
            return;
        }

        if(response != null){
            LogUtil.e(ServiceProvider.TAG,"rawResponse = " + response.raw());
        }else{
            apiCallBack.error(-1, ERROR_SERVER);
            LogUtil.e(ServiceProvider.TAG, "error = " + ERROR_SERVER);
            return;
        }

        if(!response.isSuccessful()){
            apiCallBack.error(-2, ERROR_SERVER_CODE_ERR);
            LogUtil.e(ServiceProvider.TAG, "error = " + ERROR_SERVER_CODE_ERR);
            return;
        }

        //TODO 因为这里用了泛型，所以不能打印返回数据结果呢，￣□￣｜｜
        apiCallBack.success(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(apiCallBack != null){
            apiCallBack.networkFailure(ERROR_RESPOND_NETWORK);
            LogUtil.e(ServiceProvider.TAG, "networkFailure = " + t.getMessage());
        }
    }
}
