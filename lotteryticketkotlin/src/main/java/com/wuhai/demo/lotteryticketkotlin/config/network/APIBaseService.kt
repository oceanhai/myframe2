package com.wuhai.demo.lotteryticketkotlin.config.network

import com.wuhai.retrofit.retrofit.BaseApi

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/16 17:03
 *
 * 描述：创建网络接口
 */
abstract class APIBaseService {
    @JvmField
    var api = BaseApi.INSTANCE[API::class.java]
}