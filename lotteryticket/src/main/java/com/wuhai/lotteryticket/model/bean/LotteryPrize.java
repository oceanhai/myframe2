package com.wuhai.lotteryticket.model.bean;

import com.wuhai.lotteryticket.model.BaseBean;

/**
 * 彩票 奖品
 */
public class LotteryPrize extends BaseBean {

    private String prize_name;//奖项名称
    private String prize_num;//中奖数量，公布数据可能延时可能为空或--
    private String prize_amount;//中奖数量，公布数据可能延时可能为空或--
    private String prize_require;//中奖条件

    public String getPrize_name() {
        return prize_name;
    }

    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }

    public String getPrize_num() {
        return prize_num;
    }

    public void setPrize_num(String prize_num) {
        this.prize_num = prize_num;
    }

    public String getPrize_amount() {
        return prize_amount;
    }

    public void setPrize_amount(String prize_amount) {
        this.prize_amount = prize_amount;
    }

    public String getPrize_require() {
        return prize_require;
    }

    public void setPrize_require(String prize_require) {
        this.prize_require = prize_require;
    }
}
