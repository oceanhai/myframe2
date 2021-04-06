package com.wuhai.demo.lotteryticketkotlin.model.bean

/**
 * 作者 wh
 *
 * 创建日期 2020/1/10 18:08
 *
 * 描述：彩票历史数据
 */
class LotteryHistory {
    /**
     * "lottery_id":"ssq",
     * "lottery_res":"02,15,17,27,32,33,03",
     * "lottery_no":"20004",
     * "lottery_date":"2020-01-09",
     * "lottery_exdate":"2020-03-08",
     * "lottery_sale_amount":"370,826,158",
     * "lottery_pool_amount":"906,654,598"
     */
    var lottery_id //彩票ID
            : String? = null
    var lottery_res //开奖结果
            : String? = null
    var lottery_no //开奖期号
            : String? = null
    var lottery_date //开奖日期
            : String? = null
    var lottery_exdate //兑奖截止日期
            : String? = null
    var lottery_sale_amount //本期销售额，可能为空
            : String? = null
    var lottery_pool_amount //奖池滚存，可能为空
            : String? = null

}