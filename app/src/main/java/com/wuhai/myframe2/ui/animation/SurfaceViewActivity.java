package com.wuhai.myframe2.ui.animation;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
 * SurfaceView 实现动画
 */
public class SurfaceViewActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.surface_view_show)
    Button surfaceViewShow;
    @BindView(R.id.surface_anim_view)
    SurfaceAnimView surfaceAnimView;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SurfaceViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_surface_view);
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
        surfaceViewShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.surface_view_show:
                SurfaceBean surfaceBean =
                        new SurfaceBean(BitmapFactory.decodeResource(getResources(),
                                R.drawable.xiaoliansmiley40), surfaceAnimView);
                surfaceAnimView.addBean(surfaceBean);
                break;
        }
    }
}
