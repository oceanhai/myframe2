package com.wuhai.scan;

import android.app.Application;
import android.content.Context;

/**
 * Application类，负责保存一些全局常量以及做一些初始化
 * ※分包之后要继承MultiDexApplication,不然友盟sdk总是报错
 */
public class BaseApplication extends Application {
    /**
     * 全局上下文
     */
    private static Context context;

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Context getAppContext() {
        return BaseApplication.context;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        BaseApplication.context = this.getApplicationContext();

    }


}
