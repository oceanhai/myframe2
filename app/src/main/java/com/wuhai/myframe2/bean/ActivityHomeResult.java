package com.wuhai.myframe2.bean;

/**
 * Created by wuhai on 2019/4/3.
 * 活动首页 Result
 */

public class ActivityHomeResult {

    private int code;
    private boolean success;
    private String message;
    private ActivityHomeEntity result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActivityHomeEntity getResult() {
        return result;
    }

    public void setResult(ActivityHomeEntity result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ActivityHomeResult{" +
                "code=" + code +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
