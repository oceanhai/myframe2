package com.wuhai.retrofit.retrofit;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * okhttp的实例
 * Created by fanchang on 2016/11/22.
 */
public enum OkHttpFactory {
    INSTANCE;
    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 15;
    private static final int TIMEOUT_CONNECTION = 15;

    OkHttpFactory() {
        NetProvider provider = BaseApi.INSTANCE.getProvider();

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        //打印请求Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        if (provider.configLogEnable()) {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        //缓存目录
//        Cache cache = new Cache(MyApplication.mContext.getCacheDir(), 10 * 1024 * 1024);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //time out
        builder.connectTimeout(provider.configConnectTimeoutMills() != 0
                ? provider.configConnectTimeoutMills()
                : TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        builder.readTimeout(provider.configReadTimeoutMills() != 0
                ? provider.configReadTimeoutMills() : TIMEOUT_READ, TimeUnit.SECONDS);

        CookieJar cookieJar = provider.configCookie();
        if (cookieJar != null) {
            builder.cookieJar(cookieJar);
        }

        //默认拦截器
        RequestHandler handler = provider.configHandler();
        if (handler != null) {
            builder.addInterceptor(new BaseHttpInterceptor(handler));
        }
        //其余的拦截器
        Interceptor[] interceptors = provider.configInterceptors();
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        //打印请求log
        if (provider.configLogEnable()) {
            builder.addInterceptor(logInterceptor);
        }

        //添加UA
//                .addInterceptor(new UserAgentInterceptor(HttpHelper.getUserAgent()))

        //必须是设置Cache目录
//                .cache(cache)

        //走缓存，两个都要设置
//                .addInterceptor(new OnOffLineCachedInterceptor())
//                .addNetworkInterceptor(new OnOffLineCachedInterceptor())

        //失败重连
//                .retryOnConnectionFailure(true)


        //设置https证书
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        //stetho,可以在chrome中查看请求
        if (provider.configLogEnable()) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        okHttpClient = builder.build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
