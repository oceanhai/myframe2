package com.wuhai.myframe2.bean;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 17:57
 *
 * 描述：用户详情 result
 * ※无特殊说明情况下    code 默认 1：成功  2：失败
 */
public class UserDetailEntityResult {

    private int code;
    private boolean success;
    private String message;
    private UserDetailEntity result;

    public UserDetailEntity getResult() {
        return result;
    }

    public void setResult(UserDetailEntity result) {
        this.result = result;
    }

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
}
