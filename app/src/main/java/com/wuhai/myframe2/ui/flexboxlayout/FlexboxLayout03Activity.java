package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
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
 * flexWrap属性
 * nowrap （默认）：不换行。
 * wrap：按正常方向换行。
 * wrap_reverse：按反方向换行。
 */
public class FlexboxLayout03Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.nowrap)
    TextView nowrap;
    @BindView(R.id.wrap)
    TextView wrap;
    @BindView(R.id.wrap_reverse)
    TextView wrapReverse;
    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.textview3)
    TextView textview3;
    @BindView(R.id.textview4)
    TextView textview4;
    @BindView(R.id.textview5)
    TextView textview5;
    @BindView(R.id.flexbox_layout)
    FlexboxLayout flexboxLayout;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FlexboxLayout03Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout03);
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
        nowrap.setOnClickListener(this);
        wrap.setOnClickListener(this);
        wrapReverse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nowrap:
                flexboxLayout.setFlexWrap(FlexWrap.NOWRAP);
                break;
            case R.id.wrap:
                flexboxLayout.setFlexWrap(FlexWrap.WRAP);
                break;
            case R.id.wrap_reverse:
                flexboxLayout.setFlexWrap(FlexWrap.WRAP_REVERSE);
                break;
        }
    }
}
