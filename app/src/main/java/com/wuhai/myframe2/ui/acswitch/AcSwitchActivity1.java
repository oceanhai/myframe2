package com.wuhai.myframe2.ui.acswitch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcSwitch1Binding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：overridePendingTransition
 */
public class AcSwitchActivity1 extends BaseActivity implements View.OnClickListener {

    private AcSwitch1Binding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AcSwitchActivity1.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_switch1);
    }

    private void setListener() {
        binding.tv01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv01://退出
                finish();
                break;
        }
    }

    /**
     * 进入动画  TODO 无效，应该是放再启动页
     * @param intent
     */
//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.swipeback_base_slide_up_in,
//                R.anim.swipeback_base_slide_remain);
//    }

    /**
     * 退出动画  TODO 有效
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.swipeback_base_slide_down_out);
    }


}
