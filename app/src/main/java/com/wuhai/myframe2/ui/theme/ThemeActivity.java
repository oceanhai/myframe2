package com.wuhai.myframe2.ui.theme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcThemeBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：Theme
 */
public class ThemeActivity extends BaseActivity {

    private AcThemeBinding binding;

    private int mThemeType;
    private int mSystemUi;

    /**
     * @param context
     */
    public static void startActivity(Context context, int themeType) {
        Intent intent = new Intent();
        intent.setClass(context, ThemeActivity.class);
        intent.putExtra("themeType", themeType);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int themeType, int systemUi) {
        Intent intent = new Intent();
        intent.setClass(context, ThemeActivity.class);
        intent.putExtra("themeType", themeType);
        intent.putExtra("systemUi", systemUi);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){
            mThemeType = intent.getIntExtra("themeType", 1);
            mSystemUi = intent.getIntExtra("systemUi", 0);
        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        switch (mThemeType){
            case 1://背景为白色
                setTheme(android.R.style.Theme_Light);
                break;
            case 2://白色背景并无标题栏
                setTheme(android.R.style.Theme_Light_NoTitleBar);
                break;
            case 3://白色背景，无标题栏，全屏
                setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
                break;
            case 4://背景黑色
                setTheme(android.R.style.Theme_Black);
                break;
            case 5://黑色背景并无标题栏
                setTheme(android.R.style.Theme_Black_NoTitleBar);
                break;
            case 6://黑色背景，无标题栏，全屏
                setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                break;
            case 7://用系统桌面为应用程序背景
                setTheme(android.R.style.Theme_Wallpaper);
                break;
            case 8://用系统桌面为应用程序背景，且无标题栏
                setTheme(android.R.style.Theme_Wallpaper_NoTitleBar);
                break;
            case 9://用系统桌面为应用程序背景，无标题栏，全屏
                setTheme(android.R.style.Theme_Wallpaper_NoTitleBar_Fullscreen);
                break;
            case 10://半透明
                setTheme(android.R.style.Theme_Translucent);
                break;
            case 11://半透明、无标题栏
                setTheme(android.R.style.Theme_Translucent_NoTitleBar);
                break;
            case 12://半透明、无标题栏、全屏
                setTheme(android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                break;
            case 13://
                setTheme(android.R.style.Theme_Panel);
                break;
            case 14://
                setTheme(android.R.style.Theme_Light_Panel);
                break;
        }

        switch (mSystemUi){
            case 1:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                break;
            case 2:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                break;
            case 3:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    getWindow().setStatusBarColor(Color.TRANSPARENT);
//                }
//                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                        View.SYSTEM_UI_FLAG_FULLSCREEN);
                break;
            case 4:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                break;
            case 5:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                break;
            case 6:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                break;
            case 7:
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_IMMERSIVE);
                break;
            case 8:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                break;
        }

        binding = DataBindingUtil.setContentView(this, R.layout.ac_theme);
    }

    private void setListener() {

    }

    private boolean mVisible;
    public void toggle(View view) {
        if (mVisible) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }

        mVisible = !mVisible;
    }
}
