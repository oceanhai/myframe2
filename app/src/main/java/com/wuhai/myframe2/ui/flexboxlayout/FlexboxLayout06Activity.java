package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.AlignContent;
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
 * alignContent属性   定义了多根轴线的对齐方式。如果项目只有一根轴线，该属性不起作用。
 * flex_start：与交叉轴的起点对齐。
 * flex_end：与交叉轴的终点对齐。
 * center：与交叉轴的中点对齐。
 * space_between：与交叉轴两端对齐，轴线之间的间隔平均分布。
 * space_around：每根轴线两侧的间隔都相等。所以，轴线之间的间隔比轴线与边框的间隔大一倍。
 * stretch（默认值）：轴线占满整个交叉轴。
 * <p>
 * TODO 这个好像要跟justifyContent属性结合着用
 */
public class FlexboxLayout06Activity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.flex_start)
    TextView flexStart;
    @BindView(R.id.flex_end)
    TextView flexEnd;
    @BindView(R.id.center)
    TextView center;
    @BindView(R.id.space_between)
    TextView spaceBetween;
    @BindView(R.id.space_around)
    TextView spaceAround;
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
        intent.setClass(context, FlexboxLayout06Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout06);
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
        spaceBetween.setOnClickListener(this);
        spaceAround.setOnClickListener(this);
        stretch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flex_start:
                flexboxLayout.setAlignContent(AlignContent.FLEX_START);
                break;
            case R.id.flex_end:
                flexboxLayout.setAlignContent(AlignContent.FLEX_END);
                break;
            case R.id.center:
                flexboxLayout.setAlignContent(AlignContent.CENTER);
                break;
            case R.id.space_between:
                flexboxLayout.setAlignContent(AlignContent.SPACE_BETWEEN);
                break;
            case R.id.space_around:
                flexboxLayout.setAlignContent(AlignContent.SPACE_AROUND);
                break;
            case R.id.stretch:
                flexboxLayout.setAlignContent(AlignContent.STRETCH);
                break;
        }
    }
}
