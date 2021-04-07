package com.wuhai.demo.lotteryticketkotlin.model.interfaces

import com.wuhai.demo.lotteryticketkotlin.model.bean.Lottery

/**
 * 作者 wh
 *
 * 创建日期 2020/2/14 17:53
 *
 * 描述：
 */
interface ILotteryModel : IBaseModelInterFace {
    /**
     * 彩票
     * @param lottery
     */
    fun addLottery(lottery: Lottery?)

    /**
     * 查询所有数据
     * @return
     */
    fun queryLotteryAll(): List<Lottery?>?

    /**
     * 删除
     * @param lottery
     */
    fun deleteLottery(lottery: Lottery?)
}