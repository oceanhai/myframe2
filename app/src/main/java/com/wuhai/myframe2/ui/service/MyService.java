package com.wuhai.myframe2.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	public static final String TAG = "MyService";

	private Thread t;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private boolean flag = true;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "onCreate");
		t = new Thread(new Runnable() {

			@Override
			public void run() {
					while (flag) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Log.e(TAG, "我是服务，你需要服务么");
				}
			}
		});
		t.start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
		flag = false;
	}

}
