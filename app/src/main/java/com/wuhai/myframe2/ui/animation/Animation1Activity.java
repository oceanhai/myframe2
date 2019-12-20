package com.wuhai.myframe2.ui.animation;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：三大动画
 * 逐帧动画
 */
public class Animation1Activity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.frame_iv1)
    ImageView frameIv1;
    @BindView(R.id.frame_start)
    Button frameStart;
    @BindView(R.id.frame_stop)
    Button frameStop;
    @BindView(R.id.frame_start2)
    Button frameStart2;
    @BindView(R.id.frame_stop2)
    Button frameStop2;
    @BindView(R.id.frame_iv2)
    ImageView frameIv2;


    private AnimationDrawable animationDrawable;
    private AnimationDrawable animationDrawable2;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Animation1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_animation1);
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
        //代码设置方式一  对应的布局直接设置android:background="@drawable/frame_animation"
//        frameIv1.setBackgroundResource(R.drawable.frame_animation);
//        animationDrawable = (AnimationDrawable) frameIv1.getBackground();

        //代码设置方式二  对应的布局直接设置android:src="@drawable/frame_animation"
//        frameIv1.setImageResource(R.drawable.frame_animation);
        animationDrawable = (AnimationDrawable) frameIv1.getDrawable();

        //纯代码实现
        animationDrawable2 = new AnimationDrawable();
        for (int x=1;x<9;x++){
            int id = getResources().getIdentifier("icon"+x,"drawable",getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable2.addFrame(drawable,150);
        }
    }

    private void setListener() {
        frameStart.setOnClickListener(this);
        frameStop.setOnClickListener(this);
        frameStart2.setOnClickListener(this);
        frameStop2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frame_start://开始播放
                animationDrawable.start();
                break;
            case R.id.frame_stop://停止播放
                animationDrawable.stop();
                break;
            case R.id.frame_start2://开始播放
                animationDrawable2.setOneShot(false);//循环 "Android 动画总结"文章说的不对，么必要stop再start
                frameIv2.setImageDrawable(animationDrawable2);//这么用 一点没问题呢，没api限制
//                frameIv2.setBackground(animationDrawable2);//需要api 16至少
                animationDrawable2.start();
                break;
            case R.id.frame_stop2://停止播放
                animationDrawable2.stop();
                break;
        }
    }
}
