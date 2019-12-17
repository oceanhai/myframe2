package com.wuhai.myframe2.ui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReveiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String result = intent.getStringExtra("data");
		if (result != null) {
			Log.e(SendBroadcastActivity.TAG, "自己的广播接收者："+result);
//			System.out.println(result);
		}
		// 取消广播
		// abortBroadcast();

	}

}
