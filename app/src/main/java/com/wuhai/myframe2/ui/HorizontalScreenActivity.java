package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcHorizontalScreenBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：横屏
 * 开始
 *      onCreate
 *      onStart
 *      onResume
 *      onPause
 *      onSaveInstanceState
 *      onStop
 *      onDestroy
 * 横屏
 *      onCreate
 *      onStart
 *      onRestoreInstanceState
 *      onResume
 *
 */
public class HorizontalScreenActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "HorizontalScreen";

    private AcHorizontalScreenBinding binding;

    private int num = 0;
    private boolean isRunning = true;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HorizontalScreenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate savedInstanceState="+savedInstanceState);

//        setContentView(R.layouts.ac_save_instance_state);
        binding = DataBindingUtil.setContentView(this, R.layout.ac_horizontal_screen);

        parseIntent();
//        init(savedInstanceState);
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init(Bundle savedInstanceState) {
        Log.e(TAG,"init isRunning="+isRunning);
        if(savedInstanceState != null){
            num = savedInstanceState.getInt("num");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num++;
                    Log.e(TAG, "num="+num);
                }
            }
        }).start();
    }

    private void setListener() {
        binding.btn01.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//        Log.e(TAG,"onCreateView");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        isRunning = false;

        Log.e(TAG,"onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.e(TAG,"onSaveInstanceState");

        outState.putInt("num", num);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG,"onRestoreInstanceState");
        if(savedInstanceState != null && savedInstanceState.containsKey("num")){
            binding.edt01.setText(""+savedInstanceState.getInt("num"));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                ConstraintLayoutActivity.startActivity(this);
                break;
        }
    }
}
