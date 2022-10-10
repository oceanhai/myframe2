package com.wuhai.rxjava2;

import java.util.List;

public class BannerResult {

    private int errorCode;
    private String errorMsg;
    private List<Banner> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<Banner> getData() {
        return data;
    }

    public void setData(List<Banner> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BannerResult{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
