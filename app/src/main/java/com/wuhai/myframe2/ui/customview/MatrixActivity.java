package com.wuhai.myframe2.ui.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

/**
 * 作者 wh
 *
 * 创建日期 2021/4/27 19:19
 *
 * 描述：Matrix图片放大缩小
 */
public class MatrixActivity extends AppCompatActivity {

    ImageView img1=null;
    public static final  int NONE=0;
    public static final  int DRAG=1;
    public static final  int ZOOM=2;
    public int state=NONE;
    Matrix matrix=new Matrix();
    Matrix  m=new Matrix();
    PointF   startPoint=null;
    PointF   startPoint2=null;
    float    startDist=0;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MatrixActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_matrix);
        img1=(ImageView)findViewById(R.id.img1);
        img1.setOnTouchListener(lst);
    }

    View.OnTouchListener lst=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action=event.getAction();
            action=action&0x00ff;
            switch(action)
            {
                //一根手指按下  拖拽
                case MotionEvent.ACTION_DOWN:
                    //保存上一次移动结束后的状态矩阵
                    m.set(matrix);
                    startPoint=new PointF(event.getX(), event.getY());
                    state=DRAG;
                    break;
                //2根手指头
                case MotionEvent.ACTION_POINTER_DOWN:
                    state=ZOOM;
                    if(event.getPointerCount()==2)
                    {
                        //记录2根手指的起始位置
                        m.set(matrix);
                        startPoint=new PointF(event.getX(0),event.getY(0));
                        startPoint2=new PointF(event.getX(1),event.getY(1));
                        startDist=getDist(startPoint.x, startPoint.y,
                                startPoint2.x, startPoint2.y);
                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    if(event.getPointerCount()==1)
                    {
                        state=DRAG;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(state==DRAG)
                    {
                        //处于拖动状态
                        float x= event.getX();
                        float y=event.getY();
                        float distX=x-startPoint.x;
                        float distY=y-startPoint.y;
                        //在每一次移动之前要先恢复原有状态矩阵
                        matrix.set(m);
                        matrix.postTranslate(distX, distY);
                    }else if(state==ZOOM)
                    {
                        //双点触摸 实现缩放  和旋转
                        if(event.getPointerCount()>=2)
                        {
                            PointF p1=new PointF(event.getX(0),event.getY(0));
                            PointF p2=new PointF(event.getX(1),event.getY(1));
                            float dist=getDist(p1.x, p1.y, p2.x, p2.y);
                            float scale=dist/startDist;
                            matrix.set(m);
                            matrix.postScale(scale,scale,(p1.x+p2.x)/2,(p1.y+p2.y)/2 );
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP://最后一根手指抬起
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_OUTSIDE:
                    state=NONE;
                    break;
            }
            img1.setImageMatrix(matrix);
            img1.invalidate();
            return true;
        }
    };

    public float  getDist(float x1,float y1,float x2,float y2)
    {
        double value= Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return (float)value;
    }

}
