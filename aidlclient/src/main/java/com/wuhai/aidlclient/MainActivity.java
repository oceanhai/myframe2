package com.wuhai.aidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.wuhai.aidlserver.RemoteInterface;

public class MainActivity extends Activity {

	private boolean isBind = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * 绑定服务 view onclick
	 * @param v
	 */
	public void bindAidlServer(View v){
		Intent intent=new Intent("com.wuhai.aidlserver.remote_service");//服务端中定义的action
		intent.setPackage("com.wuhai.aidlserver");//※必需 服务端app包名
		isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
		Log.e("aidlclient", "bindAidlServer isBind=" + isBind);
    }

	/**
	 * 调起 adil里的接口方法	view onclick
	 * @param v
	 */
	public void invokeAidlServer(View v){
		try {
			String result = "";
			if(dataService != null){
				result = dataService.callRemote();
			}
			Log.e("aidlclient", "result:" + result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


	private RemoteInterface dataService;
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.e("aidlclient", "ServiceConnection onServiceDisconnected:" + name.toString());
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			dataService = RemoteInterface.Stub.asInterface(service);
			Log.e("aidlclient", "ServiceConnection onServiceConnected:" + name.toString());
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(isBind){
			Log.e("aidlclient", "onDestroy unbindService");
			unbindService(conn);
			isBind = false;
		}
	}
}
