package com.wuhai.myframe2.bean;


import com.wuhai.myframe2.bean.base.BaseBean;

import java.util.List;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/11 17:22
 *
 * 描述：感言
 */
public class Feelings extends BaseBean {

    /**
     * stage : 1
     * goodName : 小米8
     * nickName : 吴海1
     * bidedPrice : 399
     * bidedPirceThousand : 399
     * feelingsTime : 04-03 18:42:58
     * checked : 0
     * avatar : 123
     * feelings : 4444444444444
     * pics : ["http://m.qiandaodao.com/ecshop/webview/item/detail.do?itemId=2VM4MKV8ULS12_11_22_31","http://m.qiandaodao.com/ecshop/webview/item/detail.do?itemId=2VM4MKV8ULS12_11_22_31","http://m.qiandaodao.com/ecshop/webview/item/detail.do?itemId=2VM4MKV8ULS12_11_22_31"]
     */

    private int type = 1;//1:item  99:item no-data

    private String activityId;//活动id
    private int stage;//中标期数
    private String goodName;//活动商品名称
    private String nickName;//中标者昵称
    private double bidedPrice;//中标价格
    private String bidedPirceThousand;//千位符中标金额
    private String feelingsTime;//中标时间
    private String avatar;//中标者头像
    private String feelings;//中标感言内容
    private List<String> pics;//中标者感言中发表的图片

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public double getBidedPrice() {
        return bidedPrice;
    }

    public void setBidedPrice(double bidedPrice) {
        this.bidedPrice = bidedPrice;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setBidedPirceThousand(String bidedPirceThousand) {
        this.bidedPirceThousand = bidedPirceThousand;
    }

    public void setFeelingsTime(String feelingsTime) {
        this.feelingsTime = feelingsTime;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    public int getStage() {
        return stage;
    }

    public String getGoodName() {
        return goodName;
    }

    public String getNickName() {
        return nickName;
    }


    public String getBidedPirceThousand() {
        return bidedPirceThousand;
    }

    public String getFeelingsTime() {
        return feelingsTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFeelings() {
        return feelings;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
