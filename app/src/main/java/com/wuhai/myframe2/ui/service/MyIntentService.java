package com.wuhai.myframe2.ui.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 有道笔记
 * 面试/腾讯课堂/享学课堂/面试题 拓展  1
 */
public class MyIntentService extends IntentService {

	public static final String TAG = "MyIntentService";
	private String ex = "";

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.e(TAG, "ex: " + ex);
		}
	};

	public MyIntentService(){
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		//在这里通过intent携带的数据，开进行任务的操作。
		Log.e(TAG, "onHandleIntent: " + Thread.currentThread().getName());
		int num = 0;
		while (num<5){
			num ++;
			Log.e(TAG, "onHandleIntent: " + num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mHandler.sendEmptyMessage(0);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "onStartCommand intent="+intent+",flags="+flags+",startId="+startId);
		ex = intent.getStringExtra("start");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

}
