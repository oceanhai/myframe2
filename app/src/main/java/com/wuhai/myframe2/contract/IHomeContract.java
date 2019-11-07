package com.wuhai.myframe2.contract;


import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.contract.base.IBasePresenterInterFace;
import com.wuhai.myframe2.contract.base.INormalViewInterFace;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/7/12 9:41
 *
 * 描述：
 */
public interface IHomeContract {

//    interface View extends INewLoadingBaseViewInterFace {
    interface View extends INormalViewInterFace {

        /**
         * 设置首页信息
         * @param result
         */
        void setActivityhome(ActivityHomeEntity result);

    }

    interface Presenter extends IBasePresenterInterFace {

        /**
         * 首页活动展示信息
         * @param refresh   0：old loading  1:new loading
         */
        void activityhome(int refresh);

    }

}
