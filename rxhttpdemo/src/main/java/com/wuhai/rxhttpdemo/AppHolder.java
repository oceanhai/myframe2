package com.wuhai.rxhttpdemo;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * 应用application
 * Created by fanchang on 2017/2/14.
 */
public class AppHolder extends MultiDexApplication {

    private static AppHolder instance;

    public static AppHolder getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        MultiDex.install(this);
    }



}
