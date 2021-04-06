package com.wuhai.demo.lotteryticketkotlin.contract.base

import com.trello.rxlifecycle.LifecycleTransformer

/**
 * view 基类
 */
interface INormalRxLifecycleViewInterFace {
    /**
     * 加载loading
     */
    fun showLoading()

    /**
     * 隐藏loading
     */
    fun dimssLoading()

    /**
     * ac 销毁时候，取消订阅
     * @return
     */
    val lifecycleTransformer: LifecycleTransformer<*>?
}