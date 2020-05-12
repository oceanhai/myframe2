package com.wuhai.myframe2.ui.acswitch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcSwitchMainBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：Activity切换动画
 */
public class AcSwitchMainActivity extends BaseActivity implements View.OnClickListener {

    private AcSwitchMainBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AcSwitchMainActivity.class);
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

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_switch_main);
    }

    private void setListener() {
        binding.tv01.setOnClickListener(this);
        binding.tv02.setOnClickListener(this);
        binding.tv03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv01://方式1  overridePendingTransition
                AcSwitchActivity1.startActivity(this);
                //进入动画
                overridePendingTransition(R.anim.swipeback_base_slide_up_in,
                        R.anim.swipeback_base_slide_remain);
                break;
            case R.id.tv02://方式2  使用style的方式定义Activity的切换动画  TODO 好像并不起作用
                AcSwitchActivity2.startActivity(this);
                break;
            case R.id.tv03://默认
                AcSwitchActivity3.startActivity(this);
                break;
        }
    }
}
