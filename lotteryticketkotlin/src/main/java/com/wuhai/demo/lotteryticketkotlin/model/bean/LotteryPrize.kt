package com.wuhai.demo.lotteryticketkotlin.model.bean

class LotteryPrize:BaseBean() {

    var prize_name:String?=null//奖项名称
    var prize_num:String?=null//中奖数量，公布数据可能延时可能为空或--
    var prize_amount:String?=null//中奖数量，公布数据可能延时可能为空或--
    var prize_require:String?=null//中奖条件
}