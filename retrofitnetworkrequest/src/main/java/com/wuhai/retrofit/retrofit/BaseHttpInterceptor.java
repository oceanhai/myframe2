package com.wuhai.retrofit.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * retrofit 拦截器  拦截任务交给RequestHandler
 * Created by fanchang on 2016/11/22.
 */
public class BaseHttpInterceptor implements Interceptor {
    RequestHandler handler;

    public BaseHttpInterceptor(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (handler != null) {
            request = handler.onBeforeRequest(request, chain);
        }
        Response response = chain.proceed(request);
        if (handler != null) {
            response = handler.onAfterRequest(response, chain);
        }
        return response;
    }
}
