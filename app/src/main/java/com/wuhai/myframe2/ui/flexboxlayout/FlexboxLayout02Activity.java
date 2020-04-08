package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
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
 * flexDirection属性
 * row（默认值）：主轴为水平方向，起点在左端。
 * row_reverse：主轴为水平方向，起点在右端。
 * column：主轴为垂直方向，起点在上沿。
 * column_reverse：主轴为垂直方向，起点在下沿。
 */
public class FlexboxLayout02Activity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.row)
    TextView row;
    @BindView(R.id.row_reverse)
    TextView rowReverse;
    @BindView(R.id.column)
    TextView column;
    @BindView(R.id.column_reverse)
    TextView columnReverse;
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
    @BindView(R.id.textview6)
    TextView textview6;
    @BindView(R.id.textview7)
    TextView textview7;
    @BindView(R.id.flexbox_layout)
    FlexboxLayout flexboxLayout;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FlexboxLayout02Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout02);
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
        row.setOnClickListener(this);
        rowReverse.setOnClickListener(this);
        column.setOnClickListener(this);
        columnReverse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.row:
                flexboxLayout.setFlexDirection(FlexDirection.ROW);
                break;
            case R.id.row_reverse:
                flexboxLayout.setFlexDirection(FlexDirection.ROW_REVERSE);
                break;
            case R.id.column:
                flexboxLayout.setFlexDirection(FlexDirection.COLUMN);
                break;
            case R.id.column_reverse:
                flexboxLayout.setFlexDirection(FlexDirection.COLUMN_REVERSE);
                break;
        }
    }
}
