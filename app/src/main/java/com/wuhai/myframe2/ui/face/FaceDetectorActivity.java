package com.wuhai.myframe2.ui.face;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcFaceDetectorBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：android FaceDetector
 * 输入图片必须为Bitmap RGB565格式
 * 人脸的检测方法是用双眼来检测人脸的位置，也就是说无法检测到嘴、侧脸等，双眼必须同时可见，并且眼镜会影响检测的效果
 * 检测到的人脸存放到FaceDetector.Face类中，该类无法再扩展。从该类可以获取到人眼的中心位置和双眼之间的具体。
 *
 */
public class FaceDetectorActivity extends BaseActivity {

    private AcFaceDetectorBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FaceDetectorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        setContentView(new myView(this));
        parseIntent();
//        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_face_detector);
    }

    private void setListener() {
    }


    private class myView extends View {

        private int imageWidth, imageHeight;
        private int numberOfFace = 18;
        private FaceDetector myFaceDetect;
        private FaceDetector.Face[] myFace;
        float myEyesDistance;
        int numberOfFaceDetected;

        Bitmap myBitmap;

        public myView(Context context) {
            super(context);
            BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
            //输入图片必须为Bitmap RGB565格式
            BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565;
            //单个头像图片，鞠婧祎小美女的头像哦
//            myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face01, BitmapFactoryOptionsbfo);
            //多个头像图片
            myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faces, BitmapFactoryOptionsbfo);
            imageWidth = myBitmap.getWidth();
            imageHeight = myBitmap.getHeight();
            myFace = new FaceDetector.Face[numberOfFace];
            myFaceDetect = new FaceDetector(imageWidth, imageHeight, numberOfFace);
            numberOfFaceDetected = myFaceDetect.findFaces(myBitmap, myFace);
        }

        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub

            canvas.drawBitmap(myBitmap, 0, 0, null);

            Paint myPaint = new Paint();
            myPaint.setColor(Color.GREEN);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3);

            for(int i=0; i < numberOfFaceDetected; i++)
            {
                FaceDetector.Face face = myFace[i];
                //脸的中点
                PointF myMidPoint = new PointF();
                face.getMidPoint(myMidPoint);
                //返回眼睛之间的距离
                myEyesDistance = face.eyesDistance();
                canvas.drawRect(
                        (int)(myMidPoint.x - myEyesDistance),
                        (int)(myMidPoint.y - myEyesDistance),
                        (int)(myMidPoint.x + myEyesDistance),
                        (int)(myMidPoint.y + myEyesDistance),
                        myPaint);
            }
        }
    }
}
