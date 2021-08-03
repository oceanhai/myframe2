package com.wuhai.rxhttpdemo;

import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.annotation.Domain;

public class Url {
    @DefaultDomain() //设置为默认域名
    public static String baseUrl = "https://www.wanandroid.com/";

    @Domain(name = "BaseUrlBaidu") //非默认域名，并取别名为BaseUrlBaidu
    public static String baidu = "https://www.baidu.com/";

    @Domain(name = "BaseUrlGoogle") //非默认域名，并取别名为BaseUrlGoogle
    public static String google = "https://www.google.com/";
}

