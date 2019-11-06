package com.wuhai.retrofit.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by fanchang on 2016/11/22.
 */
public enum RetrofitClient {
    INSTANCE;
    private final Retrofit retrofit;

    RetrofitClient() {
        NetProvider provider = BaseApi.INSTANCE.getProvider();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                //baseUrl
                .baseUrl(provider.configBaseUrl())
                //设置OKHttpClient
                .client(OkHttpFactory.INSTANCE.getOkHttpClient())
                //rxjava适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //gson转化器
                .addConverterFactory(provider.configConverterFactory())

                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
