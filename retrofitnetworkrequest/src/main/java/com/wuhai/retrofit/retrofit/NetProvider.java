package com.wuhai.retrofit.retrofit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import retrofit2.Converter;

/**
 * 提供网络请求的全局配置
 * Created by fanchang on 2016/12/24.
 */

public interface NetProvider {
    /**
     * 配置基础url
     *
     * @return
     */
    String configBaseUrl();

    /**
     * 配置转化器
     *
     * @return
     */
    Converter.Factory configConverterFactory();

    /**
     * 配置拦截器
     *
     * @return
     */
    Interceptor[] configInterceptors();

    /**
     * 配置cookiejar
     *
     * @return
     */
    CookieJar configCookie();

    /**
     * request和result 的拦截处理  添加公共参数等操作
     *
     * @return
     */
    RequestHandler configHandler();

    /**
     * 配置连接超时
     *
     * @return
     */
    long configConnectTimeoutMills();

    /**
     * 配置读取超时
     *
     * @return
     */
    long configReadTimeoutMills();

    /**
     * 配置是否启用log和开启stetho调试
     *
     * @return
     */
    boolean configLogEnable();
    

    /**
     * success code
     *
     * @return
     */
    int configSuccessCode();
}
