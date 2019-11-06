package com.wuhai.retrofit.net;


import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;


/**
 * 基于ok 的回调 会使用lct的回调作为一次转发
 *  这个是基于第三方的框架
 */

public class SimpleGetOrGetCallBack extends StringCallback {

    //lct回调
    private LctCallBackInterFace requestCallBack;

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if(requestCallBack!=null){
            requestCallBack.lct_before( request .url().toString() +"开始请求");
        }
    }

    //设置回调监听
    public void setRequestCallBack(LctCallBackInterFace requestCallBack){
        this.requestCallBack=requestCallBack;

    }

    @Override
    public void onError(Call call, Exception e, int id) {
        if(requestCallBack!=null){
            requestCallBack.lct_onFailed(call.request().url().toString() , e);
        }
    }

    @Override
    public void onResponse(String response, int id) {
        if(requestCallBack!=null){
            requestCallBack.lct_onSuccess(response);
        }
    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        if(requestCallBack!=null){
            requestCallBack.lct_after("----请求结束----");
        }
    }

    @Override
    public void inProgress(float progress, long total, int id) {
        super.inProgress(progress, total, id);

    }
}

