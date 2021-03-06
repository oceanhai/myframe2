package com.wuhai.demo.lotteryticketkotlin.contract.base

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/14 18:16
 *
 * 描述：P层
 */
interface IBasePresenterInterFace {
    /**
     * 释放view的资源
     * @params
     * @return
     */
    fun onDestroy()
}