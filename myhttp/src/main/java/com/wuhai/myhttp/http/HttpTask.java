package com.wuhai.myhttp.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * 在这里把两个接口合并
 */
public class HttpTask<T> implements Runnable{

    private IHttpRequest iHttpRequest;
    private IHttpListener iHttpListener;

    public HttpTask(String url, T requestData, IHttpRequest iHttpRequest, IHttpListener iHttpListener) {
        this.iHttpRequest = iHttpRequest;
        this.iHttpListener = iHttpListener;
        this.iHttpRequest.setUrl(url);
        this.iHttpRequest.setListener(iHttpListener);

        //这里可以写一个模板方法给子类用，来处理不同的HttpTask
        //(json xml protobuf)
        if(requestData != null){
            String dataStr = JSON.toJSONString(requestData);
            try {
                this.iHttpRequest.setParams(dataStr.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        this.iHttpRequest.execute();
    }
}
