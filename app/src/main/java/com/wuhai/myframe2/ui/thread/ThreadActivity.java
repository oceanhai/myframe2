package com.wuhai.myframe2.ui.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcThreadBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：Thread
 */
public class ThreadActivity extends BaseActivity implements View.OnClickListener {

    private AcThreadBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThreadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();

//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        Log.e(TAG, "当前线程:"+Thread.currentThread().getName());
        new Handler().post(()->{
            Log.e(TAG, "Handler Runnable里线程:"+Thread.currentThread().getName());
        });
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_thread);
    }

    private void setListener() {
        binding.handlerThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.handler_thread:
                HandlerThreadActivity.startActivity(this);
                break;
        }
    }
}
