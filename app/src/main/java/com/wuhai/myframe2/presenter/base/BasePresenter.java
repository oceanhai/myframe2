package com.wuhai.myframe2.presenter.base;


import android.content.Context;


/**
 * 作者 wuhai
 * 
 * 创建日期 2018/5/14 17:58
 * 
 * 描述：
 */
public class BasePresenter {
    
    //类名
    public String TAG = getClass().getSimpleName();

    /**
     * 判断 code
     * @param context
     * @param code          接口返回的code
     * @param condition     条件0：不处理 1:4030下，登录后回上一页
     */
    public static boolean judegeCode(Context context, int code, int condition){
        boolean result = false;
        switch (code){
            case 4030://未登录
                result = true;
//                UserInfoCenter.getInstance().Logout();
                break;
        }
        return result;
    }
}
