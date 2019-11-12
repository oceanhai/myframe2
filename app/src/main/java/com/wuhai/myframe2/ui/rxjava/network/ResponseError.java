package com.wuhai.myframe2.ui.rxjava.network;


import com.google.gson.annotations.SerializedName;

/**
 * 返回error
 * Created by fanchang on 2016/11/22.
 */
public class ResponseError extends RuntimeException {
    /**
     * 约定异常类型
     */
    private int type = ErrorCode.UNKNOWN_ERROR;
    /**
     * 原始error
     */
    private Throwable exception;
    //只有是业务异常的时候才会被赋值  业务code
    private int code = -1000;

    @SerializedName("errMsg")
    private String msg;

    public ResponseError(Throwable exception, int type) {
        this.type = type;
        if (exception != null) {
            this.exception = exception;
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "type=" + type +
                ", exception=" + getMessage() +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        if (exception != null) return exception.getMessage();
        return "";
    }

    /**
     * 约定异常
     */
    public static class ErrorCode {
        /**
         * code 异常
         */
        public static final int BUSINESS_ERROR = 999;
        /**
         * 未知错误
         */
        public static final int UNKNOWN_ERROR = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 用户验证异常？
         */
        public static final int AUTH_ERROR = 1002;
        /**
         * 无网络错误
         */
        public static final int NOT_NETWORD_ERROR = 1003;
        /**
         * http协议出错 如401等
         */
        public static final int HTTP_ERROR = 1004;

        /**
         * 证书出错？
         */
        public static final int SSL_ERROR = 1005;
    }
}
