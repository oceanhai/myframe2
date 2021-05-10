package com.wuhai.myframe2.ui.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.wuhai.myframe2.BuildConfig;
import com.wuhai.myframe2.R;

/**
 * 作者 wh
 *
 * 创建日期 2021/5/10 14:24
 *
 * 描述：雄安智慧社保使用这个view，但v7下的AppCompatImageView 在一些机型中有问题
 * 而在rx下的 就没问题 貌似
 */
public class ClipImageView extends AppCompatImageView {

    public static final String TAG = "ClipImageView";
    
    private Context mContext;
    private PointF mDownPoint;//点：down的float型点
    private PointF mMiddlePoint;//点：两手指中间的点
    private Matrix mMatrix;
    private Matrix mTempMatrix;//临时矩阵

    //传入的图片的宽和高
    private int mBitmapWidth;
    private int mBitmapHeight;

    private final int MODE_NONE = 0;
    private final int MODE_DRAG = 1;//拖拽
    private final int MODE_ZOOM = 2;//缩放
    private final int MODE_POINTER_UP = 3;//手指离开
    private int CURR_MODE = MODE_NONE;

    private float mLastDistance;

    private Paint mFrontGroundPaint = new Paint();
    private int mTargetWidth;//裁剪框的宽
    private int mTargetHeight;//裁剪框的高
    private Xfermode mXfermode;
    private Rect r;
    private RectF rf;

    private float mCircleCenterX, mCircleCenterY;//裁剪框中心点(其实也是整个view的中心点)
    private float mCircleX, mCircleY;//裁剪框左上点
    private boolean isCutImage;
    private float mRatio = 1.0f;
    private int mWidth = 358; //默认值 dp
    private int mHeight = 441;//默认值 dp
    private int mSpace = 0;//解决一些手机right、bottom边框问题

    private Bitmap mBitmap;

    public ClipImageView(Context context) {
        super(context);
        mContext = context;
        setRadius();
    }

    public ClipImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setRadius();
    }

    /**
     * 设置要剪裁的图片
     *
     * @param bitmap
     */
    public void setBitmapData(Bitmap bitmap) {

        if (bitmap == null) {
            return;
        }

        mBitmapHeight = bitmap.getHeight();
        mBitmapWidth = bitmap.getWidth();
        setImageBitmap(bitmap);
        init();
    }

    private void init() {
        mDownPoint = new PointF();
        mMiddlePoint = new PointF();
        mMatrix = new Matrix();
        mTempMatrix = new Matrix();
        mFrontGroundPaint.setColor(Color.parseColor("#ac000000"));
        mFrontGroundPaint.setAntiAlias(true);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

        setScaleType(ImageView.ScaleType.MATRIX);
        post(new Runnable() {
            @Override
            public void run() {
                center();
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setRadius();
    }

    private void setRadius() {
//        mTargetWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 26,
//                mContext.getResources().getDisplayMetrics());
//        mTargetHeight =(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 32,
//                mContext.getResources().getDisplayMetrics());
        mTargetWidth = mWidth;
        mTargetHeight = mHeight;
//        Log.e(TAG, "mTargetWidth==" + mTargetWidth);
//        Log.e(TAG, "mTargetHeight==" + mTargetHeight);
        mCircleCenterX = getWidth() / 2;
        mCircleCenterY = getHeight() / 2;
        mCircleX = mCircleCenterX - mTargetWidth / 2;
        mCircleY = mCircleCenterY - mTargetHeight / 2;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face_head);
    }

    public void setRatio(float ratio) {
        if (mRatio != ratio) {
            mRatio = ratio;
            setRadius();
            invalidate();
        }
    }

    public void setWidthAndHeight(int width, int height) {
        if (mWidth != 0) {
            mWidth = width;
        }
        if (mHeight != 0) {
            mHeight = height;
        }
        setRadius();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isCutImage) {
            return;
        }
        if (rf == null || rf.isEmpty()) {
            r = new Rect(0, 0, getWidth(), getHeight());
            rf = new RectF(r);
        }
        // 画入前景圆形蒙板层
        int sc = canvas.saveLayer(rf, null, Canvas.ALL_SAVE_FLAG);
        //画入矩形黑色半透明蒙板层
        canvas.drawRect(r, mFrontGroundPaint);
        //设置Xfermode，目的是为了去除矩形黑色半透明蒙板层和圆形的相交部分
        mFrontGroundPaint.setXfermode(mXfermode);
        //画入正方形
        float left = mCircleCenterX - mTargetWidth / 2;
        float top = mCircleCenterY - mTargetHeight / 2;
        float right = mCircleCenterX + mTargetWidth / 2;
        float bottom = mCircleCenterY + mTargetHeight / 2;
        canvas.drawRect(left, top, right, bottom, mFrontGroundPaint);

        canvas.restoreToCount(sc);
        //清除Xfermode，防止影响下次画图
        mFrontGroundPaint.setXfermode(null);

//        Log.e(TAG, "getWidth()="+getWidth()+",getHeight()="+getHeight());
//        Log.e(TAG,"mCircleCenterX="+mCircleCenterX);
//        Log.e(TAG,"mCircleCenterY="+mCircleCenterY);
//        Log.e(TAG,"mBitmap.getWidth()="+mBitmap.getWidth()+",mBitmap.getHeight()="+mBitmap.getHeight());
        // 画出原图像
        //计算出绘制点
        int face_head_w = mBitmap.getWidth();
        int face_head_h = mBitmap.getHeight();
        float face_head_left =  mCircleCenterX-face_head_w/2;
        float face_head_top =  mCircleCenterY-face_head_h/2;
        canvas.drawBitmap(mBitmap, face_head_left, face_head_top, null);
    }

    /**
     * 截取Bitmap
     *
     * @return
     */
    public Bitmap clipImage() {
        isCutImage = true;
        Paint paint = new Paint();
        setDrawingCacheEnabled(true);
        Bitmap bitmap = getDrawingCache();
        Bitmap targetBitmap = Bitmap.createBitmap(mTargetWidth, mTargetHeight,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        int left = -bitmap.getWidth() / 2 + mTargetWidth / 2;
        int top = -getHeight() / 2 + mTargetHeight / 2;
        int right = bitmap.getWidth() / 2 + mTargetWidth / 2;
        int bottom = getHeight() / 2 + mTargetHeight / 2;
        RectF dst = new RectF(left, top, right, bottom);
        canvas.drawBitmap(bitmap, null, dst, paint);
        setDrawingCacheEnabled(false);
        bitmap.recycle();
        bitmap = null;
        isCutImage = false;

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "targetBitmap.getWidth()="+targetBitmap.getWidth());
            Log.d(TAG, "targetBitmap.getHeight()="+targetBitmap.getHeight());
        }
        //返回正方形图片
        return targetBitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mMatrix == null) {
            return super.onTouchEvent(event);
        }

        float[] values = new float[9];
        mMatrix.getValues(values);
        float left = values[Matrix.MTRANS_X];
        float top = values[Matrix.MTRANS_Y];
        float right = (left + mBitmapWidth * values[Matrix.MSCALE_X]);
        float bottom = (top + mBitmapHeight * values[Matrix.MSCALE_Y]);
//        Log.e(TAG, "left="+left+",top="+top+",right="+right+",bottom="+bottom);
        float x = 0f;
        float y = 0f;
        Log.e(TAG, "ACTION_MOVE-1111 event.getX()="+event.getX()+",event.getY()="+event.getY());
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                CURR_MODE = MODE_DRAG;
                mDownPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (getDistance(event) > 10f) {
                    CURR_MODE = MODE_ZOOM;
                    midPoint(mMiddlePoint, event);
                    mLastDistance = getDistance(event);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //如果当前模式为拖曳（单指触屏）
                if (CURR_MODE == MODE_DRAG || CURR_MODE == MODE_POINTER_UP) {
                    if (CURR_MODE == MODE_DRAG) {

                        x = event.getX() - mDownPoint.x;
                        y = event.getY() - mDownPoint.y;
                        Log.e(TAG, "ACTION_MOVE-1111 mDownPoint.x="+mDownPoint.x+",mDownPoint.y="+mDownPoint.y);
                        Log.e(TAG, "ACTION_MOVE-1111前 x="+x+",y="+y);
                        //left靠边
                        if (x + left > mCircleX) {
                            x = 0;
                        }
                        //right靠边
                        if (x + right < mCircleX + mTargetWidth+mSpace) {
                            x = 0;
                        }
                        //top靠边
                        if (y + top > mCircleY) {
                            y = 0;
                        }
                        //bottom靠边
                        if (y + bottom < mCircleY + mTargetHeight+mSpace) {
                            y = 0;
                        }
                        mMatrix.postTranslate(x, y);
                        mDownPoint.set(event.getX(), event.getY());
                        Log.e(TAG, "ACTION_MOVE-1111后 x="+x+",y="+y);
                    } else {
                        CURR_MODE = MODE_DRAG;
                        mDownPoint.set(event.getX(), event.getY());
                    }
                } else {
                    //否则当前模式为缩放（双指触屏）
                    float distance = getDistance(event);
                    if (distance > 10f) {
                        float scale = distance / mLastDistance;
                        Log.e(TAG, "scale="+scale+",distance="+distance+",mLastDistance="+mLastDistance);

                        //left靠边
                        if (left >= mCircleX) {
                            mMiddlePoint.x = 0;
                        }
                        //right靠边
                        if (right <= mCircleX + mTargetWidth+mSpace) {
                            mMiddlePoint.x = right;
                        }
                        //top靠边
                        if (top >= mCircleY) {
                            mMiddlePoint.y = 0;
                        }
                        //bottom靠边
                        if (bottom <= mCircleY + mTargetHeight+mSpace) {
                            mMiddlePoint.y = bottom;
                        }
                        mTempMatrix.set(mMatrix);
                        mTempMatrix.postScale(scale, scale, mMiddlePoint.x, mMiddlePoint.y);

                        float[] temp_values = new float[9];
                        mTempMatrix.getValues(temp_values);
                        float temp_left = temp_values[Matrix.MTRANS_X];
                        float temp_top = temp_values[Matrix.MTRANS_Y];
                        float temp_right = (temp_left + mBitmapWidth * temp_values[Matrix.MSCALE_X]);
                        float temp_bottom = (temp_top + mBitmapHeight * temp_values[Matrix.MSCALE_Y]);
//                        Log.e(TAG, "ACTION_MOVE-2222 temp_left="+temp_left+",temp_top="+temp_top+
//                                ",temp_right="+temp_right+",temp_bottom="+temp_bottom);
                        //靠边预判断
                        if (temp_left > mCircleX || temp_right < mCircleX + mTargetWidth ||
                                temp_top > mCircleY || temp_bottom < mCircleY + mTargetHeight) {
                            return true;
                        }
                        mMatrix.postScale(scale, scale, mMiddlePoint.x, mMiddlePoint.y);
                        mLastDistance = getDistance(event);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                CURR_MODE = MODE_NONE;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                CURR_MODE = MODE_POINTER_UP;
                break;
        }
        setImageMatrix(mMatrix);
        return true;
    }


    /**
     * 两点的距离
     */
    private float getDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 两点的中点
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * 横向、纵向居中
     */
    protected void center() {
        float height = mBitmapHeight;
        float width = mBitmapWidth;

        float screenWith = getScreenWidth(getContext());
        float screenHeight = getScreenHeight(getContext());

        float scale = Math.max(screenWith / width, screenHeight / height);

        float deltaX = -(width * scale - getWidth()) / 2.0f;
        float deltaY = -(height * scale - getHeight()) / 2.0f;
        mMatrix.postScale(scale, scale);
        mMatrix.postTranslate(deltaX, deltaY);
        setImageMatrix(mMatrix);
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
