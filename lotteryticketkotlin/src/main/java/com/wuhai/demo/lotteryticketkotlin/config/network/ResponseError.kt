package com.wuhai.demo.lotteryticketkotlin.config.network

import com.google.gson.annotations.SerializedName

/**
 * 返回error
 * Created by fanchang on 2016/11/22.
 */
class ResponseError(exception: Throwable?, type: Int) : RuntimeException() {
    /**
     * 约定异常类型
     */
    var type = ErrorCode.UNKNOWN_ERROR

    /**
     * 原始error
     */
    private var exception: Throwable? = null

    //只有是业务异常的时候才会被赋值  业务code  TODO 毛线啊，这个地方onNext操作里抛异常也是会捕获到的
    var code = -1000

    @SerializedName("errMsg")
    var msg: String? = null

    override fun toString(): String {
        return "ResponseError{" +
                "type=" + type +
                ", exception=" + message +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}'
    }

    override val message: String
        get() = if (exception != null) exception?.cause.toString() + "," + exception?.message else ""

    /**
     * 约定异常
     */
    object ErrorCode {
        /**
         * code 异常
         */
        const val BUSINESS_ERROR = 999

        /**
         * 未知错误
         */
        const val UNKNOWN_ERROR = 1000

        /**
         * 解析错误
         */
        const val PARSE_ERROR = 1001

        /**
         * 用户验证异常？
         */
        const val AUTH_ERROR = 1002

        /**
         * 无网络错误
         */
        const val NOT_NETWORD_ERROR = 1003

        /**
         * http协议出错 如401等
         */
        const val HTTP_ERROR = 1004

        /**
         * 证书出错？
         */
        const val SSL_ERROR = 1005
    }

    init {
        this.type = type
        if (exception != null) {
            this.exception = exception
        }
    }
}