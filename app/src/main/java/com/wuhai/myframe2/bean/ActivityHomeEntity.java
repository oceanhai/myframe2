package com.wuhai.myframe2.bean;

import java.util.List;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 19:38
 *
 * 描述：首页 Entity
 */
public class ActivityHomeEntity {

    private ActivityBean todayActivity;//当日活动
    private ActivityBean tomorrowActivity;//明日预告
    private ActivityBean yesterdayActivity;//以前的（流标、本人、非本人）

    private FeatureContent featureContent1;//推荐位1
    private FeatureContent featureContent2;//推荐位1
    private FeatureContent featureContent3;//推荐位1
    private FeatureContent featureContent4;//推荐位1

    private int feelingsRespCount;//页数
    private List<Feelings> feelingsRespList;//中标感言list

    private ShareContent shareContentResp;//分享好友实体  TODO 字段废弃
    private List<ShareContent> shareContentRespList;//分享好友实体 列表

    private List<ActivityBean> endActivityList;//往期活动列表

    private int vipLevel;//当前vip 等级

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public List<ShareContent> getShareContentRespList() {
        return shareContentRespList;
    }

    public void setShareContentRespList(List<ShareContent> shareContentRespList) {
        this.shareContentRespList = shareContentRespList;
    }

    public List<ActivityBean> getEndActivityList() {
        return endActivityList;
    }

    public void setEndActivityList(List<ActivityBean> endActivityList) {
        this.endActivityList = endActivityList;
    }

    public ShareContent getShareContentResp() {
        return shareContentResp;
    }

    public void setShareContentResp(ShareContent shareContentResp) {
        this.shareContentResp = shareContentResp;
    }

    public ActivityBean getTodayActivity() {
        return todayActivity;
    }

    public void setTodayActivity(ActivityBean todayActivity) {
        this.todayActivity = todayActivity;
    }

    public ActivityBean getTomorrowActivity() {
        return tomorrowActivity;
    }

    public void setTomorrowActivity(ActivityBean tomorrowActivity) {
        this.tomorrowActivity = tomorrowActivity;
    }

    public ActivityBean getYesterdayActivity() {
        return yesterdayActivity;
    }

    public void setYesterdayActivity(ActivityBean yesterdayActivity) {
        this.yesterdayActivity = yesterdayActivity;
    }

    public FeatureContent getFeatureContent1() {
        return featureContent1;
    }

    public void setFeatureContent1(FeatureContent featureContent1) {
        this.featureContent1 = featureContent1;
    }

    public FeatureContent getFeatureContent2() {
        return featureContent2;
    }

    public void setFeatureContent2(FeatureContent featureContent2) {
        this.featureContent2 = featureContent2;
    }

    public FeatureContent getFeatureContent3() {
        return featureContent3;
    }

    public void setFeatureContent3(FeatureContent featureContent3) {
        this.featureContent3 = featureContent3;
    }

    public FeatureContent getFeatureContent4() {
        return featureContent4;
    }

    public void setFeatureContent4(FeatureContent featureContent4) {
        this.featureContent4 = featureContent4;
    }

    public int getFeelingsRespCount() {
        return feelingsRespCount;
    }

    public void setFeelingsRespCount(int feelingsRespCount) {
        this.feelingsRespCount = feelingsRespCount;
    }

    public List<Feelings> getFeelingsRespList() {
        return feelingsRespList;
    }

    public void setFeelingsRespList(List<Feelings> feelingsRespList) {
        this.feelingsRespList = feelingsRespList;
    }

    @Override
    public String toString() {
        return "ActivityHomeEntity{" +
                "todayActivity=" + todayActivity +
                ", tomorrowActivity=" + tomorrowActivity +
                ", yesterdayActivity=" + yesterdayActivity +
                ", featureContent1=" + featureContent1 +
                ", featureContent2=" + featureContent2 +
                ", featureContent3=" + featureContent3 +
                ", featureContent4=" + featureContent4 +
                ", feelingsRespCount=" + feelingsRespCount +
                ", feelingsRespList=" + feelingsRespList +
                ", shareContentResp=" + shareContentResp +
                ", shareContentRespList=" + shareContentRespList +
                ", endActivityList=" + endActivityList +
                ", vipLevel=" + vipLevel +
                '}';
    }
}
