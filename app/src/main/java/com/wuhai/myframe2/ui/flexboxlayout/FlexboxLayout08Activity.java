package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
 * layout_flexBasisPercent 属性定义了在分配多余空间之前，子元素占据的主轴空间的百分比。它的默认值为auto，即子元素的本来大小。
 */
public class FlexboxLayout08Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.set_flex_basis_percent)
    TextView setFlexBasisPercent;
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
        intent.setClass(context, FlexboxLayout08Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout08);
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
    }

    @Override
    public void onClick(View v) {
    }
}
