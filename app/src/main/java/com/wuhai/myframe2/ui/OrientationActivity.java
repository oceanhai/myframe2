package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.business.AppFrontBackHelper;
import com.wuhai.myframe2.databinding.AcOrientationBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：可切换横竖屏
 */
public class OrientationActivity extends BaseActivity {

    private AcOrientationBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OrientationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();

        Log.e(AppFrontBackHelper.TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(AppFrontBackHelper.TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(AppFrontBackHelper.TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(AppFrontBackHelper.TAG, "onDestroy");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.e(AppFrontBackHelper.TAG, "横屏");
        }else{
            Log.e(AppFrontBackHelper.TAG, "竖屏");
        }
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_orientation);
    }

    private void setListener() {
    }


}
