package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
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
 * alignItems属性
 * flex_start：交叉轴的起点对齐。
 * flex_end：交叉轴的终点对齐。
 * center：交叉轴的中点对齐。
 * baseline: 项目的第一行文字的基线对齐。
 * stretch（默认值）：如果项目未设置高度或设为auto，将占满整个容器的高度
 * <p>
 * TODO baseline 效果好像跟文章说的不太一样呢
 */
public class FlexboxLayout05Activity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.flex_start)
    TextView flexStart;
    @BindView(R.id.flex_end)
    TextView flexEnd;
    @BindView(R.id.center)
    TextView center;
    @BindView(R.id.baseline)
    TextView baseline;
    @BindView(R.id.stretch)
    TextView stretch;
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
        intent.setClass(context, FlexboxLayout05Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout05);
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
        flexStart.setOnClickListener(this);
        flexEnd.setOnClickListener(this);
        center.setOnClickListener(this);
        baseline.setOnClickListener(this);
        stretch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flex_start:
                flexboxLayout.setAlignItems(AlignItems.FLEX_START);
                break;
            case R.id.flex_end:
                flexboxLayout.setAlignItems(AlignItems.FLEX_END);
                break;
            case R.id.center:
                flexboxLayout.setAlignItems(AlignItems.CENTER);
                break;
            case R.id.baseline:
                flexboxLayout.setAlignItems(AlignItems.BASELINE);
                break;
            case R.id.stretch:
                flexboxLayout.setAlignItems(AlignItems.STRETCH);
                break;
        }
    }
}
