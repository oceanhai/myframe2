package com.wuhai.demo.lotteryticketkotlin.config.network

import com.google.gson.annotations.SerializedName

/**
 * 接口数据通用返回格式
 */
class RootResponse<T> {
    @SerializedName("error_code")
    var error_code = 0

    @SerializedName("reason")
    var reason: String? = null

    @SerializedName("result")
    var result: T? = null

}