package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：谷歌FlexboxLayout
 * 参考文章 https://www.jianshu.com/p/5224af72987e
 */
public class FlexboxLayoutActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.tv04)
    TextView tv04;
    @BindView(R.id.tv05)
    TextView tv05;
    @BindView(R.id.tv06)
    TextView tv06;
    @BindView(R.id.tv07)
    TextView tv07;
    @BindView(R.id.tv08)
    TextView tv08;
    @BindView(R.id.tv09)
    TextView tv09;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FlexboxLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout);
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
        tv01.setOnClickListener(this);
        tv02.setOnClickListener(this);
        tv03.setOnClickListener(this);
        tv04.setOnClickListener(this);
        tv05.setOnClickListener(this);
        tv06.setOnClickListener(this);
        tv07.setOnClickListener(this);
        tv08.setOnClickListener(this);
        tv09.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv01:
                FlexboxLayout01Activity.startActivity(this);
                break;
            case R.id.tv02://flexDirection属性
                FlexboxLayout02Activity.startActivity(this);
                break;
            case R.id.tv03://flexWrap属性
                FlexboxLayout03Activity.startActivity(this);
                break;
            case R.id.tv04://justifyContent属性
                FlexboxLayout04Activity.startActivity(this);
                break;
            case R.id.tv05://alignItems属性
                FlexboxLayout05Activity.startActivity(this);
                break;
            case R.id.tv06://alignContent属性
                FlexboxLayout06Activity.startActivity(this);
                break;
            case R.id.tv07://FlexboxLayout实现tag
                FlexboxLayout07Activity.startActivity(this);
                break;
            case R.id.tv08://layout_flexBasisPercent 属性(控件内view)
                FlexboxLayout08Activity.startActivity(this);
                break;
            case R.id.tv09://layout_flexBasisPercent 属性(控件内view)
                RecyclerViewActivity.startActivity(this);
                break;
        }
    }
}
