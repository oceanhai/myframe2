package com.wuhai.myframe2.ui.eventtransmit;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatTextView;


public class MyView extends AppCompatTextView {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context) {
		super(context);
	}

	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.v(EventTransmitActivity.TAG, " MyView==dispatchTouchEvent==" + event.getAction());
		return super.dispatchTouchEvent(event);
	}


	//处理
	//false  没有处理
	//true  处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.v(EventTransmitActivity.TAG, " MyView==onTouchEvent==" + event.getAction());
//		return true;
		return false;
//		return super.onTouchEvent(event);
	}

}
