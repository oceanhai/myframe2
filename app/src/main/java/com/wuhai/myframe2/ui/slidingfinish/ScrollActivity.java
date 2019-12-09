package com.wuhai.myframe2.ui.slidingfinish;

import android.os.Bundle;

import com.wuhai.myframe2.R;


public class ScrollActivity extends SwipeBackActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_slide_scroll);
	}

	@Override
	public boolean canSlidingFinish() {
		return true;
	}
}
