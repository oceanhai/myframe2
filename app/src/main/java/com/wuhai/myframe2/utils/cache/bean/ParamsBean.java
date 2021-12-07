package com.wuhai.myframe2.utils.cache.bean;

import java.io.Serializable;

public class ParamsBean implements Serializable {
    //0：需要登录；1：不需登录
    public String isLogin;
    //预设字段，h5 url 需要动态拼接参数时使用
    public String parasId;
    //标题, 根据业务，h5页非自行实现topbar时使用
    public String title;
    //web加载的h5 url地址
    public String url;
}