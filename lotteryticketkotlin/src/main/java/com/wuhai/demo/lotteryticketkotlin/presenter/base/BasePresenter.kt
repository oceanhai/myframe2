package com.wuhai.demo.lotteryticketkotlin.presenter.base

import android.content.Context

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/14 17:58
 *
 * 描述：
 */
open class BasePresenter {
    //类名
    var TAG = javaClass.simpleName

    companion object {
        /**
         * 判断 code
         * @param context
         * @param code          接口返回的code
         * @param condition     条件0：不处理 1:4030下，登录后回上一页
         */
        fun judegeCode(context: Context?, code: Int, condition: Int): Boolean {
            var result = false
            when (code) {
                4030 -> result = true
            }
            return result
        }
    }
}