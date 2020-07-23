package com.wuhai.myhttp.http;

/**
 * 给用户使用的
 */
public interface IDataListener<T> {
    void onSuccess(T t);
    void onFailure();
}
