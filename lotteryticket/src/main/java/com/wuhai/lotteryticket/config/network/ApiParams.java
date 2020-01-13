package com.wuhai.lotteryticket.config.network;


import android.util.Log;

import com.wuhai.lotteryticket.config.Constants;

import java.util.HashMap;

public class ApiParams extends HashMap<String, String> {

    public enum Method{
        GET_PARMS("getParams"),POST_PARMS("postParams");

        private String method;

        Method(String method) {
            this.method = method;
        }
    }

    private Method mMethod;

    public ApiParams(Method mMethod) {
        this.mMethod = mMethod;
    }

    public ApiParams with(String key, String value) {
        put(key, value);
        return this;
    }


    public ApiParams juhe(String version) {
        put(Constants.PARAMS_KEY, version);
        return this;
    }
//
//
//    public ApiParams otherWith() {
//        put(Constants.version_key,"4.2.1");
//        put(Constants.source_key, Constants.source_other_value);
//        put(Constants.os_key, Constants.os_value);
//        put(Constants.pro_key, Constants.pro_value);
//        return this;
//    }
//
//
//    /**
//     * Version 手动传入
//     */
//    public ApiParams withNoVersion() {
//        put(Constants.source_key, Constants.source_value);
//        put(Constants.os_key, Constants.os_value);
//        put(Constants.pro_key, Constants.pro_value);
//        put(Constants.token_key, (String) SPUtils.get("token",""));
//        return this;
//    }
//
//    /**
//     * 注册，登录，第三方登录，修改密码  不传token
//     * @return
//     */
//    public ApiParams withNoToken(String version) {
//        put(Constants.version_key, version);
//        put(Constants.source_key, Constants.source_value);
//        put(Constants.os_key, Constants.os_value);
//        put(Constants.pro_key, Constants.pro_value);
//        return this;
//    }

    /**
     * 打印log
     */
    public ApiParams print(){
        StringBuffer sbf=new StringBuffer();
        if(this!=null) {
            for (Entry<String, String> entry : this.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sbf.append(key + "=" + value+" ");
            }
        }
        Log.e(ServiceProvider.TAG, mMethod.method+":"+sbf.toString());

        return this;
    }
}
