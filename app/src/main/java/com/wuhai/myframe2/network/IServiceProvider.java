package com.wuhai.myframe2.network;


import com.wuhai.myframe2.bean.ActivityHomeResult;
import com.wuhai.myframe2.ui.rxjava.network.RequestNetCallBack;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface IServiceProvider {


    /**
     * 首页活动展示信息
     */
    void activityhome(APICallBack<ActivityHomeResult> callback);

    /**
     * 首页活动展示信息 retrofit + rxJava  捎货帮
     */
    void activityhomeRx(RequestNetCallBack callBack);

    /**
     * 首页活动展示信息 retrofit + rxJava  普通情况
     */
    void activityhomeRx2();

}







