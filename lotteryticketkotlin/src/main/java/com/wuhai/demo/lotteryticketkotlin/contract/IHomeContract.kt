package com.wuhai.demo.lotteryticketkotlin.contract

import com.wuhai.demo.lotteryticketkotlin.contract.base.IBasePresenterInterFace
import com.wuhai.demo.lotteryticketkotlin.contract.base.INormalRxLifecycleViewInterFace
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity

/**
 * 作者 wuhai
 *
 * 创建日期 2018/7/12 9:41
 *
 * 描述：
 */
interface IHomeContract {
    interface View : INormalRxLifecycleViewInterFace {
        /**
         * 双色球结果
         */
        fun setLotteryQuerySsq(entity: LotteryQueryEntity?)

        /**
         * 超级大乐透 结果
         */
        fun setLotteryQueryDlt(entity: LotteryQueryEntity?)
    }

    interface Presenter : IBasePresenterInterFace {
        /**
         * 双色球
         */
        fun lotteryQuerySsq(key: String?, lottery_id: String?, lottery_no: String?)

        /**
         * 超级大乐透
         */
        fun lotteryQueryDlt(key: String?, lottery_id: String?, lottery_no: String?)
    }
}