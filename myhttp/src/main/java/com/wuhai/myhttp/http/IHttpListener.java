package com.wuhai.myhttp.http;

import java.io.InputStream;

/**
 * 响应的顶层接口
 */
public interface IHttpListener {
    void onSuccess(InputStream inputStream);
    void onFailure();
}
