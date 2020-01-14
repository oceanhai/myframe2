package com.wuhai.myframe2.ui.homepage.qddgouwu;

/**
 * createby yangzheng
 * date 2016/3/21
 * email yangzhenop@126.com
 */
public class SearchProInfo {

    public String id;//商品id，
    public String name;//商品名称
    public float price;//商品价格，
    public float minDownpay;//最低首付额
    public int maxSelectMonths;//最大分期数
    public String icon;//图片
    public String description;//商品详情描述
    public String webviewUrl;//详情页
    public float  cutPrice; //降价额度
    public int coupon; //是否存在可用红包 0没红包，1有红包
    public String activity;//活动标题
    public float monthlyPrincipal;//月供


    public SearchProInfo(String id, String name, float price, float minDownpay, int maxSelectMonths, String icon
            , String description, String webviewUrl, float cutPrice, int coupon, String activity, float monthlyPrincipal) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.minDownpay = minDownpay;
        this.maxSelectMonths = maxSelectMonths;
        this.icon = icon;
        this.description = description;
        this.webviewUrl = webviewUrl;
        this.cutPrice = cutPrice;
        this.coupon = coupon;
        this.activity = activity;
        this.monthlyPrincipal = monthlyPrincipal;
    }

    @Override
    public String toString() {
        return "";
    }
}