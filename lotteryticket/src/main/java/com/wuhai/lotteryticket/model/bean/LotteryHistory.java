package com.wuhai.lotteryticket.model.bean;

/**
 * 作者 wh
 *
 * 创建日期 2020/1/10 18:08
 *
 * 描述：彩票历史数据
 */
public class LotteryHistory {

    /**
     "lottery_id":"ssq",
     "lottery_res":"02,15,17,27,32,33,03",
     "lottery_no":"20004",
     "lottery_date":"2020-01-09",
     "lottery_exdate":"2020-03-08",
     "lottery_sale_amount":"370,826,158",
     "lottery_pool_amount":"906,654,598"
     */

    private String lottery_id;//彩票ID
    private String lottery_res;//开奖结果
    private String lottery_no;//开奖期号
    private String lottery_date;//开奖日期
    private String lottery_exdate;//兑奖截止日期
    private String lottery_sale_amount;//本期销售额，可能为空
    private String lottery_pool_amount;//奖池滚存，可能为空

    public String getLottery_id() {
        return lottery_id;
    }

    public void setLottery_id(String lottery_id) {
        this.lottery_id = lottery_id;
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
