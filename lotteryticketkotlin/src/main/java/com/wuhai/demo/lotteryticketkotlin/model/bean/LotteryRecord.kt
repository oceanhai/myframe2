package com.wuhai.demo.lotteryticketkotlin.model.bean

import org.greenrobot.greendao.annotation.*

/**
 * 作者 wh
 *
 * 创建日期 2020/1/10 18:08
 *
 * 描述：彩票
 */
@Entity(nameInDb = "lottery_record")
class LotteryRecord {
    @Id
    @Property(nameInDb = "lottery_id")
    var lottery_id //主键
            : String? = null

    @Property(nameInDb = "lottery_type")
    var lottery_type //彩票ID ssq：双色球；dlt：大乐透
            : String? = null

    @Property(nameInDb = "lottery_name")
    var lottery_name //彩票名称
            : String? = null

    @Property(nameInDb = "lottery_red_ball")
    var lottery_red_ball //红球,eg:03,04,05…  逗号隔开
            : String? = null

    @Property(nameInDb = "lottery_blue_ball")
    var lottery_blue_ball //蓝球,eg:03,04,05…  逗号隔开
            : String? = null

    @Property(nameInDb = "lottery_red_ball_count")
    var lottery_red_ball_count //红球个数
            = 0

    @Property(nameInDb = "lottery_blue_ball_count")
    var lottery_blue_ball_count //蓝球个数
            = 0

    @Property(nameInDb = "lottery_bet_num")
    var lottery_bet_num //方案注数
            = 0

    @Property(nameInDb = "lottery_bet_money")
    var lottery_bet_money //投注金额
            = 0

    @Property(nameInDb = "lottery_produce_method")
    var lottery_produce_method //0:随机；1：排除
            = 0

    @Property(nameInDb = "lottery_no")
    var lottery_no //开奖期号
            : String? = null

    @Property(nameInDb = "create_time")
    @NotNull
    var create_time //添加时间
            : String? = null

    @Property(nameInDb = "last_modified")
    @NotNull
    var last_modified //最后修改时间
            : String? = null

    /**
     * 转换
     * @param lottery
     */
    constructor(lottery: Lottery) {
        lottery_id = lottery.lottery_id
        lottery_type = lottery.lottery_type
        lottery_name = lottery.lottery_name
        lottery_red_ball = lottery.lottery_red_ball
        lottery_blue_ball = lottery.lottery_blue_ball
        lottery_red_ball_count = lottery.lottery_red_ball_count
        lottery_blue_ball_count = lottery.lottery_blue_ball_count
        lottery_bet_num = lottery.lottery_bet_num
        lottery_bet_money = lottery.lottery_bet_money
        lottery_produce_method = lottery.lottery_produce_method
        lottery_no = lottery.lottery_no
        create_time = lottery.create_time
        last_modified = lottery.last_modified
    }

    @Generated(hash = 1085174894)
    constructor(lottery_id: String?, lottery_type: String?,
                lottery_name: String?, lottery_red_ball: String?, lottery_blue_ball: String?,
                lottery_red_ball_count: Int, lottery_blue_ball_count: Int,
                lottery_bet_num: Int, lottery_bet_money: Int, lottery_produce_method: Int,
                lottery_no: String?, @NotNull create_time: String?,
                @NotNull last_modified: String?) {
        this.lottery_id = lottery_id
        this.lottery_type = lottery_type
        this.lottery_name = lottery_name
        this.lottery_red_ball = lottery_red_ball
        this.lottery_blue_ball = lottery_blue_ball
        this.lottery_red_ball_count = lottery_red_ball_count
        this.lottery_blue_ball_count = lottery_blue_ball_count
        this.lottery_bet_num = lottery_bet_num
        this.lottery_bet_money = lottery_bet_money
        this.lottery_produce_method = lottery_produce_method
        this.lottery_no = lottery_no
        this.create_time = create_time
        this.last_modified = last_modified
    }

    @Generated(hash = 1839488281)
    constructor() {
    }

}