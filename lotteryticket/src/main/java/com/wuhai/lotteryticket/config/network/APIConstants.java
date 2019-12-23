package com.wuhai.lotteryticket.config.network;


import com.wuhai.lotteryticket.BuildConfig;

public class APIConstants {

	//test 开发环境
	public static String TEST_Url = "http://apis.juhe.cn/";
	//正式环境
	public static String RELEASE_Url = "http://apis.juhe.cn/";

	//网络请求
	public static String NetWorkBaseURL = "";

	//代码内 作为一种开关控制
	public static boolean DEBUG = true;
	static {
		if(BuildConfig.DEBUG){
			NetWorkBaseURL = TEST_Url;
			DEBUG = true;
		}else {
			NetWorkBaseURL = RELEASE_Url;
			DEBUG = false;
		}
	}

}
