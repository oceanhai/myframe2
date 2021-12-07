package com.wuhai.myframe2.utils.cache.bean;

import java.io.Serializable;

public class RouterBean implements Serializable {
    //跳转配置参数
    public ParamsBean params;
    //路由跳转方式,wap：跳转到url；vc：跳转到原生; home: 首页; 公共服务：service；我的：mine；生活缴费：ePay；
    public String pattern;
}
