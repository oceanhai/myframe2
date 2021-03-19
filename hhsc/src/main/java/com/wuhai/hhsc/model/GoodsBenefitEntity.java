package com.wuhai.hhsc.model;

import java.math.BigDecimal;

/**
 * description:惠筹商品
 * data: 2019-12-17
 * author: zhudi
 */
public class GoodsBenefitEntity {
    /**
     * 活动图片
     */
    private String activityImg;


    /**
     * 已筹金额
     */
    private BigDecimal amount;


    /**
     * 惠筹开始时间
     */
    private String beginTime;


    /**
     * 惠筹商品价格
     */
    private BigDecimal discountPrice;


    /**
     * 惠筹结束时间
     */
    private String endTime;


    /**
     * 赠送积分
     */
    private BigDecimal giveIntegral;


    /**
     * 商品货号编码等
     */
    private String goodsCode;


    /**
     * 惠筹商品名称
     */
    private String goodsName;


    /**
     * K-V值，多属性值逗号分隔，
     * 跟随分类属性组变动
     */
    private String goodsProperty;


    /**
     * 惠筹商品主键ID
     */
    private String id;


    /**
     * 单笔最大购买数量
     */
    private int limitMax;


    /**
     * 单笔最小购买数量
     */
    private int limitMin;


    /**
     * 商品主图
     */
    private String mainImgUrl;


    /**
     * 已筹数量（支持人数）
     */
    private int number;


    /**
     * 备注信息
     */
    private String remarks;


    /**
     * 商品spuId
     */
    private String spuId;


    /**
     * 惠筹活动状态 1=待发布；2=未开始；3=惠筹中；4=已关闭；5=已成功；6=已失败；
     */
    private int state;


    /**
     * 总库存
     */
    private String stock;


    /**
     * 目标金额
     */
    private BigDecimal targetAmount;


    /**
     * 惠筹目标数量
     */
    private int targetNumber;

    /**
     * vip赠送积分
     */
    private BigDecimal vipGiveIntegral;


    /**
     * 惠筹商品vip价格
     */
    private BigDecimal vipPrice;

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(BigDecimal giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsProperty() {
        return goodsProperty;
    }

    public void setGoodsProperty(String goodsProperty) {
        this.goodsProperty = goodsProperty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(int limitMax) {
        this.limitMax = limitMax;
    }

    public int getLimitMin() {
        return limitMin;
    }

    public void setLimitMin(int limitMin) {
        this.limitMin = limitMin;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public BigDecimal getVipGiveIntegral() {
        return vipGiveIntegral;
    }

    public void setVipGiveIntegral(BigDecimal vipGiveIntegral) {
        this.vipGiveIntegral = vipGiveIntegral;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }
}
