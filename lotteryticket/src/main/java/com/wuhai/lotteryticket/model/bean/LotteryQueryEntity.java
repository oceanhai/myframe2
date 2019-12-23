package com.wuhai.lotteryticket.model.bean;

import java.util.List;

public class LotteryQueryEntity {

    /**
     * lottery_id : ssq
     * lottery_name : 双色球
     * lottery_res : 03,06,08,20,24,32,07
     * lottery_no : 19080
     * lottery_date : 2019-07-11
     * lottery_exdate : 2019-09-08
     * lottery_sale_amount : 333,601,616
     * lottery_pool_amount : 878,626,031
     */

    private String lottery_id;//彩票ID
    private String lottery_name;//彩票名称
    private String lottery_res;//开奖结果
    private String lottery_no;//开奖期号
    private String lottery_date;//开奖日期
    private String lottery_exdate;//兑奖截止日期
    private String lottery_sale_amount;//本期销售额，可能为空
    private String lottery_pool_amount;//奖池滚存，可能为空
    private List<LotteryPrize> lottery_prize;//开奖详情，可能为null

    public List<LotteryPrize> getLottery_prize() {
        return lottery_prize;
    }

    public void setLottery_prize(List<LotteryPrize> lottery_prize) {
        this.lottery_prize = lottery_prize;
    }

    public String getLottery_id() {
        return lottery_id;
    }

    public void setLottery_id(String lottery_id) {
        this.lottery_id = lottery_id;
    }

    public String getLottery_name() {
        return lottery_name;
    }

    public void setLottery_name(String lottery_name) {
        this.lottery_name = lottery_name;
    }

    public String getLottery_res() {
        return lottery_res;
    }

    public void setLottery_res(String lottery_res) {
        this.lottery_res = lottery_res;
    }

    public String getLottery_no() {
        return lottery_no;
    }

    public void setLottery_no(String lottery_no) {
        this.lottery_no = lottery_no;
    }

    public String getLottery_date() {
        return lottery_date;
    }

    public void setLottery_date(String lottery_date) {
        this.lottery_date = lottery_date;
    }

    public String getLottery_exdate() {
        return lottery_exdate;
    }

    public void setLottery_exdate(String lottery_exdate) {
        this.lottery_exdate = lottery_exdate;
    }

    public String getLottery_sale_amount() {
        return lottery_sale_amount;
    }

    public void setLottery_sale_amount(String lottery_sale_amount) {
        this.lottery_sale_amount = lottery_sale_amount;
    }

    public String getLottery_pool_amount() {
        return lottery_pool_amount;
    }

    public void setLottery_pool_amount(String lottery_pool_amount) {
        this.lottery_pool_amount = lottery_pool_amount;
    }
}
