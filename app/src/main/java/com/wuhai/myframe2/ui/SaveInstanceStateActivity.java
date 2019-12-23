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
import com.wuhai.myframe2.databinding.AcSaveInstanceStateBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：现场保护
 * onCreate savedInstanceState = null->起一个线程 num++,竖屏改横屏，执行onPause->onSaveInstanceState Bundle记录num->onStop->onDestroy结束线程
 * ->onCreate savedInstanceState != null 这时，这里可以取到记录值并赋值给变量num,起线程在num++->onStart
 * ->onRestoreInstanceState 这里if(savedInstanceState != null && savedInstanceState.containsKey("num"))判断进行记录值操作
 * 我这里简单的把num值放到EditText展示->onResume
 */
public class SaveInstanceStateActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SaveInstanceState";//TODO 日志标记最多可以是23个字符，即25个字符，居然还长度要求 囧

    private AcSaveInstanceStateBinding binding;

    private int num = 0;
    private boolean isRunning = true;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SaveInstanceStateActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate savedInstanceState="+savedInstanceState);

//        setContentView(R.layouts.ac_save_instance_state);
        binding = DataBindingUtil.setContentView(this, R.layout.ac_save_instance_state);

        parseIntent();
        init(savedInstanceState);
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
