package com.wuhai.myframe2.ui.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private Paint paint;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
	}

	// 系统进行'画'事件
	@Override
	protected void onDraw(Canvas canvas) {
		// 先让系统画出初始的部分
		super.onDraw(canvas);
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(10);
		canvas.drawColor(Color.GRAY);
		// 锁定画布
		canvas.save();
		canvas.drawLine(10, 10, 100, 100, paint);
		canvas.drawLine(50, 20, 200, 200, paint);
		// 解锁画布
		canvas.restore();

	}

}
