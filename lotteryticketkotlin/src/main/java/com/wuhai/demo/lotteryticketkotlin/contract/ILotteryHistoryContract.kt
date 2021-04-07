package com.wuhai.demo.lotteryticketkotlin.contract

import com.wuhai.demo.lotteryticketkotlin.contract.base.IBasePresenterInterFace
import com.wuhai.demo.lotteryticketkotlin.contract.base.INormalRxLifecycleViewInterFace
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistoryEntity

/**
 * 作者 wuhai
 *
 * 创建日期 2018/7/12 9:41
 *
 * 描述：
 */
interface ILotteryHistoryContract {
    interface View : INormalRxLifecycleViewInterFace {
        /**
         * 历史开奖结果查询
         */
        fun setLotteryHistory(entity: LotteryHistoryEntity?)
    }

    interface Presenter : IBasePresenterInterFace {
        /**
         * 历史开奖结果查询
         */
        fun lotteryHistory(lottery_id: String?, page: Int, page_size: Int)
    }
}