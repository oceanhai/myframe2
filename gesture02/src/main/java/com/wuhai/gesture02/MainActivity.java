package com.wuhai.gesture02;


import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 手势识别库
		final GestureLibrary library = GestureLibraries.fromRawResource(
				MainActivity.this, R.raw.gestures);
		library.load();

		
		//go桌面    es
		
		// 识别手势
		GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		gestureView
				.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							Gesture gesture) {
						// 得到识别出来的手势
						// 越像的手势，越在前面，匹配度越高的，越在前面
						ArrayList<Prediction> recognize = library
								.recognize(gesture);
						if (recognize.size() > 0) {
							Prediction prediction = recognize.get(0);

							if (prediction.score > 1) {

								if (prediction.name.equals("aa")) {
									Toast.makeText(MainActivity.this, "三角形",
											Toast.LENGTH_SHORT).show();
								} else if (prediction.name.equals("bb")) {
									Toast.makeText(MainActivity.this, "圆圈形",
											Toast.LENGTH_SHORT).show();
								}

							} else {
								Toast.makeText(MainActivity.this, "重试一下",
										Toast.LENGTH_SHORT).show();
							}

						}

					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
