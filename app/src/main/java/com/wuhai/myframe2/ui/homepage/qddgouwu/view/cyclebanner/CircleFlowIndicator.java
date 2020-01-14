
package com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

/**
 * A FlowIndicator which draws circles (one for each view).
 * <br/>
 * Availables attributes are:<br/>
 * <ul>
 * activeColor: Define the color used to draw the active circle (default to white)
 * </ul>
 * <ul>
 * inactiveColor: Define the color used to draw the inactive circles (default to 0x44FFFFFF)
 * </ul>
 * <ul>
 * inactiveType: Define how to draw the inactive circles, either stroke or fill (default to stroke)
 * </ul>
 * <ul>
 * activeType: Define how to draw the active circle, either stroke or fill (default to fill)
 * </ul>
 * <ul>
 * fadeOut: Define the time (in ms) until the indicator will fade out (default to 0 = never fade out)
 * </ul>
 * <ul>
 * radius: Define the circle radius (default to 4.0)
 * </ul>
 *
 * @author http://blog.csdn.net/finddreams
 */
public class CircleFlowIndicator extends View implements FlowIndicator,
        AnimationListener {
    private static final int STYLE_STROKE = 0;
    private static final int STYLE_FILL = 1;

    private float radius;
    private float circleCenterDistance;
    private float activeRadius = 0.5f;
    private int fadeOutTime = 0;
    private Paint mPaintCurrent = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintNext = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintInactive = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ViewFlow viewFlow;
    private int currentScroll = 0;
    private int originPosition = 0;
    private int currentPosition;
    private int nextPosition;
    private int flowWidth = 0;
    private FadeTimer timer;
    public AnimationListener animationListener = this;
    private Animation animation;
    private boolean mCentered = false;

    /**
     * Default constructor
     *
     * @param context
     */
    public CircleFlowIndicator(Context context) {
        super(context, null);
    }

    /**
     * The contructor used with an inflater
     *
     * @param context
     * @param attrs
     */
    public CircleFlowIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        circleCenterDistance = 2 * radius + radius;

        initColors(0xFFFFFFFF, 0xFFFFFFFF, STYLE_FILL, STYLE_FILL);

        currentPosition = 0;
        nextPosition = currentPosition + 1;
    }

    private void initColors(int activeColor, int inactiveColor, int activeType,
                            int inactiveType) {
        // Select the paint type given the type attr
        switch (inactiveType) {
            case STYLE_FILL:
                mPaintInactive.setStyle(Style.FILL);
                break;
            default:
                mPaintInactive.setStyle(Style.STROKE);
        }
        mPaintInactive.setColor(inactiveColor);
        mPaintInactive.setAlpha(128);

        // Select the paint type given the type attr
        switch (activeType) {
            case STYLE_STROKE:
                mPaintCurrent.setStyle(Style.STROKE);
                break;
            default:
                mPaintCurrent.setStyle(Style.FILL);
                mPaintNext.setStyle(Style.FILL);
        }
        mPaintCurrent.setColor(activeColor);
        mPaintCurrent.setAlpha(255);
        mPaintNext.setColor(activeColor);
        mPaintNext.setAlpha(255);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = 0;
        if (viewFlow != null) {
            count = viewFlow.getViewsCount();
        }

        int leftPadding = getPaddingLeft();
        final float alphaRatio = flowWidth == 0 ? 0 : Math.abs(((float) currentScroll) / flowWidth);
        // Draw circle indicators.
        for (int i = 0; i < count; i++) {
            Paint paint;
            if (i == currentPosition) {
                paint = mPaintCurrent;
                paint.setAlpha((int) (255 * (1 - alphaRatio / 2)));
            } else if (i == nextPosition) {
                paint = mPaintNext;
                paint.setAlpha((int) (255 * (0.5 + alphaRatio / 2)));
            } else {
                paint = mPaintInactive;
            }
            canvas.drawCircle(leftPadding + radius + (i * circleCenterDistance),
                    getPaddingTop() + radius, radius, paint);
        }
    }

    @Override
    public void setViewFlow(ViewFlow view) {
        resetTimer();
        viewFlow = view;
        flowWidth = viewFlow.getWidth();
        if (viewFlow.getViewsCount() <= 1) {
            setVisibility(GONE);
        }

        invalidate();
    }

    @Override
    public void onSwitched(View view, int position) {
        originPosition = position;
        currentPosition = position % viewFlow.getViewsCount();
        nextPosition = (position + 1) % viewFlow.getViewsCount();
        currentScroll = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.taptwo.android.widget.FlowIndicator#onScrolled(int, int, int,
     * int)
     */
    @Override
    public void onScrolled(int h, int v, int oldh, int oldv) {
        resetTimer();
        flowWidth = viewFlow.getWidth();
        if (viewFlow.getViewsCount() * flowWidth != 0) {
            if (currentScroll == 0) {
                currentScroll = 1;
            } else {
                currentScroll += h - oldh;
                if (currentScroll < 0) {
                    nextPosition = (originPosition - 1) % viewFlow.getViewsCount();
                } else {
                    nextPosition = (originPosition + 1) % viewFlow.getViewsCount();
                }

                invalidate();
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // We were told how big to be
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            int count = 3;
            if (viewFlow != null) {
                count = viewFlow.getViewsCount();
            }
            float temp = circleCenterDistance - 2 * radius;
            result = (int) (getPaddingLeft() + getPaddingRight()
                    + (count * 2 * radius) + (count - 1) * temp);
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // We were told how big to be
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (2 * radius + getPaddingTop() + getPaddingBottom());
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Sets the fill color
     *
     * @param color ARGB value for the text
     */
    public void setFillColor(int color) {
        mPaintCurrent.setColor(color);
        invalidate();
    }

    /**
     * Sets the stroke color
     *
     * @param color ARGB value for the text
     */
    public void setStrokeColor(int color) {
        mPaintInactive.setColor(color);
        invalidate();
    }

    /**
     * Resets the fade out timer to 0. Creating a new one if needed
     */
    private void resetTimer() {
        // Only set the timer if we have a timeout of at least 1 millisecond
        if (fadeOutTime > 0) {
            // Check if we need to create a new timer
            if (timer == null || timer._run == false) {
                // Create and start a new timer
                timer = new FadeTimer();
                timer.execute();
            } else {
                // Reset the current tiemr to 0
                timer.resetTimer();
            }
        }
    }

    /**
     * Counts from 0 to the fade out time and animates the view away when
     * reached
     */
    private class FadeTimer extends AsyncTask<Void, Void, Void> {
        // The current availableCount
        private int timer = 0;
        // If we are inside the timing loop
        private boolean _run = true;

        public void resetTimer() {
            timer = 0;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (_run) {
                try {
                    // Wait for a millisecond
                    Thread.sleep(1);
                    // Increment the timer
                    timer++;

                    // Check if we've reached the fade out time
                    if (timer == fadeOutTime) {
                        // Stop running
                        _run = false;
                    }
                } catch (InterruptedException e) {
                    // Do nothing.
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            animation = AnimationUtils.loadAnimation(getContext(),
                    android.R.anim.fade_out);
            animation.setAnimationListener(animationListener);
            startAnimation(animation);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        setVisibility(View.GONE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }
}