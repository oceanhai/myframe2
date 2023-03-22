package com.wuhai.myframe2.ui.routable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcRouterMainBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述： https://github.com/clayallsopp/routable-android
 * Router 使用
 */
public class RouterMainActivity extends BaseActivity implements View.OnClickListener {

    private AcRouterMainBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RouterMainActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_router_main);
    }

    private void setListener() {
        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                // starts a new UserActivity
                Router.sharedRouter().open("users/16");
                break;
            case R.id.btn02:
                // starts a new NewUserActivity
                Router.sharedRouter().open("users/new/Clay/94303");
                break;
        }
    }
}
