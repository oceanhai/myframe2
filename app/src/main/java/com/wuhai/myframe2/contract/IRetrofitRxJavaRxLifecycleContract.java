package com.wuhai.myframe2.contract;


import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.bean.UserDetailEntity;
import com.wuhai.myframe2.contract.base.IBasePresenterInterFace;
import com.wuhai.myframe2.contract.base.INormalRxLifecycleViewInterFace;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/7/12 9:41
 *
 * 描述：
 */
public interface IRetrofitRxJavaRxLifecycleContract {

//    interface View extends INewLoadingBaseViewInterFace {
    interface View extends INormalRxLifecycleViewInterFace {

        /**
         * 设置首页信息
         * @param result
         */
        void setActivityhome(ActivityHomeEntity result);

        /**
         * 用户详情
         * @param entity
         */
        void setUserDetail(UserDetailEntity entity);
    }

    interface Presenter extends IBasePresenterInterFace {

        /**
         * 首页活动展示信息
         * @param refresh   0：old loading  1:new loading
         */
        void activityhome(int refresh);

        /**
         * 获取用户详情
         * @param token
         */
        void detail(String token);
    }

}
