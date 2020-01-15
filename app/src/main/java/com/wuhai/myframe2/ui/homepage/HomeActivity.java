package com.wuhai.myframe2.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.ui.homepage.qddgouwu.ShoppingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：首页复杂布局实现方案
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.btn03)
    Button btn03;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        ButterKnife.bind(this);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {

    }

    private void setListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01://使用特殊的(自定义)布局管理器
                HomePageActivity1.startActivity(this);
                break;
            case R.id.btn02://使用特殊的(自定义)布局管理器 实战
                ShoppingActivity.startActivity(this);
                break;
            case R.id.btn03://第三方twowayview
                break;
        }
    }
}
