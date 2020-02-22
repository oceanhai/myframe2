package com.wuhai.myframe2.ui.eventtransmit;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;


public class MyLayout extends RelativeLayout {

	// public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
	// super(context, attrs, defStyleAttr);
	// }

	// xml 无样式
	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// 代码
	public MyLayout(Context context) {
		super(context);
	}

	// 分法事件
	// 不会重写
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.v(EventTransmitActivity.TAG, " MyLayout==dispatchTouchEvent==" + ev.getAction());
		return super.dispatchTouchEvent(ev);
//		return true;
	}

	// 拦截事件
	// false 不拦截 事件会继续往下传递
	// true 拦截 onTounch
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.v(EventTransmitActivity.TAG, " MyLayout==onInterceptTouchEvent==" + ev.getAction());
//		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//			return true;
//			// action_cancel 通知内蹭的组件，不用处理事件了
//		}

		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.v(EventTransmitActivity.TAG, " MyLayout==onTouchEvent==" + event.getAction());
//		return false;
		return true;
	}

}
