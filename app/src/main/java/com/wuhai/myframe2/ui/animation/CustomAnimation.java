package com.wuhai.myframe2.ui.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class CustomAnimation extends Animation {
    private float centerX;
    private float centerY;
    // 定义动画的持续事件
    private int duration;
    private Camera camera = new Camera();
    public CustomAnimation(float x,float y,int duration)
    {
        this.centerX = x;
        this.centerY = y;
        this.duration = duration;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //设置动画的持续时间
        setDuration(duration);
        //设置动画结束后保留效果
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //super.applyTransformation(interpolatedTime, t);
        camera.save();
        // 根据 interpolatedTime 时间来控制X,Y,Z 上偏移
        camera.translate(100.0f - 100.f * interpolatedTime,150.0f * interpolatedTime - 150,
                80.0f - 80.0f * interpolatedTime);
        // 根据 interploatedTime 设置在 X 轴 和 Y 轴旋转
        camera.rotateX(360 * interpolatedTime);
        camera.rotateY(360 * interpolatedTime);
        // 获取 Transformation 参数的 Matrix 对象
        Matrix matrix = t.getMatrix();
        camera.getMatrix(matrix);
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
        camera.restore();
    }
}


