package com.wuhai.myframe2.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	public static final String TAG = "MyReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e(TAG, "info " + intent.toString());
		//eg:2020-04-29 18:40:19.613 29381-29381/com.wuhai.myframe2 E/MyReceiver: info Intent { act=android.intent.action.USER_PRESENT flg=0x24000010 }
	}

}
