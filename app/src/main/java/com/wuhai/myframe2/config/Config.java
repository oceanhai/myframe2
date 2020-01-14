package com.wuhai.myframe2.config;

import java.io.File;

public class Config {

    public static final String APP_BASE_SERVER_API_URL = "http://api.qiandaodao.com";
    public static final String APP_BASE_SERVER_API_URL_OFFLINE = "http://192.168.10.100";

    public static final String APP_BASE_DIR_NAME = "myframe2";
    public static final String DATABASE_NAME = "qiandaodao.db";

    public static final String DOWNLOAD_APK_PUBLIC_DIR = "Download";
    public static final String DOWNLOAD_APK_FILE_NAME = "qiandaodaoNew.apk";

    // Image cache name
    public static final String IMAGE_CACHE_DIR_NAME = APP_BASE_DIR_NAME + File.separator + "cache";
    public static final String TMP_FILE_DIR_NAME = APP_BASE_DIR_NAME + File.separator + "tmpfile";

    // Loan agreement url.
    public static final String LOAN_AGREEMENT_EMPTY_DETAIL_URL = "http://static.qiandaodao.cn/help/agreement/loan.html";

    // Loan agreement url(money to account).
    public static final String LOAN_AGREEMENT_DETAIL_URL = APP_BASE_SERVER_API_URL + "/user/credit/loan/agreement.do";

    // Register agreement url
//    public static final String REGISTER_AGREEMENT_DETAIL_URL = "http://static.qiandaodao.cn/help/agreement/register.html";
    public static final String REGISTER_AGREEMENT_DETAIL_URL = "http://static.qiandaodao.cn/help/agreement/register_qd.html";

    // Server Time format
    public static final String SERVER_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    // Server photo min length
    public static final int SERVER_PHOTO_MIN_LENGTH = 960;

    public static final String ALIAS_TYPE = "qiandaodao";

    // Web view UserAgent.
    public static final String WEB_VIEW_USER_AGENT = " app/qiandaodao/android";

    //xiaomi push id and app key
    public static final String MI_PUSH_APP_ID = "2882303761517449375";
    public static final String MI_PUSH_APP_KEY = "5161744998375";
    //umeng push id and app key
    public static final String UMENG_PUSH_APP_ID = "563d61b6e0f55a5d62003fd3";
    public static final String UMENG_PUSH_APP_KEY = "99aece2f5b7e77c7be5227ed9c5f91ae";


}
