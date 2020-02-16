package com.wuhai.lotteryticket.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者 wh
 *
 * 创建日期 2020/1/10 18:08
 *
 * 描述：彩票
 */
@Entity(nameInDb = "Lottery")
public class Lottery {

    @Id
    @Property(nameInDb = "lottery_id")
    private String lottery_id;//主键
    @Property(nameInDb = "lottery_type")
    private String lottery_type;//彩票ID ssq：双色球；dlt：大乐透
    @Property(nameInDb = "lottery_name")
    private String lottery_name;//彩票名称
    @Property(nameInDb = "lottery_red_ball")
    private String lottery_red_ball;//红球,eg:03,04,05…  逗号隔开
    @Property(nameInDb = "lottery_blue_ball")
    private String lottery_blue_ball;//蓝球,eg:03,04,05…  逗号隔开
    @Property(nameInDb = "lottery_red_ball_count")
    private int lottery_red_ball_count;//红球个数
    @Property(nameInDb = "lottery_blue_ball_count")
    private int lottery_blue_ball_count;//蓝球个数
    @Property(nameInDb = "lottery_bet_num")
    private int lottery_bet_num;//方案注数
    @Property(nameInDb = "lottery_bet_money")
    private int lottery_bet_money;//投注金额
    @Property(nameInDb = "lottery_produce_method")
    private int lottery_produce_method;//0:随机；1：排除
    @Property(nameInDb = "lottery_no")
    private String lottery_no;//开奖期号
    @Property(nameInDb = "create_time")
    @NotNull
    private String create_time;//添加时间
    @Property(nameInDb = "last_modified")
    @NotNull
    private String last_modified;//最后修改时间
    @Generated(hash = 836306236)
    public Lottery(String lottery_id, String lottery_type, String lottery_name,
            String lottery_red_ball, String lottery_blue_ball,
            int lottery_red_ball_count, int lottery_blue_ball_count,
            int lottery_bet_num, int lottery_bet_money, int lottery_produce_method,
            String lottery_no, @NotNull String create_time,
            @NotNull String last_modified) {
        this.lottery_id = lottery_id;
        this.lottery_type = lottery_type;
        this.lottery_name = lottery_name;
        this.lottery_red_ball = lottery_red_ball;
        this.lottery_blue_ball = lottery_blue_ball;
        this.lottery_red_ball_count = lottery_red_ball_count;
        this.lottery_blue_ball_count = lottery_blue_ball_count;
        this.lottery_bet_num = lottery_bet_num;
        this.lottery_bet_money = lottery_bet_money;
        this.lottery_produce_method = lottery_produce_method;
        this.lottery_no = lottery_no;
        this.create_time = create_time;
        this.last_modified = last_modified;
    }
    @Generated(hash = 63235763)
    public Lottery() {
    }
    public String getLottery_id() {
        return this.lottery_id;
    }
    public void setLottery_id(String lottery_id) {
        this.lottery_id = lottery_id;
    }
    public String getLottery_type() {
        return this.lottery_type;
    }
    public void setLottery_type(String lottery_type) {
        this.lottery_type = lottery_type;
    }
    public String getLottery_name() {
        return this.lottery_name;
    }
    public void setLottery_name(String lottery_name) {
        this.lottery_name = lottery_name;
    }
    public String getLottery_red_ball() {
        return this.lottery_red_ball;
    }
    public void setLottery_red_ball(String lottery_red_ball) {
        this.lottery_red_ball = lottery_red_ball;
    }
    public String getLottery_blue_ball() {
        return this.lottery_blue_ball;
    }
    public void setLottery_blue_ball(String lottery_blue_ball) {
        this.lottery_blue_ball = lottery_blue_ball;
    }
    public int getLottery_red_ball_count() {
        return this.lottery_red_ball_count;
    }
    public void setLottery_red_ball_count(int lottery_red_ball_count) {
        this.lottery_red_ball_count = lottery_red_ball_count;
    }
    public int getLottery_blue_ball_count() {
        return this.lottery_blue_ball_count;
    }
    public void setLottery_blue_ball_count(int lottery_blue_ball_count) {
        this.lottery_blue_ball_count = lottery_blue_ball_count;
    }
    public int getLottery_bet_num() {
        return this.lottery_bet_num;
    }
    public void setLottery_bet_num(int lottery_bet_num) {
        this.lottery_bet_num = lottery_bet_num;
    }
    public int getLottery_bet_money() {
        return this.lottery_bet_money;
    }
    public void setLottery_bet_money(int lottery_bet_money) {
        this.lottery_bet_money = lottery_bet_money;
    }
    public int getLottery_produce_method() {
        return this.lottery_produce_method;
    }
    public void setLottery_produce_method(int lottery_produce_method) {
        this.lottery_produce_method = lottery_produce_method;
    }
    public String getLottery_no() {
        return this.lottery_no;
    }
    public void setLottery_no(String lottery_no) {
        this.lottery_no = lottery_no;
    }
    public String getCreate_time() {
        return this.create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    public String getLast_modified() {
        return this.last_modified;
    }
    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }
    
}
