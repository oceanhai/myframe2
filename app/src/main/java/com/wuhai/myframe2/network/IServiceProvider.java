package com.wuhai.myframe2.network;


import com.wuhai.myframe2.bean.ActivityHomeResult;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface IServiceProvider {


    /**
     * 首页活动展示信息
     */
    void activityhome(APICallBack<ActivityHomeResult> callback);

}







