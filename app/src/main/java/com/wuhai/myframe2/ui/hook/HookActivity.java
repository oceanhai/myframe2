package com.wuhai.myframe2.ui.hook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcHookBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：空ac
 */
public class HookActivity extends BaseActivity implements View.OnClickListener {

    private AcHookBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HookActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();

        try {
            HookHelper.hookOnClickListener(binding.btn01);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        getWindow().getDecorView().post(new Runnable() {
//            @Override
//            public void run() {
//                HookViewClickUtil.hookView(binding.btn01);
//            }
//        });
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_hook);
    }

    private void setListener() {
        binding.btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                Log.e(TAG, "btn01 被点击了");
                break;
        }
    }
}
