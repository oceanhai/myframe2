package com.wuhai.retrofit.retrofit;

import android.text.TextUtils;

/**
 * Created by fanchang on 2016/12/24.
 */
public enum BaseApi {
    INSTANCE;
    private NetProvider provider = null;

    BaseApi() {
    }

    /**
     * 获得对应  Retrofit实例
     * @param service
     * @param <S>
     * @return
     */
    public <S> S get(Class<S> service) {
        checkProvider();
        return RetrofitClient.INSTANCE.getRetrofit().create(service);
    }

    /**
     * 实例化provider   用于全局配置
     * @param provider
     */
    public void registerProvider(NetProvider provider) {
        this.provider = provider;
    }

    /**
     * 检查provider  必须实例化才可以使用
     */
    private void checkProvider() {
        if (provider == null
                || TextUtils.isEmpty(provider.configBaseUrl())) {
            throw new IllegalStateException("must register provider first");
        }
    }

    public NetProvider getProvider() {
        checkProvider();
        return provider;
    }
}
