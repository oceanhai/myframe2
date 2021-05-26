package com.wuhai.myframe2.ui.webview1.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 获取app的VersionName 和 VersionCode
 */

public class VersionUtil {

    public static String getVersion(Context context){
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = packageInfo.versionName;

        return versionName;
    }

    public static int getVersionCode(Context context){
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packageInfo.versionCode;

        return versionCode;
    }

}
