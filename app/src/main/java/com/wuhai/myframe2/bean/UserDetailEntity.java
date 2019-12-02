package com.wuhai.myframe2.bean;


import com.wuhai.myframe2.bean.base.BaseBean;

import java.util.List;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/9 11:52
 *
 * 描述：我的信息 Entity
 */
public class UserDetailEntity extends BaseBean {

    private String id;//邀请码id   invite_code
    private String nickName;//昵称
    private int jifen;//用户积分
    private String jifenDesc;//格式化用户积分
    private String inviteCode;//邀请码
    private List<Messages> messages;//消息列表
    private String avatar;//头像
    private String wxHeadImgUrl;//微信头像
    private String wxNickName;//微信昵称

    private String name;//姓名
    private String idcard;//身份证

    private String jifenMarketUrl;//积分集市
    private String vipLevel;//当前vip等级

    private String fund;//带人民币符号的我的钱包金额

    private int orderCount;//购物订单 进行的个数

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getJifenMarketUrl() {
        return jifenMarketUrl;
    }

    public void setJifenMarketUrl(String jifenMarketUrl) {
        this.jifenMarketUrl = jifenMarketUrl;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public void setJifenDesc(String jifenDesc) {
        this.jifenDesc = jifenDesc;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setJifen(int jifen) {
        this.jifen = jifen;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setWxHeadImgUrl(String wxHeadImgUrl) {
        this.wxHeadImgUrl = wxHeadImgUrl;
    }

    public String getJifenDesc() {
        return jifenDesc;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getJifen() {
        return jifen;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getWxHeadImgUrl() {
        return wxHeadImgUrl;
    }

    @Override
    public String toString() {
        return "UserDetailEntity{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", jifen=" + jifen +
                ", jifenDesc='" + jifenDesc + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", messages=" + messages +
                ", avatar='" + avatar + '\'' +
                ", wxHeadImgUrl='" + wxHeadImgUrl + '\'' +
                ", wxNickName='" + wxNickName + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", jifenMarketUrl='" + jifenMarketUrl + '\'' +
                ", vipLevel='" + vipLevel + '\'' +
                ", fund='" + fund + '\'' +
                ", orderCount=" + orderCount +
                '}';
    }
}
