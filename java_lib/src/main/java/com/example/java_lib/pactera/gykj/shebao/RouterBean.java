package com.example.java_lib.pactera.gykj.shebao;

import java.io.Serializable;

public class RouterBean implements Serializable {
    //跳转配置参数
    public ParamsBean params;
    //路由跳转方式,wap：跳转到url；vc：跳转到原生
    public String pattern;
}
