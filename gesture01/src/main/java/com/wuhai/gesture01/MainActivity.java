package com.wuhai.gesture01;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final GestureDetector detector;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
			detector = new GestureDetector(MainActivity.this,
					new GestureDetector.OnGestureListener() {

						// 单击 放开的事件
						@Override
						public boolean onSingleTapUp(MotionEvent e) {

							System.out.println("====onSingleTapUp==="
									+ e.getAction());

							return false;
						}

						// 展示 按下的效果
						@Override
						public void onShowPress(MotionEvent e) {
							System.out.println("====onShowPress===" + e.getAction());

						}

						// 手指滑动事件 e1首次按下的事件 e2滑动时的事件 distanceX x、y轴方向的距离
						@Override
						public boolean onScroll(MotionEvent e1, MotionEvent e2,
								float distanceX, float distanceY) {
							System.out.println("====onScroll===" + e1.getX() + ":"
									+ e2.getX() + ":" + distanceX);
							return false;
						}

						// 长按事件
						@Override
						public void onLongPress(MotionEvent e) {
							System.out.println("====onLongPress===" + e.getAction());
						}

						// 快速滑动的时候，执行的操作
						// e1 e2 与onScroll最后一次调用的 e1,e2 一样
						// velocityX velocityY x,y轴的速度
						@Override
						public boolean onFling(MotionEvent e1, MotionEvent e2,
								float velocityX, float velocityY) {
							System.out.println("====onFling===" + e1.getX() + ":"
									+ e2.getX() + ":" + velocityX);
							return false;
						}

						// 按下时候的操作
						@Override
						public boolean onDown(MotionEvent e) {
							System.out.println("====onDown===" + e.getAction());
							return false;
						}
					});

			detector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {

				//点击事件执行
				@Override
				public boolean onSingleTapConfirmed(MotionEvent e) {
					System.out.println("=======onSingleTapConfirmed=="+e.getAction());
					return false;
				}
				//双击事件  第二次  按下  放开
				@Override
				public boolean onDoubleTapEvent(MotionEvent e) {
					System.out.println("=======onDoubleTapEvent=="+e.getAction());
					return false;
				}

				//第二次 按下事件
				@Override
				public boolean onDoubleTap(MotionEvent e) {
					System.out.println("=======onDoubleTap=="+e.getAction());
					return false;
				}
			});

			findViewById(R.id.button1).setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					// 把事件传递给 测探器
					detector.onTouchEvent(event);

					// 处理完成事件
					return true;
				}
			});
		}





	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
