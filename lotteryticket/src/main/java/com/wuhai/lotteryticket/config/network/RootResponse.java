package com.wuhai.lotteryticket.config.network;

import com.google.gson.annotations.SerializedName;

/**
 * 接口数据通用返回格式
 */
public class RootResponse<T> {
    @SerializedName("error_code")
    private int error_code;

    @SerializedName("reason")
    private String reason;

    @SerializedName("result")
    T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}