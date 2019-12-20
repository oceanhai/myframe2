package com.wuhai.myframe2.ui.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
 * 补间动画（视图动画）
 */
public class Animation2Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tween_start)
    Button tweenStart;
    @BindView(R.id.tween_image)
    ImageView tweenImage;
    @BindView(R.id.translate)
    Button translate;
    @BindView(R.id.scale)
    Button scale;
    @BindView(R.id.rotate)
    Button rotate;
    @BindView(R.id.alpha)
    Button alpha;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.tween_start2)
    Button tweenStart2;
    @BindView(R.id.custom_animation)
    Button customAnimation;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Animation2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_animation2);
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
        tweenStart.setOnClickListener(this);

        translate.setOnClickListener(this);
        scale.setOnClickListener(this);
        rotate.setOnClickListener(this);
        alpha.setOnClickListener(this);
        tweenStart2.setOnClickListener(this);

        customAnimation.setOnClickListener(this);

        image2.setOnClickListener(this);
    }

    private void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tween_start://组合动画
                /**
                 * R.anim.tween_anim 只要替换成你想要的xml动画，即可实现单一形式动画
                 */
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween_anim);
                tweenImage.startAnimation(animation);
                break;
            case R.id.translate://位移
                Animation translateAnimation =
                        new TranslateAnimation(0, 200, 0, 200);
                // 创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
                // 参数分别是：
                // 1. fromXDelta ：视图在水平方向x 移动的起始值
                // 2. toXDelta ：视图在水平方向x 移动的结束值
                // 3. fromYDelta ：视图在竖直方向y 移动的起始值
                // 4. toYDelta：视图在竖直方向y 移动的结束值
                translateAnimation.setDuration(3000);
                //动画执行完毕后固定在动画结束的那一帧
                translateAnimation.setFillAfter(true);
                //监听
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.e(TAG, "onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.e(TAG, "onAnimationEnd");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.e(TAG, "onAnimationRepeat");
                    }
                });
                // 播放动画直接 startAnimation(translateAnimation)
                //如：
                image2.startAnimation(translateAnimation);
                break;
            case R.id.scale://缩放
                Animation scaleAnimation =
                        new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                // 1. fromX ：动画在水平方向X的结束缩放倍数
                // 2. toX ：动画在水平方向X的结束缩放倍数
                // 3. fromY ：动画开始前在竖直方向Y的起始缩放倍数
                // 4. toY：动画在竖直方向Y的结束缩放倍数
                // 5. pivotXType:缩放轴点的x坐标的模式
                // 6. pivotXValue:缩放轴点x坐标的相对值
                // 7. pivotYType:缩放轴点的y坐标的模式
                // 8. pivotYValue:缩放轴点y坐标的相对值
                // pivotXType = Animation.ABSOLUTE:缩放轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
                // pivotXType = Animation.RELATIVE_TO_SELF:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
                // pivotXType = Animation.RELATIVE_TO_PARENT:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
                scaleAnimation.setDuration(3000);
                // 使用
                image2.startAnimation(scaleAnimation);
                break;
            case R.id.rotate://旋转
                Animation rotateAnimation =
                        new RotateAnimation(0, -270, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                // 1. fromDegrees ：动画开始时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
                // 2. toDegrees ：动画结束时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
                // 3. pivotXType：旋转轴点的x坐标的模式
                // 4. pivotXValue：旋转轴点x坐标的相对值
                // 5. pivotYType：旋转轴点的y坐标的模式
                // 6. pivotYValue：旋转轴点y坐标的相对值
                // pivotXType = Animation.ABSOLUTE:旋转轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
                // pivotXType = Animation.RELATIVE_TO_SELF:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
                // pivotXType = Animation.RELATIVE_TO_PARENT:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
                rotateAnimation.setDuration(3000);
                image2.startAnimation(rotateAnimation);
                break;
            case R.id.alpha://淡入淡出
                Animation alphaAnimation = new AlphaAnimation(1, 0);
                // 1. fromAlpha:动画开始时视图的透明度(取值范围: -1 ~ 1)
                // 2. toAlpha:动画结束时视图的透明度(取值范围: -1 ~ 1)
                alphaAnimation.setDuration(3000);
                image2.startAnimation(alphaAnimation);
                break;
            case R.id.tween_start2://动画组合2
                // 组合动画设置
                AnimationSet setAnimation = new AnimationSet(true);

                // 特别说明以下情况
                // 因为在下面的旋转动画设置了无限循环(RepeatCount = INFINITE)
                // 所以动画不会结束，而是无限循环
                // 所以组合动画的下面两行设置是无效的
                setAnimation.setRepeatMode(Animation.RESTART);
                setAnimation.setRepeatCount(1);// 设置了循环一次,但无效

                // 旋转动画
                Animation rotate =
                        new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1000);
                rotate.setRepeatMode(Animation.RESTART);
                rotate.setRepeatCount(Animation.INFINITE);

                // 平移动画
                Animation translate = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, -0.5f,
                        TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
                        TranslateAnimation.RELATIVE_TO_SELF, 0
                        , TranslateAnimation.RELATIVE_TO_SELF, 0);
                translate.setDuration(10000);

                // 透明度动画
                Animation alpha = new AlphaAnimation(1, 0);
                alpha.setDuration(3000);
                alpha.setStartOffset(7000);

                // 缩放动画
                Animation scale1 =
                        new ScaleAnimation(1, 0.5f, 1, 0.5f, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scale1.setDuration(1000);
                scale1.setStartOffset(4000);

                // 将创建的子动画添加到组合动画里
                setAnimation.addAnimation(alpha);
                setAnimation.addAnimation(rotate);
                setAnimation.addAnimation(translate);
                setAnimation.addAnimation(scale1);
                // 使用
                image2.startAnimation(setAnimation);
                break;
            case R.id.custom_animation://自定义补间动画
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                //屏幕尺寸
                Log.e(TAG,metrics.toString());
                CustomAnimation animation1 =
                        new CustomAnimation(metrics.xdpi/2,metrics.ydpi/2,3500);
                image2.startAnimation(animation1);
                break;
            case R.id.image2://image2
                /**
                 * 位移setFillAfter(true)，动画执行完毕后固定在动画结束的那一帧，但点击效果却没有了，不会toast
                 */
                Toast.makeText(this, "我被点击了", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
