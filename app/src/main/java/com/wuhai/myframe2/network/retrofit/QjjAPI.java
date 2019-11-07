package com.wuhai.myframe2.network.retrofit;


import com.wuhai.myframe2.bean.ActivityHomeResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface QjjAPI {


    //首页活动展示信息
    @GET("activity/activityhome")
    Call<ActivityHomeResult> activityhome(@QueryMap Map<String, String> map);

}
