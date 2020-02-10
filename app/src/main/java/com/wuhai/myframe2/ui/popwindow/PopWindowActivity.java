package com.wuhai.myframe2.ui.popwindow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：PopWindow
 */
public class PopWindowActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;

    private MorePopWindow morePopWindow;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PopWindowActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_popwindow);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                if (morePopWindow == null) {
                    morePopWindow = new MorePopWindow(this, this);
                }
                morePopWindow.showPopupWindow(v);
                break;
            case R.id.tv_from_gallery:// 从相册选择
                morePopWindow.dismiss();
                break;
            case R.id.tv_from_camera:// 从拍照选择
                morePopWindow.dismiss();
                break;
            case R.id.btn02:
                if (morePopWindow == null) {
                    morePopWindow = new MorePopWindow(this, this);
                }
                morePopWindow.showPopupWindow(v);
                break;
        }
    }
}
