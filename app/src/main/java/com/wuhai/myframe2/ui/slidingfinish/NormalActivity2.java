package com.wuhai.myframe2.ui.slidingfinish;

import android.os.Bundle;

import com.wuhai.myframe2.R;


public class NormalActivity2 extends SlideRightOrDownActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_slide_normal2);
	}

	@Override
	public boolean canSlidingFinish() {
		return true;
	}
}
