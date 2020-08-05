package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcChronometerBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：Chronometer
 */
public class ChronometerActivity extends BaseActivity {

    private AcChronometerBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ChronometerActivity.class);
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

        }
    }

    @Override
    public void setStatistical() {

    }

    int current = 0;

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_chronometer);

        //设置初始值 一般为当前时间
        binding.chronometer.setBase(SystemClock.elapsedRealtime());
        //事件监听器，时间发生变化时可进行操作
//        binding.chronometer.setOnChronometerTickListener();
        //设置格式(默认"MM:SS"格式)
        binding.chronometer.setFormat("%s");//TODO ?不起作用？

        binding.chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //每10s重置下
                //SystemClock.elapsedRealtime()系统当前时间
                //chronometer.getBase()记录计时器开始时的时间
//                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) > 10*1000 ){
//                    binding.chronometer.setBase(SystemClock.elapsedRealtime());
//                }

                //通过自己设置展示样式为"HH:MM:SS"
                if (current > 4){
                    current = 0;
                }
                current ++;
                chronometer.setText(formatMiss(current));
            }
        });

        //开始
        binding.chronometer.start();
        //停止
//        binding.chronometer.stop();
    }

    public static String formatMiss(int time){
        String hh=time/3600>9?time/3600+"":"0"+time/3600;
        String mm=(time% 3600)/60>9?(time% 3600)/60+"":"0"+(time% 3600)/60;
        String ss=(time% 3600) % 60>9?(time% 3600) % 60+"":"0"+(time% 3600) % 60;
        return hh+":"+mm+":"+ss;
    }

    private void setListener() {
    }

}
