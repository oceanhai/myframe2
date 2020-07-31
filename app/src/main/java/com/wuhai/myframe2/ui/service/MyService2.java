package com.wuhai.myframe2.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * startService  内部调用stopSelf 自己结束
 */
public class MyService2 extends Service {

	public static final String TAG = "MyService2";

	private Thread t;
	private int num = 0;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "onCreate");
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (num<10) {
					num++;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Log.e(TAG, "我是服务，你需要服务么 num = " + num);
				}

				stopSelf();
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
	}

}
