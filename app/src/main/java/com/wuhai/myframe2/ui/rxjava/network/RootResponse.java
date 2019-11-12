package com.wuhai.myframe2.ui.rxjava.network;

import com.google.gson.annotations.SerializedName;

/**
 * 接口数据通用返回格式
 * Created by fanchang on 2016/11/22.
 */
public class RootResponse<T> {
    @SerializedName("code")
    private int code;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    T result;

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}