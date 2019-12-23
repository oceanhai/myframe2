package com.wuhai.lotteryticket.contract.base;

import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * view 基类
 */
public interface INormalRxLifecycleViewInterFace {

    /**
     * 加载loading
     */
    void showLoading();

    /**
     * 隐藏loading
     */
    void dimssLoading();

    /**
     * ac 销毁时候，取消订阅
     * @return
     */
    LifecycleTransformer getLifecycleTransformer();
}
