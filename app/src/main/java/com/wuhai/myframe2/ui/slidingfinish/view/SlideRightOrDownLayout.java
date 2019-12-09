package com.wuhai.myframe2.ui.slidingfinish.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Scroller;

import androidx.viewpager.widget.ViewPager;

import com.wuhai.myframe2.R;

import java.util.LinkedList;
import java.util.List;


/**
 *
 * 再SwipeBackLayout基础上改造
 * 效果仿头条 评论页
 */
public class SlideRightOrDownLayout extends FrameLayout {
	private static final String TAG = SlideRightOrDownLayout.class.getSimpleName();
	private View mContentView;
	private int mTouchSlop;//触发移动事件的最短距离
	private int downX;
	private int downY;
	private int tempX;
	private int tempY;
	private Scroller mScroller;
	private int viewWidth;
	private int viewHeight;
	private boolean isSildingX;
	private boolean isSildingY;
	private boolean isFinish;
	private Drawable mShadowDrawable;//阴影
	private Activity mActivity;
	private List<ViewPager> mViewPagers = new LinkedList<ViewPager>();//有viewpager布局时候，viewpager的集合
	private ListView mListView = null;//有listview时候

	public SlideRightOrDownLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideRightOrDownLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		//触发移动事件的最短距离
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mScroller = new Scroller(context);

		mShadowDrawable = getResources().getDrawable(R.drawable.shadow_left);
	}

	public void attachToActivity(Activity activity) {
		mActivity = activity;
//		TypedArray a = activity.getTheme().obtainStyledAttributes(
//				new int[] { android.R.attr.windowBackground });
//		int background = a.getResourceId(0, 0);
//		a.recycle();

		/**
		 * UI 界面框架图
		 * Activity
		 *  PhoneWindow
		 *   DecorView
		 *    ViewGroup(LinearLayout )  getChildAt(0)取到
		 *     TitleView
		 *     ContentView
		 */

		ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();// 获得根视图
		ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
//		decorChild.setBackgroundResource(background);//TODO 设置背景 其实没啥特殊作用 侧滑显示前一个activity是在清单文件中设置透明主题
		decor.removeView(decorChild);//把decorChild从decor移除，不然decorChild是无法被SwipeBackLayout addview操作的(因为decorChild存在父窗体)
		addView(decorChild);//SwipeBackLayout addview操作
		setContentView(decorChild);//mContentView 其实就是当前SwipeBackLayout
		decor.addView(this);// 在根视图，添加自定义的SwipeBackLayout
	}

	private void setContentView(View decorChild) {
		mContentView = (View) decorChild.getParent();// 父界面 mContentView 其实就是当前SwipeBackLayout
	}

	/**
	 * 事件拦截操作
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// 处理ViewPager冲突问题
		ViewPager mViewPager = getTouchViewPager(mViewPagers, ev);
		Log.i(TAG, "mViewPager = " + mViewPager);

		if (mViewPager != null && mViewPager.getCurrentItem() != 0) {
			return super.onInterceptTouchEvent(ev);
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = tempX = (int) ev.getRawX();
			downY = tempY = (int) ev.getRawY();
			Log.e(TAG,"onInterceptTouchEvent-ACTION_DOWN downX="+downX+",downY="+downY);
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getRawX();
			int moveY = (int) ev.getRawY();
			Log.e(TAG,"onInterceptTouchEvent-ACTION_MOVE moveX="+moveX+",moveY="+moveY+",mTouchSlop="+mTouchSlop);

			//处理listview冲突问题
			if(mListView != null){
				return false;
			}

			// 横向	满足此条件屏蔽SlideRightOrDownLayout里面子类的touch事件
			if (moveX - downX > mTouchSlop
					&& Math.abs(moveY - downY) < mTouchSlop) {
				Log.e(TAG,"onInterceptTouchEvent-ACTION_MOVE拦截X---------------------------------");
				return true;
			}
			// 纵向	满足此条件屏蔽SlideRightOrDownLayout里面子类的touch事件
			if (moveX - downX < mTouchSlop
					&& Math.abs(moveY - downY) > mTouchSlop && moveY - downY>0) {//向下滑:moveY - downY>0
				Log.e(TAG,"onInterceptTouchEvent-ACTION_MOVE拦截Y---------------------------------");
				return true;
			}
			Log.e(TAG,"onInterceptTouchEvent-ACTION_MOVE未拦截---------------------------------");
			break;
		}

		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) event.getRawX();
			int moveY = (int) event.getRawY();
			int deltaX = tempX - moveX;
			int deltaY = tempY - moveY;
			Log.e(TAG,"onTouchEvent-ACTION_MOVE moveX="+moveX+
					",moveY="+moveY+",deltaX="+deltaX+",deltaY="+deltaY);
			tempX = moveX;
			tempY = moveY;

			/**
			 * 向右滑
			 */
			if (moveX - downX > mTouchSlop
					&& Math.abs( moveY - downY) < mTouchSlop) {
				isSildingX = true;
				Log.e(TAG,"onTouchEvent-ACTION_MOVE isSilding = true");
			}

			/**
			 * 向下滑
			 */
			if (moveX - downX < mTouchSlop
					&& Math.abs( moveY - downY) > mTouchSlop) {
				isSildingY = true;
				Log.e(TAG,"onTouchEvent-ACTION_MOVE isSildingY = true");
			}

			//右滑 && isSilding=true
			if (moveX - downX >= 0 && isSildingX) {
				mContentView.scrollBy(deltaX, 0);//横坐标移动deltaX距离
			}

			//下滑 && isSildingY=true
			Log.e(TAG,"onTouchEvent-ACTION_MOVE moveY - downY="+(moveY - downY));
			if (moveY - downY >= 0 && isSildingY) {
				mContentView.scrollBy(0, deltaY);//横坐标移动deltaY距离
			}

			break;
		case MotionEvent.ACTION_UP:
			if(isSildingX){
				isSildingX = false;

				/**
				 * 当前view的左上角相对于母视图的左上角的X轴偏移量（这里是负数） <= -viewWidth / 5
				 * true:滑动超过一半  false:滑动未超过一半
				 */
				if (mContentView.getScrollX() <= -viewWidth / 5) {
					Log.e(TAG,"onTouchEvent-ACTION_UP isFinish = true  mContentView.getScrollX()="+mContentView.getScrollX());
					isFinish = true;
					scrollRight();
				} else {
					Log.e(TAG,"onTouchEvent-ACTION_UP isFinish = false mContentView.getScrollX()="+mContentView.getScrollX());
					scrollOrigin();
					isFinish = false;
				}
			}

			if(isSildingY){
				isSildingY = false;
				/**
				 * 当前view的左上角相对于母视图的左上角的X轴偏移量（这里是负数） <= -viewWidth / 5
				 * true:滑动超过一半  false:滑动未超过一半
				 */
				if (mContentView.getScrollY() <= -viewHeight / 5) {
					Log.e(TAG,"onTouchEvent-ACTION_UP isFinishY = true mContentView.getScrollY()="+mContentView.getScrollY());
					isFinish = true;
					scrollDown();
				} else {
					Log.e(TAG,"onTouchEvent-ACTION_UP isFinishY = false mContentView.getScrollY()="+mContentView.getScrollY());
					scrollOriginY();
					isFinish = false;
				}
			}

			break;
		}

		return true;
	}

	/**
	 * 获取SwipeBackLayout里面的ViewPager的集合
	 * 
	 * @param mViewPagers
	 * @param parent
	 */
	private void getAlLViewPager(List<ViewPager> mViewPagers, ViewGroup parent) {
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {// 在ViewGroup中查找是否存在viewPager
			View child = parent.getChildAt(i);
			if (child instanceof ViewPager) {
				mViewPagers.add((ViewPager) child);
			} else if (child instanceof ViewGroup) {
				getAlLViewPager(mViewPagers, (ViewGroup) child);
			} else if (child instanceof ListView){
				mListView = (ListView) child;
			}
		}
	}

	/**
	 * 返回我们touch的ViewPager
	 * 
	 * @param mViewPagers
	 * @param ev
	 * @return
	 */
	private ViewPager getTouchViewPager(List<ViewPager> mViewPagers,
                                        MotionEvent ev) {
		if (mViewPagers == null || mViewPagers.size() == 0) {
			return null;
		}
		Rect mRect = new Rect();
		for (ViewPager v : mViewPagers) {
			v.getHitRect(mRect);//ViewPager的矩形
			//矩形内
			if (mRect.contains((int) ev.getX(), (int) ev.getY())) {
				return v;
			}
		}
		return null;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			viewWidth = this.getWidth();
			viewHeight = this.getHeight();

			getAlLViewPager(mViewPagers, this);
			Log.i(TAG, "ViewPager size = " + mViewPagers.size());
		}
	}

	/**
	 * 这个方法主要用于控制子View的绘制分发，重载该方法可改变子View的绘制，进而实现一些复杂的视效，
	 * @param canvas
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mShadowDrawable != null && mContentView != null) {

			int left = mContentView.getLeft()
					- mShadowDrawable.getIntrinsicWidth();
			int right = left + mShadowDrawable.getIntrinsicWidth();
			int top = mContentView.getTop();
			int bottom = mContentView.getBottom();

			mShadowDrawable.setBounds(left, top, right, bottom);
			mShadowDrawable.draw(canvas);
		}

	}

	/**
	 * 侧滑滚动出界面
	 */
	private void scrollRight() {
		final int delta = (viewWidth + mContentView.getScrollX());//屏幕宽+当前view的左上角相对于母视图的左上角的X轴偏移量（在这里是负数）
		Log.e(TAG,"scrollRight viewWidth="+viewWidth+
				",mContentView.getScrollX()="+mContentView.getScrollX()+",delta="+delta);
		// 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
		mScroller.startScroll(mContentView.getScrollX(), 0, -delta + 1, 0,
				Math.abs(delta));
		postInvalidate();
	}

	/**
	 * 滚动到起始位置
	 */
	private void scrollOrigin() {
		int delta = mContentView.getScrollX();//这是负数
		Log.e(TAG,"scrollOrigin delta="+delta);
		//第三个参数  正数：向左滑动  负数：向右滑动
		mScroller.startScroll(mContentView.getScrollX(), 0, -delta, 0,
				Math.abs(delta));
		postInvalidate();// TODO 刷新界面，但为啥要用在工作者线程的postInvalidate，而不是invalidate()？？？
	}


	/**
	 * 下滑滚动出界面
	 */
	private void scrollDown() {
		final int deltaY = (viewHeight + mContentView.getScrollY());//屏幕宽+当前view的左上角相对于母视图的左上角的Y轴偏移量（在这里是负数）
		Log.e(TAG,"scrollDown viewHeight="+viewHeight+
				",mContentView.getScrollY()="+mContentView.getScrollY()+",deltaY="+deltaY);
		// 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
		mScroller.startScroll(0, mContentView.getScrollY(), 0, -deltaY + 1,
				Math.abs(deltaY));
		postInvalidate();
	}

	/**
	 * 滚动到起始位置 Y
	 */
	private void scrollOriginY() {
		int deltaY = mContentView.getScrollY();//这是负数
		Log.e(TAG,"scrollOriginY deltaY="+deltaY);
		//第三个参数  正数：向左滑动  负数：向右滑动
		mScroller.startScroll(0, mContentView.getScrollY(), 0, -deltaY,
				Math.abs(deltaY));
		postInvalidate();// TODO 刷新界面，但为啥要用在工作者线程的postInvalidate，而不是invalidate()？？？
	}

	@Override
	public void computeScroll() {
		// 调用startScroll的时候scroller.computeScrollOffset()返回true，
		if (mScroller.computeScrollOffset()) {// 判断是否滑动结束
			mContentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();

			if (mScroller.isFinished() && isFinish) {
				mActivity.finish();
			}
		}
	}

}
