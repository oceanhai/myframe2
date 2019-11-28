package com.wuhai.myframe2.ui.retrofit.networknormalrx;

import com.google.gson.annotations.SerializedName;

/**
 * 接口数据通用返回格式
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