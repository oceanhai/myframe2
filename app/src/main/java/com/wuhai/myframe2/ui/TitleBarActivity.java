package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.autowrap.widget.TitleBar;
import com.wuhai.myframe2.ui.base.BaseActivity2;
import com.wuhai.myframe2.utils.ToastUtils;
import com.wuhai.myframe2.widget.titlebar2.TitleViewListener;
import com.wuhai.myframe2.widget.titlebar2.TitleViewWithBack;
import com.wuhai.myframe2.widget.titlebar2.TitleViewWithCloseBtn;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TitleBarActivity extends BaseActivity2 {

    public static final String TAG = "TitleBarActivity";

    @BindView(R.id.titleview_with_back)
    TitleViewWithBack titleviewWithBack;
    @BindView(R.id.titleview_with_close)
    TitleViewWithCloseBtn titleviewWithClose;
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TitleBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_title_bar);
        ButterKnife.bind(this);

        /**
         * 标题栏颜色
         * TODO 这个应该写在基类里(前提标题栏颜色一致)
         * TODO 如果不一致，可以把这个变成可控的，根据每个页面自行设置
         * TODO common_titlebar_height 高度根据是否是KITKAT选择不同高度
         */
        Log.e(TAG, "Build.VERSION.SDK_INT="+Build.VERSION.SDK_INT+
                "Build.VERSION_CODES.KITKAT="+Build.VERSION_CODES.KITKAT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        /**
         * titlebar1
         */
        titlebar.setTitle("titlebar");
        titlebar.setBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * titlebar2
         */
        titleviewWithBack.setTitleText("titleviewWithBack");
        titleviewWithBack.setRightBtnText("right");
        titleviewWithBack.setTitleViewListener(new TitleViewListener() {
            @Override
            public boolean onLeftImgClick() {
                return false;
            }

            @Override
            public boolean onRightImgClick() {
                return false;
            }

            @Override
            public boolean onLeftBtnClick() {
                return false;
            }

            @Override
            public boolean onRightBtnClick() {
                ToastUtils.showShort(TitleBarActivity.this, "点击右侧文字");
                return true;
            }
        });

        /**
         * titlebar2 扩展 左侧图片变
         */
        titleviewWithClose.setTitleText("titleviewWithClose");
        titleviewWithClose.setRightBtnText("right");
        titleviewWithClose.setTitleViewListener(new TitleViewListener() {
            @Override
            public boolean onLeftImgClick() {
                ToastUtils.showShort(TitleBarActivity.this, "弹出退出窗口");
                return true;
            }

            @Override
            public boolean onRightImgClick() {
                return false;
            }

            @Override
            public boolean onLeftBtnClick() {
                return false;
            }

            @Override
            public boolean onRightBtnClick() {
                ToastUtils.showShort(TitleBarActivity.this, "点击右侧文字");
                return true;
            }
        });

    }
}
