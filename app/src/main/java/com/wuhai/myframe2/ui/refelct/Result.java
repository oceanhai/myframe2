package com.wuhai.myframe2.ui.refelct;

public class Result {

    /***
     * 返回结果code
     * ok:10000
     * error:20000
     */
    private String code;

    /***
     * 返回描述
     */
    private String msg;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
