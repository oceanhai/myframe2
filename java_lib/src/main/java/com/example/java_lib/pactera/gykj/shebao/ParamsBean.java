package com.example.java_lib.pactera.gykj.shebao;

import java.io.Serializable;

public class ParamsBean implements Serializable {
    //0：需要实名；1：不需实名
    public String isCertified;
    //0：需要登录；1：不需登录
    public String isLogin;
    //请求id
    public String parasId;
    //事项标题
    public String title;
    //跳转URL地址
    public String url;
}