package com.wuhai.myframe2.widget.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.wuhai.myframe2.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日历显示activity
 * 
 * @author Vincent Lee
 * 
 */
public class CalendarActivity extends Activity implements View.OnClickListener {

	private GestureDetector gestureDetector = null;
	private CalendarAdapter calV = null;
	private ViewFlipper flipper = null;
	private GridView gridView = null;
	private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
	private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private String currentDate = "";
	/** 每次添加gridview到viewflipper中时给的标记 */
	private int gvFlag = 0;
	/** 当前的年月，现在日历顶端 */
	private TextView currentMonth;
	/** 上个月 */
	private ImageView prevMonth;
	/** 下个月 */
	private ImageView nextMonth;

	public CalendarActivity() {

		Date date = new Date();
		/**
		 * M和d只有一位，表明至少占一位，这样个位的时候前面就不会存在0X情况
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		currentDate = sdf.format(date); // 当期日期
		year_c = Integer.parseInt(currentDate.split("-")[0]);
		month_c = Integer.parseInt(currentDate.split("-")[1]);
		day_c = Integer.parseInt(currentDate.split("-")[2]);

	}

	public static void startActivity(Context context){
		Intent intent = new Intent(context, CalendarActivity.class);
		context.startActivity(intent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		currentMonth = (TextView) findViewById(R.id.currentMonth);
		prevMonth = (ImageView) findViewById(R.id.prevMonth);
		nextMonth = (ImageView) findViewById(R.id.nextMonth);
		setListener();

		/**
		 * 手势识别类
		 */
		gestureDetector = new GestureDetector(this, new MyGestureListener());
//		gestureDetector = new GestureDetector(this, new MyGestureListener2());//效果感觉并不好
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		flipper.removeAllViews();
		calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
		addGridView();
		gridView.setAdapter(calV);
		flipper.addView(gridView, 0);
		addTextToTopTextView(currentMonth);
	}

	private class MyGestureListener extends SimpleOnGestureListener {
		/**
		 * Touch了滑动一点距离后，up时触发
		 * @param e1
		 * @param e2
		 * @param velocityX
		 * @param velocityY
		 * @return
		 */
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
			if (e1.getX() - e2.getX() > 120) {
				// 像左滑动,下一个月
				enterNextMonth(gvFlag);
				return true;
			} else if (e1.getX() - e2.getX() < -120) {
				// 向右滑动,上一个月
				enterPrevMonth(gvFlag);
				return true;
			}
			return false;
		}
	}


	private class MyGestureListener2 implements GestureDetector.OnGestureListener {

		public boolean onDoubleTap(MotionEvent e) {
			if(flipper.isFlipping()) {
				flipper.stopFlipping();
			}else {
				flipper.startFlipping();
			}
			return true;
		}


		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {

			//用户按下屏幕，快速移动后松开（就是在屏幕上滑动）
			//e1:第一个ACTION_DOWN事件（手指按下的那一点）
			//e2:最后一个ACTION_MOVE事件 （手指松开的那一点）
			//velocityX:手指在x轴移动的速度 单位：像素/秒
			//velocityY:手指在y轴移动的速度 单位：像素/秒

			if (e1.getX() - e2.getX() > 60) {
				flipper.setInAnimation(AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.push_left_in));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.push_left_out));
				flipper.showNext();
				return true;
			} else if (e1.getX() - e2.getX() < -60) {
				flipper.setInAnimation(AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.push_right_in));
				flipper.setOutAnimation(AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.push_right_out));
				flipper.showPrevious();
				return true;
			}
			return false;
		}


		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
								float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	/**
	 * 移动到下一个月
	 * 
	 * @param gvFlag
	 */
	private void enterNextMonth(int gvFlag) {
		addGridView(); // 添加一个gridView
		jumpMonth++; // 下一个月

		calV = new CalendarAdapter(this, this.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
		gridView.setAdapter(calV);
		addTextToTopTextView(currentMonth); // 移动到下一月后，将当月显示在头标题中
		gvFlag++;
		flipper.addView(gridView, gvFlag);
		flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
		flipper.showNext();
		flipper.removeViewAt(0);
	}

	/**
	 * 移动到上一个月
	 * 
	 * @param gvFlag
	 */
	private void enterPrevMonth(int gvFlag) {
		addGridView(); // 添加一个gridView
		jumpMonth--; // 上一个月

		calV = new CalendarAdapter(this, this.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
		gridView.setAdapter(calV);
		gvFlag++;
		addTextToTopTextView(currentMonth); // 移动到上一月后，将当月显示在头标题中
		flipper.addView(gridView, gvFlag);

		flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
		flipper.showPrevious();
		flipper.removeViewAt(0);
	}

	/**
	 * 添加头部的年份 闰哪月等信息
	 * 
	 * @param view
	 */
	public void addTextToTopTextView(TextView view) {
		StringBuffer textDate = new StringBuffer();
		// draw = getResources().getDrawable(R.drawable.top_day);
		// view.setBackgroundDrawable(draw);
		textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append("\t");
		view.setText(textDate);
	}

	private void addGridView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 取得屏幕的宽度和高度
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int Width = display.getWidth();
		int Height = display.getHeight();

		gridView = new GridView(this);
		gridView.setNumColumns(7);
		gridView.setColumnWidth(40);
		// gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		if (Width == 720 && Height == 1280) {
			gridView.setColumnWidth(40);
		}
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// 去除gridView边框
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setOnTouchListener(new OnTouchListener() {
			// 将gridview中的触摸事件回传给gestureDetector
			public boolean onTouch(View v, MotionEvent event) {
				return CalendarActivity.this.gestureDetector.onTouchEvent(event);
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
				int startPosition = calV.getStartPositon();
				int endPosition = calV.getEndPosition();
				if (startPosition <= position + 7 && position <= endPosition - 7) {
					String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
					// String scheduleLunarDay =
					// calV.getDateByClickItem(position).split("\\.")[1];
					// //这一天的阴历
					String scheduleYear = calV.getShowYear();
					String scheduleMonth = calV.getShowMonth();
					Toast.makeText(CalendarActivity.this, scheduleYear + "-" + scheduleMonth + "-" + scheduleDay, Toast.LENGTH_SHORT).show();
					// Toast.makeText(CalendarActivity.this, "点击了该条目",
					// Toast.LENGTH_SHORT).show();
				}
			}
		});
		gridView.setLayoutParams(params);
	}

	private void setListener() {
		prevMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.nextMonth: // 下一个月
			enterNextMonth(gvFlag);
			break;
		case R.id.prevMonth: // 上一个月
			enterPrevMonth(gvFlag);
			break;
		default:
			break;
		}
	}

}