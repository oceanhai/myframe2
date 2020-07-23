package com.example.java_lib.pactera;

import org.junit.Test;

import java.util.Map;

public class Sign {

    @Test
    public void sign(){
        Map apiParams = new ApiTreeParams(ApiTreeParams.Method.POST_PARMS)
                .with("appId", "appa1972bccd0e946f988c1ca6d1f02f8da")//应用ID，appbd6bcbdc11384beea8e5e7a2fb1370c2
                .with("presentID", "client5")//展位ID
                .with("devType", "1")//设备类型，1-安卓，2-苹果
                .with("currVersion", "1.0.0.0")//当前应用版本号
                .with("currProvince", "北京")//当前所在省  TODO 待完善
                .with("currCity", "北京")//当前所在城市//TODO 待完善
                .with("deviceId", "123")//设备Id
                .with("userId", "wuhai")//用户Id
//                .with("extParm", "")//扩展参数对象列表  list TODO 待完善
                .print();

        //签名操作
        String json = GsonUtils.getInstance().toJson(apiParams);
        System.out.println("UpdateManager build json="+json);
        String pinStr = json+ "94c6852c0edb41f6881280af506af027";
        System.out.println("UpdateManager build pinStr="+pinStr);
        String signStr = Md5Utils.md5(pinStr);
        System.out.println("UpdateManager build signStr="+signStr);
    }
}
