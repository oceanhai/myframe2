package com.wuhai.myframe2.bean;


import com.wuhai.myframe2.bean.base.BaseBean;

/**
 * Created by wuhai on 2019/4/18.
 * 消息
 */

public class Messages extends BaseBean {

    /**
     * count : 1
     * type : lading
     * descp : 中标提货单
     */
    private int count;//消息数量
    private String type;//消息类型 lading-提货单消息
    private String descp;//消息描述

    public void setCount(int count) {
        this.count = count;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public int getCount() {
        return count;
    }

    public String getType() {
        return type;
    }

    public String getDescp() {
        return descp;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "count=" + count +
                ", type='" + type + '\'' +
                ", descp='" + descp + '\'' +
                '}';
    }
}
