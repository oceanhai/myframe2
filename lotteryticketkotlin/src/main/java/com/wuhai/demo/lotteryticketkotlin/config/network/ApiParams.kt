package com.wuhai.demo.lotteryticketkotlin.config.network

import android.util.Log
import com.wuhai.demo.lotteryticketkotlin.config.Constants
import java.util.*

class ApiParams(private val mMethod: Method) : HashMap<String?, String?>() {
    enum class Method(val method: String) {
        GET_PARMS("getParams"), POST_PARMS("postParams");

    }

    fun with(key: String?, value: String?): ApiParams {
        put(key, value)
        return this
    }

    fun juhe(version: String?): ApiParams {
        put(Constants.PARAMS_KEY, version)
        return this
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
    fun print(): ApiParams {
        val sbf = StringBuffer()
        if (this != null) {
            for ((key, value) in this) {
                sbf.append("$key=$value ")
            }
        }
        Log.e(ServiceProvider.TAG, mMethod.method + ":" + sbf.toString())
        return this
    }

}