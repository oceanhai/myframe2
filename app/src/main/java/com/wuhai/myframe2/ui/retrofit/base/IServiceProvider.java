package com.wuhai.myframe2.ui.retrofit.base;


import com.trello.rxlifecycle.LifecycleTransformer;
import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.bean.ActivityHomeResult;
import com.wuhai.myframe2.bean.UserDetailEntity;
import com.wuhai.myframe2.ui.retrofit.networknormal.APICallBack;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RequestNetCallBack;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RootResponse;

import rx.Subscriber;

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

    /**
     * 首页活动展示信息 retrofit + rxJava + RxLifecycle 捎货帮
     */
    void activityhomeRxRl(RequestNetCallBack<RootResponse<ActivityHomeEntity>> callBack, LifecycleTransformer lifecycleTransformer);

    /**
     * 获取用户详情
     */
    void detail(RequestNetCallBack<RootResponse<UserDetailEntity>> callBack, LifecycleTransformer lifecycleTransformer);

    /**
     * 玩Android 轮波图
     * TODO 因为返回的数据格式不同，所以没法用RequestNetCallBack
     *  也是 retrofit + rxJava + RxLifecycle 形式，只不过没用mvp
     */
    void getBanners(Subscriber subscriber, LifecycleTransformer lifecycleTransformer);
}







