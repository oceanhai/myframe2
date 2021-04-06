package com.wuhai.demo.lotteryticketkotlin.model.bean

/**
 * 作者 wh
 *
 * 创建日期 2020/1/10 18:08
 *
 * 描述：彩票历史数据entity
 */
class LotteryHistoryEntity {
    /**
     * "page":1,
     * "pageSize":10,
     * "totalPage":252
     * lotteryResList   LotteryHistory集合
     */
    var page = 0
    var pageSize = 0
    var totalPage = 0
    var lotteryResList: List<LotteryHistory>? = null

}