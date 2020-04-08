package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;
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
 * justifyContent属性 定义了项目在主轴上的对齐方式。
 * flex_start（默认值）：左对齐。
 * flex_end ：右对齐。
 * center ： 居中。
 * space_between ：两端对齐，项目之间的间隔都相等。
 * space_around ：每个项目两侧的间隔相等。项目之间的间隔比项目与边框的间隔大一倍。
 * <p>
 * TODO 有个疑问？我用LinearLayout，而不用ScrollView，里面的textview居然高度自适应了
 */
public class FlexboxLayout04Activity extends BaseActivity implements View.OnClickListener {

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
        intent.setClass(context, FlexboxLayout04Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout04);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flex_start:
                flexboxLayout.setJustifyContent(JustifyContent.FLEX_START);
                break;
            case R.id.flex_end:
                flexboxLayout.setJustifyContent(JustifyContent.FLEX_END);
                break;
            case R.id.center:
                flexboxLayout.setJustifyContent(JustifyContent.CENTER);
                break;
            case R.id.space_between:
                flexboxLayout.setJustifyContent(JustifyContent.SPACE_BETWEEN);
                break;
            case R.id.space_around:
                flexboxLayout.setJustifyContent(JustifyContent.SPACE_AROUND);
                break;
        }
    }
}
