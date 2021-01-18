package com.wuhai.navigation1;

import android.app.Application;
import android.content.Context;

/**
 * Created by flyeek on 5/30/15.
 */
public class DaodaoApplication extends Application {

    private static DaodaoApplication mInstance;

    public static DaodaoApplication getMyApplication() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
