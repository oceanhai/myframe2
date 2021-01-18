package com.wuhai.navigation1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by flyeek on 3/3/16.
 * Tab Item view for {@link NavigationTabLayout}.
 */
public class TabItem extends View {

    /**
     * Text content of title.
     */
    private String mTextValue;

    /**
     * Text size for tab title.
     */
    private int mTextSize;

    /**
     * Text color for tab title in normal state.
     */
    private int mTextColorNormal;

    /**
     * Text color for tab title in selected state.
     */
    private int mTextColorSelect;

    /**
     * Paint for tab title in normal state.
     */
    private Paint mTextPaintNormal;

    /**
     * Paint for tab title in selected state.
     */
    private Paint mTextPaintSelect;

    /**
     * Icon in normal state.
     */
    private Bitmap mIconNormal;

    /**
     * Icon in selected state.
     */
    private Bitmap mIconSelect;

    /**
     * Icon in normal state.
     */
    private Drawable mBackgroundSelected;

    /**
     * Paint for tab icon in normal state.
     */
    private Paint mIconPaintNormal;

    /**
     * Paint for tab icon in selected state.
     */
    private Paint mIconPaintSelect;

    /**
     * Rect for record text width and height.
     */
    private Rect mBoundText;

    /**
     * Tab View's width and height.
     */
    private int mViewHeight, mViewWidth;

    /**
     * icon and text gap.
     */
    private int mIconTextGap;

    private int mBackgroundAlpha;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initText();
    }

    private void initView() {
        mBoundText = new Rect();
    }

    // Init paint for title and icon.
    private void initText() {
        mTextPaintNormal = new Paint();
        mTextPaintNormal.setTextSize(mTextSize);
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAntiAlias(true);
        mTextPaintNormal.setAlpha(0xff);

        mTextPaintSelect = new Paint();
        mTextPaintSelect.setTextSize(mTextSize);
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAntiAlias(true);
        mTextPaintSelect.setAlpha(0);

        mIconPaintSelect = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaintSelect.setAlpha(0);

        mIconPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaintNormal.setAlpha(0xff);

        mBackgroundAlpha = 0;

    }

    private void measureText() {
        mTextPaintNormal.getTextBounds(mTextValue, 0, mTextValue.length(), mBoundText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;

        measureText();

        // Measure width.
        int contentWidth = Math.max(mBoundText.width(), mIconNormal.getWidth());
        int desiredWidth = getPaddingLeft() + getPaddingRight() + contentWidth;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = Math.min(widthSize, desiredWidth);
                break;
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = desiredWidth;
                break;
        }

        // Measure height.
        Paint.FontMetrics fontMetrics = mTextPaintNormal.getFontMetrics();
        int contentHeight = (int) (fontMetrics.descent + Math.abs(fontMetrics.ascent)) +
                mIconNormal.getHeight();
        int desiredHeight = getPaddingTop() + getPaddingBottom() + contentHeight;
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(heightSize, desiredHeight);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = contentHeight;
                break;
        }

        setMeasuredDimension(width, height);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBitmap(canvas);
        drawText(canvas);
    }

    // Draw icons.
    private void drawBitmap(Canvas canvas) {
        if(mBackgroundSelected != null){
            int backgroundLeft = (mViewWidth - mBackgroundSelected.getIntrinsicWidth()) / 2;
            int backgroundTop = 0;
            mBackgroundSelected.setBounds(backgroundLeft, backgroundTop, backgroundLeft +
                    mBackgroundSelected.getIntrinsicWidth(), backgroundTop + mBackgroundSelected.getIntrinsicHeight());
            canvas.save();
            mBackgroundSelected.setAlpha(mBackgroundAlpha);
            mBackgroundSelected.draw(canvas);
            canvas.restore();
        }

        int left = (mViewWidth - mIconNormal.getWidth()) / 2;
        int top = (mViewHeight - mIconNormal.getHeight() - mIconTextGap - mBoundText.height()) / 2;
        canvas.drawBitmap(mIconNormal, left, top, mIconPaintNormal);
        canvas.drawBitmap(mIconSelect, left, top, mIconPaintSelect);

    }

    // Draw title
    private void drawText(Canvas canvas) {
        float x = (mViewWidth - mBoundText.width()) / 2.0f;
        float y = (mViewHeight + mIconNormal.getHeight() + mIconTextGap + mBoundText.height()) / 2.0F;
        canvas.drawText(mTextValue, x, y, mTextPaintNormal);
        canvas.drawText(mTextValue, x, y, mTextPaintSelect);
    }

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
        mTextPaintNormal.setTextSize(textSize);
        mTextPaintSelect.setTextSize(textSize);
    }

    public void setTextColorSelect(int mTextColorSelect) {
        this.mTextColorSelect = mTextColorSelect;
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAlpha(0);
    }

    public void setTextColorNormal(int mTextColorNormal) {
        this.mTextColorNormal = mTextColorNormal;
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAlpha(0xff);
    }

    public void setTextValue(String TextValue) {
        this.mTextValue = TextValue;
    }

    public void setIconText(int[] iconSelId, String TextValue) {
        this.mIconNormal = BitmapFactory.decodeResource(getResources(), iconSelId[0]);
        this.mIconSelect = BitmapFactory.decodeResource(getResources(), iconSelId[1]);
        this.mTextValue = TextValue;
    }

    // selected background
    public void setBackgroundIcon(int resId) {
        if (resId != -1) {
            mBackgroundSelected = getResources().getDrawable(resId);
        }
    }

    public void setIconTextGap(int gap) {
        mIconTextGap = gap;
    }

    // Use alpha to implement transition animation.
    public void setTabAlpha(float alpha) {
        int paintAlpha = (int) (alpha * 255);
        mIconPaintSelect.setAlpha(paintAlpha);
        mIconPaintNormal.setAlpha(255 - paintAlpha);
        mTextPaintSelect.setAlpha(paintAlpha);
        mTextPaintNormal.setAlpha(255 - paintAlpha);
        mBackgroundAlpha = paintAlpha;
        invalidate();
    }
}
