package com.wuhai.myframe2.ui.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService3 extends Service {

	public static final String TAG = "MyService3";

	private Thread t;

	private IntentFilter intentFilter;
	private MyReceiver myReceiver;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private boolean flag = true;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "onCreate");

		// 创建 IntentFilter 实例
		intentFilter = new IntentFilter();
		// 添加广播值
		intentFilter.addAction("android.intent.action.USER_PRESENT");
		// 创建 NetworkChangeReceiver 实例
		myReceiver = new MyReceiver();
		// 注册广播
		registerReceiver(myReceiver,intentFilter);


		t = new Thread(new Runnable() {

			@Override
			public void run() {
					while (flag) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Log.e(TAG, "常驻服务，服务里启动一个广播监听");
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

		// 取消注册
		unregisterReceiver(myReceiver);
	}

}
