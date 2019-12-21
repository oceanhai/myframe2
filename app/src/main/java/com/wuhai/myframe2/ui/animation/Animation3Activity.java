package com.wuhai.myframe2.ui.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：三大动画
 * 属性动画
 */
public class Animation3Activity extends BaseActivity implements View.OnClickListener {

    private int[] mRes = {R.id.imageView_a, R.id.imageView_b, R.id.imageView_c,
            R.id.imageView_d, R.id.imageView_e};
    private List<ImageView> mImageViews = new ArrayList<>();
    private boolean mFlag = true;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Animation3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_animation3);
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
        int sum = mRes.length;
        for (int i = 0; i < sum; i++) {
            ImageView imageView = (ImageView) findViewById(mRes[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }
    }

    private void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_a:
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
            case R.id.imageView_b:
                Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_c:
                Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_d:
                Toast.makeText(this, "d", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_e:
                Toast.makeText(this, "e", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(mImageViews.get(0),
                "alpha", 0.5F, 1F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageViews.get(1),
                "translationY", 200F, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageViews.get(2),
                "translationX", 200F, 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageViews.get(3),
                "translationY", -200F, 0);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageViews.get(4),
                "translationX", -200F, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        mFlag = true;
    }


    private int num = 1;
    private void startAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(
                mImageViews.get(0),
                "alpha",
                1F,
                0.5F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(
                mImageViews.get(1),
                "translationY",
                200F);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(
                mImageViews.get(2),
                "translationX",
                200F);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(
                mImageViews.get(3),
                "translationY",
                -200F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(
                mImageViews.get(4),
                "translationX",
                -200F);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        switch (num%9){
            case 1:
                //xml android:interpolator="@android:anim/accelerate_interpolator"
                //设置动画为加速动画(动画播放中越来越快)
                set.setInterpolator(new AccelerateInterpolator());
                Log.e(TAG,"case 1");
                break;
            case 2:
                //xml android:interpolator="@android:anim/decelerate_interpolator"
                //设置动画为减速动画(动画播放中越来越慢)
                set.setInterpolator(new DecelerateInterpolator());
                Log.e(TAG,"case 2");
                break;
            case 3:
                //xml android:interpolator="@android:anim/accelerate_decelerate_interpolator"
                //设置动画为先加速在减速(开始速度最快 逐渐减慢)
                set.setInterpolator(new AccelerateDecelerateInterpolator());
                Log.e(TAG,"case 3");
                break;
            case 4:
                //xml android:interpolator="@android:anim/anticipate_interpolator"
                //先反向执行一段，然后再加速反向回来（相当于我们弹簧，先反向压缩一小段，然后在加速弹出）
                set.setInterpolator(new AnticipateInterpolator());
                Log.e(TAG,"case 4");
                break;
            case 5:
                //xml android:interpolator="@android:anim/anticipate_overshoot_interpolator"
                //同上先反向一段，然后加速反向回来，执行完毕自带回弹效果（更形象的弹簧效果）
                set.setInterpolator(new AnticipateOvershootInterpolator());
                Log.e(TAG,"case 5");
                break;
            case 6:
                //xml android:interpolator="@android:anim/bounce_interpolator"
                //执行完毕之后会回弹跳跃几段（相当于我们高空掉下一颗皮球，到地面是会跳动几下）
                set.setInterpolator(new BounceInterpolator());
                Log.e(TAG,"case 6");
                break;
            case 7:
                //xml android:interpolator="@android:anim/cycle_interpolator"
                //循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)
                set.setInterpolator(new CycleInterpolator(2));
                Log.e(TAG,"case 7");
                break;
            case 8:
                //xml android:interpolator="@android:anim/linear_interpolator"
                //线性均匀改变
                set.setInterpolator(new LinearInterpolator());
                Log.e(TAG,"case 8");
                break;
            case 0:
                //xml android:interpolator="@android:anim/overshoot_interpolator"
                //加速执行，结束之后回弹
                set.setInterpolator(new OvershootInterpolator());
                Log.e(TAG,"case 0");
                break;
        }
        num++;
        set.playTogether(
                animator0,
                animator1,
                animator2,
                animator3,
                animator4);
        set.start();
        mFlag = false;
    }

}
