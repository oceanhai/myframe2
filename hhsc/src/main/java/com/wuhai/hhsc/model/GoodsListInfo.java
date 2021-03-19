package com.wuhai.hhsc.model;

import java.math.BigDecimal;

/**
 * 商品列表对象
 *
 * @author qi
 */
public class GoodsListInfo {
    /**
     * spuId
     */
    private String spuId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品一级分类
     */
    private String categoryOne;

    /**
     * 商品二级分类
     */
    private String categoryTwo;

    /**
     * 商品三级分类
     */
    private String categoryThree;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 折扣价
     */
    private BigDecimal discountPrice;

    /**
     * 赠送积分
     */
    private BigDecimal giveIntegral;

    /**
     * 会员价
     */
    private BigDecimal vipPrice;

    /**
     * 会员价赠送积分
     */
    private BigDecimal vipGiveIntegral;
    /**
     * 会员名品券
     */
    private BigDecimal vipCoupon;

    /**
     * 微代价
     */
    private BigDecimal agentPrice;

    /**
     * 微代积分
     */
    private BigDecimal agentIntegral;

    /**
     * 微代名品券
     */
    private BigDecimal agentCoupon;

    /**
     * 首图链接
     */
    private String mainImgUrl;

    /**
     * 商品专区id，逗号隔开，例如：1,2
     */
    private String areaIds;

    /**
     * 商品类型：1：套餐 2：惠筹
     */
    private int goodsType;

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(String categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    public String getCategoryThree() {
        return categoryThree;
    }

    public void setCategoryThree(String categoryThree) {
        this.categoryThree = categoryThree;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(BigDecimal giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getVipGiveIntegral() {
        return vipGiveIntegral;
    }

    public void setVipGiveIntegral(BigDecimal vipGiveIntegral) {
        this.vipGiveIntegral = vipGiveIntegral;
    }

    public BigDecimal getVipCoupon() {
        return vipCoupon;
    }

    public void setVipCoupon(BigDecimal vipCoupon) {
        this.vipCoupon = vipCoupon;
    }

    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    public BigDecimal getAgentIntegral() {
        return agentIntegral;
    }

    public void setAgentIntegral(BigDecimal agentIntegral) {
        this.agentIntegral = agentIntegral;
    }

    public BigDecimal getAgentCoupon() {
        return agentCoupon;
    }

    public void setAgentCoupon(BigDecimal agentCoupon) {
        this.agentCoupon = agentCoupon;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }
}
