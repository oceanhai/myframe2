package com.wuhai.myframe2.ui.webview1.h5bean;

import java.io.Serializable;

/**
 * 给H5传递的实体类（技术一部实现方式）
 */
public class ToH5Bean implements Serializable {
    //通知H5 方法名
    public String name;
    //私钥
    public String privatekey;
    //des密钥
    public String deskey;
    //身份证号（解密后）
    public String idno;
    //APP 名称
    public String appname;
    //用户手机号
    public String phone;
    //用户id
    public String userId;
    //是否实名认证  0 未认证 1认证
    public String isAuth;
    //token
    public String token;
    //状态栏高度
    public String stateHeight;
    //标题栏高度
    public String titleNavHeight;
    //底部安全区域高度
    public String bottomHeight;
    //刷脸base64字符串
    public String faceImgData;
    //回传H5调用时的shootType  0：普通拍摄 1：拍摄身份证人脸面 2：拍摄身份证国徽面 3：拍证件照
    public String shootType;
    //二维码或条形码扫描结果
    public String scanCode;
    //扫描类型 qr 二维码  bar 条形码  空为都可以
    public String scanType;
    //设备唯一标识(目前是友盟提供的)
    public String deviceId;

    //1是授权使用身份证照片 0 其他方式
    public String isAuthorize;
}
