package com.wuhai.myframe2.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 *  KeyguardManager
 *  对锁屏进行管理
 *  http://blog.sina.com.cn/s/blog_5da93c8f0101kom2.html
 *
 *
 */
public class AppStatusUtils {

    /**
     * 判断应用是否是在后台
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (TextUtils.equals(appProcess.processName, context.getPackageName())) {
                boolean isBackground = (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE);
                boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
                return isBackground || isLockedState;
            }
        }
        return false;
    }

}
