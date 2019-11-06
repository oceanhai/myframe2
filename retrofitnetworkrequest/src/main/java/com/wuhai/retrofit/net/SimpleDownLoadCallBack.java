package com.wuhai.retrofit.net;

import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by wangC on 2017/1/19.
 * 这个是基于三方框架的
 */

public class SimpleDownLoadCallBack extends FileCallBack {





    public SimpleDownLoadCallBack(String destFileDir, String destFileName) {
        super(destFileDir, destFileName);
    }

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
    public void onResponse(File response, int id) {
        if(requestCallBack!=null){
            requestCallBack.lct_downFileResponse( response, "");
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
        if(requestCallBack!=null){
            requestCallBack.lct_inProccess(progress,total );
        }

    }
}
