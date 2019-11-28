package com.wuhai.myframe2.ui.retrofit.base;


import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.bean.ActivityHomeResult;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RootResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface QjjAPI {


    //首页活动展示信息
    @GET("activity/activityhome")
    Call<ActivityHomeResult> activityhome(@QueryMap Map<String, String> map);

    //我们换成  retrofit+rxJava  捎货帮
    @GET("activity/activityhome")
    Observable<RootResponse<ActivityHomeEntity>> activityhomeRx(@QueryMap Map<String, String> map);

    //retrofit + rxJava  普通情况
    @GET("activity/activityhome")
    Observable<ActivityHomeResult> activityhomeRx2(@QueryMap Map<String, String> map);
}
