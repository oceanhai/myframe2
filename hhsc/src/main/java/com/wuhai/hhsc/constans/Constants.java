package com.wuhai.hhsc.constans;

import java.io.File;

/**
 * Description:
 * Data: 2019-11-26
 *
 * @author: zhudi
 */
public interface Constants {
    class Key {
        // tab
        public static final String KEY_TAB_INIT = "key_tab_init";
        /**
         * bugly 组件的 AppId
         * <p>
         * bugly sdk 系腾讯提供用于 APP Crash 收集和分析的组件。
         */
        //正式
        private static final String KEY_BUGLY_APPID_OFFICIAL = "3f389abbb2";
        //测试
        private static final String KEY_BUGLY_APPID_DEBUG = "63f47486bb";

        /**
         * bugly 组件的 AppId
         *
         * @return
         */
//        public static String getKeyBublyAppId() {
//            if (BuildConfig.LOG_DEBUG) {
//                return KEY_BUGLY_APPID_DEBUG;
//            } else {
//                return KEY_BUGLY_APPID_OFFICIAL;
//            }
//        }

        /**
         * 接口请求使用
         */
        public static final String KEY_SECRETKEY = "6760a3926c464decb0cdc281ec01f8cc";

        /**
         * 微信支付key
         */
        public static final String KEY_WXPAY = "wx1858e1e803a5f58a";
        //微信 AppSecret
        public final static String WeixinAppSecret = "e7323595fa8cf8dc78e36c203b9ba189";
    }

    class Action {
        public static final String ACTION_BROWSE = "io.dcloud.hhsc.action.browse";
    }

    class Fields {
        public static final String USERID = "userId";
        public static final String USERSIG = "userSig";
        public static final String USERNAME = "userName";
        public static final String USERICON = "userIcon";
        public static final String PASSWORD = "passWord";
        public static final String NICKNAME = "nickName";
        public static final String FROM = "from";
        public static final String JUMPDETAILS = "jumpDetails";
        public static final String JUMPDHOME = "jumpHome";
        public static final String LIVEID = "liveId";
        public static final String PAGE = "page";
        public static final String SIZE = "size";
        public static final String SIGN = "sign";
        public static final String TIMESTAMP = "timestamp";
        public static final String TOKENID = "tokenId";
        public static final String VERSIONCODE = "versionCode";
        public static final String APPKEY = "appkey";
        //登陆后用户必要信息 vipNumber（个推绑别名用到）
        public static final String VIP_NUMBER = "vipNumber";
        public static final String NONCE = "nonce";
        public static final String TRANSID = "transId";
        public static final String SECRETKEY = "secretKey";
        public static final String Q = "q";
        public static final String PAGENUM = "pageNum";
        public static final String PAGESIZE = "pageSize";
        public static final String SPUIDS = "spuIds";
        public static final String PLAYURL = "playUrl";
        public static final String PUSHURL = "pushUrl";
        public static final String ROOMID = "roomId";
        public static final String GOODSID = "goodsId";
        public static final String GOODSTYPE = "goodsType";
        public static final String ISANCHOR = "isAnchor";
        public static final String PUSHER_AVATAR = "pusherAvatar";
        public static final String MEMBERCOUNT = "memberCount";
        public static final String PUSHER_NAME = "pusherName";
        public static final String VIDEOQUALITY = "videoQuality";
        public static final String VIDEOBITRATE = "videoBitrate";
        public static final String URL = "url";
        public static final String OLDURL = "oldUrl";
        public static final String FLAG = "flag";
        public static final String TYPE = "type";
        public static final String LOCATION = "location";
        public static final String SHARE_MESSAGE_ID = "shareMessageId";
        public static final String KM = "km";
        public static final String LAT = "lat";
        public static final String LATLNG = "latLng";
        public static final String ADDRESS = "address";
        public static final String LON = "lon";
        //个推的clientid
        public static final String CLIENT_ID = "clientid";
    }

    class Tag {
        public static final String TAG_XUPDATE = "XUpdate";
        public static final String TAG_AUTOSIZE = "AutoSize";
        public static final String TAG_GLIDE = "Glide";
    }

    class Path {

        public static final String APP_BASE_DIR_NAME = "hhsc";
        public static final String TMP_FILE_DIR_NAME = APP_BASE_DIR_NAME + File.separator + "tmpfile";
    }
}
