package com.wuhai.retrofit.application;

import android.content.Context;

import com.wuhai.retrofit.utils.LogUtil;

/**
 * Created by wuhai on 2017/01/10 10:12.
 * 描述：common共用内容
 */

public class LctApplication {

    /**
     * application上下文
     */
    public static Context mAppContext = null;

    /**
     * ※要在住项目application里初始化
     * @param context
     */
    public static void init(Context context, Boolean log_is_show, Boolean toast_debug_is_show, String sp_file_name){
        mAppContext = context;
        LogUtil.init(log_is_show);
//        ToastUtil.initToast(toast_debug_is_show);
//        SPUtils.init(sp_file_name);
    }

    /**
     * 获得application上下文
     * @return
     */
    public static Context getAppContext(){
        if (mAppContext == null){
            throw new NullPointerException("LctCommon 需要在application里初始化 ");
        }
        return  mAppContext;
    }
}
