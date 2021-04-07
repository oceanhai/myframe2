package com.wuhai.demo.lotteryticketkotlin.model.interfaces

import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryRecord

/**
 * 作者 wh
 *
 * 创建日期 2020/2/14 17:53
 *
 * 描述：
 */
interface ILotteryRecordModel : IBaseModelInterFace {
    /**
     * 彩票
     * @param lotteryRecord
     */
    fun addLotteryRecord(lotteryRecord: LotteryRecord?)

    /**
     * 查询所有数据
     * @return
     */
    fun queryLotteryRecordAll(): List<LotteryRecord?>?

    /**
     * 查询
     * @param page
     * @param maxInPage     每页最大 数量
     * @return
     */
    fun queryLotteryRecord(page: Int, maxInPage: Int): List<LotteryRecord?>?
}