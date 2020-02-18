package com.wuhai.myframe2.ui.popwindow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
 * 描述：PopWindow
 * <p>
 * Android Popwindow使用总结  没细看
 * https://www.jianshu.com/p/3812ff5ef272
 */
public class PopWindowActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.tv02)
    TextView tv02;

    private MorePopWindow morePopWindow;
    private ListPopWindow mListPopWindow;

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

        tv01.setOnClickListener(this);
        tv02.setOnClickListener(this);
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
                if (mListPopWindow == null) {
                    mListPopWindow = new ListPopWindow(this);
                }
                mListPopWindow.showPopupWindow(v);
                break;
            case R.id.tv01:
                FloatingWindow floatingWindow = new FloatingWindow(this, v);
                floatingWindow.show(0, 0, false);
                floatingWindow.setFloatingOperation(new FloatingWindow.IFloatingOperation() {
                    @Override
                    public void onDelete() {

                    }
                });
                break;
            case R.id.tv02:
                PositionCanChangePopupWindow positionCanChangePopupWindow =
                        new PositionCanChangePopupWindow(this, v, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                Log.e(TAG, "v.getX()="+v.getX()+", v.getY()="+v.getY());
                Log.e(TAG, "v.getLeft()="+v.getLeft()+", v.getTop()="+v.getTop()+
                        ",v.getRight()="+v.getRight()+",v.getBottom()="+v.getBottom());
                positionCanChangePopupWindow.
                        showPopupWindow((v.getRight()+v.getLeft())/2,(v.getBottom()+v.getTop())/2);
                break;
        }
    }
}
