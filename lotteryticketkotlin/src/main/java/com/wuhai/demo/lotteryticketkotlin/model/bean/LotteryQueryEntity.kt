package com.wuhai.demo.lotteryticketkotlin.model.bean

class LotteryQueryEntity : BaseBean(){

    var lottery_id //彩票ID
            : String? = null
    var lottery_name //彩票名称
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
    var lottery_prize //开奖详情，可能为null
            : List<LotteryPrize>? = null
}