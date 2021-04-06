package com.wuhai.demo.lotteryticketkotlin.config.network

import com.wuhai.demo.lotteryticketkotlin.BuildConfig

object APIConstants {
    //test 开发环境
    var TEST_Url = "http://apis.juhe.cn/"

    //正式环境
    var RELEASE_Url = "http://apis.juhe.cn/"

    //网络请求
    var NetWorkBaseURL = ""

    //代码内 作为一种开关控制
    var DEBUG = true

    init {
        if (BuildConfig.DEBUG) {
            NetWorkBaseURL = TEST_Url
            DEBUG = true
        } else {
            NetWorkBaseURL = RELEASE_Url
            DEBUG = false
        }
    }
}