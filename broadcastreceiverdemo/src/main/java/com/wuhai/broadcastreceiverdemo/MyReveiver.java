package com.wuhai.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReveiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String result = intent.getStringExtra("data");
		if (result != null) {
			Log.e(MainActivity.TAG, result);
//			System.out.println(result);
		}
		// 取消广播
		// abortBroadcast();

		System.out.println("我是demo2，我也接到广播了，麻辣隔壁");
	}

}
