package com.wuhai.myframe2.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者 wh
 *
 * 创建日期 2020/8/27 9:19
 *
 * 描述：
 */
public class SharePrefService {

    private SharedPreferences prefs;
    private static SharePrefService mInstant;
    private final String PREFS_FILE = "present.prefs";

    protected SharePrefService(Context cxt) {
        prefs = cxt.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
    }

    protected SharePrefService(Context cxt, String name) {
        prefs = cxt.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharePrefService getInstance(Context context) {
        if (mInstant == null) {
            mInstant = new SharePrefService(context);
        }
        return mInstant;
    }

    public static SharePrefService newInstance(Context context) {
        mInstant = new SharePrefService(context);
        return mInstant;
    }

    /**
     * Return new instance of SharePrefHelper, won't change it's static instance.
     * @param context
     * @param name
     * @return
     */
    public static SharePrefService newInstance(Context context, String name) {
        return new SharePrefService(context, name);
    }

    public void setPref(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setPref(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setPref(String key, float value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void setPref(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setPref(String key, long value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public boolean getPref(String key, Boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    public String getPref(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    public int getPref(String key, int defaultValue) {
        return prefs.getInt(key, defaultValue);
    }

    public long getPref(String key, long defaultValue) {
        return prefs.getLong(key, defaultValue);
    }

    public float getPref(String key, float defaultValue) {
        return prefs.getFloat(key, defaultValue);
    }

    public boolean hasPrefWithKey(String key) {
        return prefs.contains(key);
    }

    public boolean removePref(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        return editor.commit();
    }
}

