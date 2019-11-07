package com.wuhai.myframe2.bean;

/**
 * 作者 wuhai
 * 
 * 创建日期 2019/4/3 18:28
 * 
 * 描述：活动(今日、昨日、明日 里的数据体一致)
 */
public class ActivityBean {

    /**
     * bidStatus : 1
     * startTimeDetail :
     * bidNickName :
     * count : 0
     * initPrice :
     * activityDescp :
     * avatar :
     * userId :
     * startTimeShort :
     * goodsBigIcon : 小米8
     * pirceThousand : 6,999
     * activityId : 3
     * stage : 3
     * bidedPriceDesc : 流标
     * activityResult :
     * price : 6999
     * bidedPrice :
     * goodsSmallIcon :
     * endTime : 23092361
     * goodsName :
     */

    private int type = 1;//1:item  99:item no-data

    private int bidStatus;//竞价状态（0-未竞价，1-竞价中，2-竞价成功，3-流拍）
    private String bidNickName;//流标情况下中标人字段描述
    private int count;//参与人数
    private int bidCount;//总报价x次
    private String initPrice;//起跳价格
    private String activicount;//每期活动出价次数上限
    private String activityDescp;//下一期活动开始描述
    private String avatar;//头像
    private String userId;//用户Id
    private String startTimeShort;//下一期商品开始时间预告简单描述
    private String startTimeDetail;// 下一期商品开始时间预告详细描述 ,
    private String goodsBigIcon;//活动商品大图
    private String price;//市场价格
    private String pirceThousand;//千位符市场价格(带逗号的格式)
    private String activityId;//活动id
    private int stage;//活动期数
    private String bidedPriceDesc;//中标价描述
    private String activityResult;//活动结果
    private String bidedPrice;//中标价格
    private String goodsSmallIcon;//活动商品小图
    private int endTime;//截止时间(距离截止时间所剩毫秒值)
    private String goodsName;//活动商品名称
    private boolean selfBided;//是否本人中标
    private String bidedBillLadingTips;//用户中标后提醒提货提示

    private String duration;//活动时长（结束时间-起始时间） 格式：xx:xx:xx

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBidCount() {
        return bidCount;
    }

    public void setBidCount(int bidCount) {
        this.bidCount = bidCount;
    }

    public String getBidedBillLadingTips() {
        return bidedBillLadingTips;
    }

    public void setBidedBillLadingTips(String bidedBillLadingTips) {
        this.bidedBillLadingTips = bidedBillLadingTips;
    }

    public boolean isSelfBided() {
        return selfBided;
    }

    public void setSelfBided(boolean selfBided) {
        this.selfBided = selfBided;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBidStatus(int bidStatus) {
        this.bidStatus = bidStatus;
    }

    public void setStartTimeDetail(String startTimeDetail) {
        this.startTimeDetail = startTimeDetail;
    }

    public void setBidNickName(String bidNickName) {
        this.bidNickName = bidNickName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setInitPrice(String initPrice) {
        this.initPrice = initPrice;
    }

    public void setActivityDescp(String activityDescp) {
        this.activityDescp = activityDescp;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStartTimeShort(String startTimeShort) {
        this.startTimeShort = startTimeShort;
    }

    public void setGoodsBigIcon(String goodsBigIcon) {
        this.goodsBigIcon = goodsBigIcon;
    }

    public void setPirceThousand(String pirceThousand) {
        this.pirceThousand = pirceThousand;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setBidedPriceDesc(String bidedPriceDesc) {
        this.bidedPriceDesc = bidedPriceDesc;
    }

    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult;
    }

    public void setBidedPrice(String bidedPrice) {
        this.bidedPrice = bidedPrice;
    }

    public void setGoodsSmallIcon(String goodsSmallIcon) {
        this.goodsSmallIcon = goodsSmallIcon;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getBidStatus() {
        return bidStatus;
    }

    public String getStartTimeDetail() {
        return startTimeDetail;
    }

    public String getBidNickName() {
        return bidNickName;
    }

    public int getCount() {
        return count;
    }

    public String getInitPrice() {
        return initPrice;
    }

    public String getActivityDescp() {
        return activityDescp;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserId() {
        return userId;
    }

    public String getStartTimeShort() {
        return startTimeShort;
    }

    public String getGoodsBigIcon() {
        return goodsBigIcon;
    }

    public String getPirceThousand() {
        return pirceThousand;
    }

    public String getActivityId() {
        return activityId;
    }

    public int getStage() {
        return stage;
    }

    public String getBidedPriceDesc() {
        return bidedPriceDesc;
    }

    public String getActivityResult() {
        return activityResult;
    }

    public String getBidedPrice() {
        return bidedPrice;
    }

    public String getGoodsSmallIcon() {
        return goodsSmallIcon;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getActivicount() {
        return activicount;
    }

    public void setActivicount(String activicount) {
        this.activicount = activicount;
    }
}
