package com.wuhai.rxhttpdemo.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * User: ljx
 * Date: 2019-11-22
 * Time: 23:34
 */
@Root(name = "route", strict = false) //要解析的xml数据的头部
public class NewsXml {
    @Attribute
    public String tag;
    @Attribute
    public String title;
}
