package com.wuhai.myhttp.http;

/**
 * 请求的顶层接口
 */
public interface IHttpRequest {
    void setUrl(String url);
    void setParams(byte[] params);
    void execute();
    //两个接口需要链接在一起
    void setListener(IHttpListener iHttpListener);
}
