package com.wuhai.myframe2.ui.webview1.constant;

import java.io.File;

public class Constant {

    //TODO =============所有用户相关 写在这里===================
    public static final String TOKEN = "token";
    public final static String SALT = "salt";
    public final static String UUID = "uuid";
    public final static String MEDIATYPE = "application/json;charset=utf-8";
    //用户唯一ID，用于刷脸登录
    public static final String USER_ID = "userId";
    //用户证件号码信息
    public static final String CARDID = "cardId";
    //用户手机号
    public static final String PHONE_NUM = "phoneNum";
    //用户登录次数
    public static final String ACCESS_NUM = "accessNum";
    //用户资格认证累计失败次数（成功后清零）
    public static final String QUALIFICATION_FAILED_NUM = "qualificationFailedNumber";
    //用户最近一次登录失败Unix时间戳
    public static final String LOGIN_TIME = "timeStamp";
    //用户类型，0为普通用户，1为实名制用户，子功能控制权限需要判断
    public static final String USER_TYPE = "userType";
    //用户是否同意隐私协议，1为同意,  null为尚未同意
    public static final String PRIVATE_PROTOCOL = "privateProtocol";
    //更新apk 不再提醒 时间戳
    public static final String UPDATE_TIMESTAMPS = "updateTimestamps";

    //TODO 事项
    //工伤保险登记
    public static final String ITEM_INSURANCE = "insurance";
    //参保证明验真
    public static final String ITEM_CERTIFY = "certify";
    //首页-扫描-all
    public static final String ITEM_ALL = "all";

    //h5-扫描-二维码
    public static final String ITEM_QR = "qr";
    //h5-扫描-条形码
    public static final String ITEM_BAR = "bar";
    //h5-扫描-二维码或条形码
    public static final String ITEM_QR_BAR = "qr_bar";

    //TODO 微信分享
    //微信好友
    public static final String WECHAT_FRIEND = "wechatFriend";
    //朋友圈
    public static final String WECHAT_CIRCLE = "wechatCircle";
    //微信好友和朋友圈
    public static final String WECHAT_BOTH = "wechatBoth";
    //拍照
    public static final String SELECT_FROM_TAKE_PHOTO = "takePhoto";
    //图库
    public static final String SELECT_FROM_GALLERY = "gallery";
    //授权使用身份证照片
    public static final String SELECT_FROM_AUTHORIZATION = "authorization";
    //图片保存的名称
    public static final String PICTURE_PATH = "idCardPhoto.png";
    //TODO =============所有用户相关 写在这里===================


    //TODO =============所有界面之间传值KEY 写在这里===================
    public static String SCANNERCODE = "scannerType";

    public static String H5_URL = "h5Url";
    public static final String H5_FLAG = "h5Flag";
    public static final String H5_TITLE = "h5Title";
    //是否加载的loading进度条
    public static final String H5_IS_SHOW_LOADING_PROGRESS = "isShowLoadingProgress";
    //是否显示topBar
    public static final String H5_IS_SHOW_TOPBAR = "isShowTopBar";
    //url params参数 是否显示topBar
    public static final String H5_HAS_NAV = "hasNav";
    //跳转登录 从哪跳转过去的 标识
    public static final String LOGIN_FROM = "loginFrom";
    //跳转实名认证 从哪跳转过去的 标识
    public static final String AUTH_FROM = "authFrom";
    //跳转全部服务 从哪跳转过去的 标识
    public static final String SERVICE_FROM = "serviceFrom";
    //ocr认证区别标识（从哪跳转去的）
    public static final String AUTH_TYPE = "authType";
    //提示语
    public static final String AUTH_TIP = "authTip";
    //标题
    public static final String AUTH_TITLE = "authTitle";
    //是否显示手动输入
    public static final String AUTH_IS_SHOW_FILL_IN = "authIsShowFillIn";
    //参保名称、id、时间
    public static final String AUTH_PROJECT_NAME = "projectName";
    public static final String AUTH_PROJECT_ID = "projectId";
    public static final String AUTH_PROJECT_TIME = "projectTime";
    //用户信息
    public static final String AUTH_USER_INFO = "userInfo";
    //是否为本人参保
    public static final String AUTH_IS_MINE = "isMine";
    //申请类型
    public static final String APPLY_TYPE = "applyType";
    //e钱包 安全密码跳转
    public static final String EWALLET_SAFE_PWD_FROM = "ewallet_jump_from";
    //TODO =============所有界面之间传值KEY  写在这里===================


    //TODO =============所有环境和日志相关 写在这里===================
    //APP 名称
    public static final String APP_NAME = "BF-XASI";
    //APP 运行环境
    public static final String ENVIRONMENT = "environment";
    //切环境标识
    public static boolean canChange = false;
    //网络拦截 log标识
    public static final String LOG_TAG = "zsn";
    //TODO =============所有所有环境和日志相关 写在这里===================

    //TODO =============所有路径相关 写在这里===================
    //保存图片文件夹
    public static final String FILE_PATH = File.separator + "bfxasi";
    public static final String SHAREPIC_NAME = File.separator + "bfxasi.png";
    public static final String AUTHORITY = "com.bfxasi.fileprovider";

    //跳转资讯web 地址
    public static final String JUMP_WEB_PATH_NEWS_INFO = "/newsInfoActivity";
    //TODO =============所有路径相关 写在这里===================


    //TODO =============所有code码 写在这里===================

    public static final int DIALOG_SURE = 0x001;
    public static final int DIALOG_CANCEL = 0x002;
    public static final int REFRESH_REQUESTCODE = 0x111;
    public static final int REQUEST_FINISH = 0x500;
    public static final int RESULT_FINISH = 0x400;
    public static final int REQUEST_RNBACK = 0x300;
    public static final int REQUEST_GOLOGIN = 0x200;
    //设置权限后，重新安装apk
    public static final int REQUEST_SOURCE_INSTALL = 0x1101;
    //下载附件 code
    public static final int DOWNLOAD_FILE_HANDLE_MESSAGE = 0x1102;
    //刷脸页面回调code
    public static final int REQUEST_BAS_FACE = 0x1103;
    //为本人减员刷脸code
    public static final int REDUCE_FOR_ME_RESULT_CODE = 0x1104;
    //为他人减员刷脸code
    public static final int REDUCE_FOR_OTHER_RESULT_CODE = 0x1105;
    //唤起刷脸code
    public static final int WEBVIEW_CALL_NATIVE_FACE_CODE = 0x1106;
    //唤起刷脸code  社保卡申领-授权使用身份证照片 用
    public static final int WEBVIEW_CALL_NATIVE_FACE_CODE_AUTHORIZATION = 0x1107;

    //从图库选择照片 code
    public static final int REQUEST_IMAGE_SELECT = 0x1002;
    //裁剪照片 code
    public static final int REQUEST_CROP_IMAGE = 0x1003;
    //普通拍照 code
    public static final int REQUEST_ONLY_TAKE_PHOTO = 0x1004;

    //------------------------------证件照-------------------------------
    //证件照 头像相机 code
    public static final int REQUEST_TAKE_HEAD_PICTURE = 0x1005;
    //证件照 头像 选择相册 code
    public static final int REQUEST_OPEN_GALLERY_CERTIFICATION = 0x1006;
    //------------------------------证件照-------------------------------

    //------------------------------电子钱包-------------------------------
    public static final int REQUEST_E_WALLET_TO_BIND_CARD = 0x1007;
    //去绑卡
    public static final int REQUEST_E_WALLET_GO_BIND_CARD_REQUEST_CODE = 0x1008;
    //------------------------------电子钱包-------------------------------

    //友盟微信分享找不到微信客户端错误码
    public static final String WECHAT_UNINSTALLED_CODE = "2008";
    //TODO =============所有code码 写在这里===================


    //TODO =============所有全局状态布尔值 写在这里===================
    //侧滑页onResume刷新状态
    public static boolean NEED_REFRESH_MINE = false;
    //我的页面onResume刷新状态
    public static boolean NEED_REFRESH_MINE_FRAGMENT = false;
    public static boolean COLLECTION_CHANGED = true;
    /**
     * 工伤事故快报是否开启    false：办事指南    true：开启事项
     **/
    public static boolean SWITCH_ACCIDENT = true;
    //TODO =============所有全局状态布尔值 写在这里===================


    //TODO =============所有应用系统层面相关 写在这里===================
    public static final int APP_STATU_FORCE_KILLED = -1; //应用在后台被强杀了
    public static final int APP_STATU_NORMAL = 0;        //应用正常运行
    public static String ACTION_GOLOGIN = "com.bfxasi.activity.login.LoginActivity";
    //电子钱包 设置安全密码
    public static String ACTION_GO_LICENSE_SET = "com.bfxasi.activity.wallet.activity.LicensePhoneAuthOnSetActivity";
    public static String CATEGORY_GOLOGIN = "android.intent.category.DEFAULT";
    public static String ACTION_LAUNCHER = "com.bfxasi.LAUNCHER";
    public static final String APK_INSTALL = "API_INSTALLED";
    //TODO =============所有应用系统层面相关 写在这里===================


    //TODO =============所有安全相关的（信任的公钥 ）写在这里==============
    //3DES加密的密钥
//    public static final String TRIPLE_DES_KEY = ConstantHelper.TRIPLE_DES_KEY;
//    //RSA加密的公钥
//    public static final String RSA_PUBLIC_KEY = ConstantHelper.RSA_PUBLIC_KEY;
//    //服务器HTTPS公钥,用于根证校验
//    public static final String HTTPS_PUB_KEY_PRO = ConstantHelper.HTTPS_PUB_KEY_PRO;
//    public static final String HTTPS_PUB_KEY_BS = ConstantHelper.HTTPS_PUB_KEY_BS;
//    public static final String HTTPS_PUB_KEY_18 = ConstantHelper.HTTPS_PUB_KEY_18;
//    public static final String HTTPS_PUB_KEY_CS = ConstantHelper.HTTPS_PUB_KEY_CS;
    //请求证书下载路径
//    public static final String CERTPATH = "http://219.143.240.4:80/apk/certkey.cert";
    //请求证书下载路径
//    public static final String CERTPATH = "http://114.255.225.26:81/apk/certkey.cert";
    /*生产环境*/
//    public static final String CERTPATH = "http://www.rsfw.xiongan.gov.cn:8088/apk/certkey.cert";
    //更新apk的秘钥
//    public static final String UPDATEAPK_DES3_SECRETKEY = ConstantHelper.UPDATEAPK_DES3_SECRETKEY;
//    //Des3加密解密需要的向量值
//    public static final String UPDATEAPK_IVPARAMETERSPEC = ConstantHelper.UPDATEAPK_IVPARAMETERSPEC;
    //预留属性
    public static final String PROP = "prop";
    // 构件公钥,差量更新
    public static final String HRMS_PUB_KEY = "8faa7c1800ef60d52872df6a44c3c8072bb57133adc3143389e89d13b722f3fea2fa8d3ac60aace26f56a45f9ab13775bd1d923817c4c7fb924dfd8c1eba1ad01119c56ed21770919b4476dbe11be7edf1716878ba10dc379cf541e458c3b06ed93e25972c78322781f6c0b9737fed7b2114c462233cef57c6857571d2663d7ee0eaa07124ae52dd1e7e5fc36ed1c364d1395e50a8e23ce4b2d542d5711fe1843494858d05a4b6835f8476ef098a5c398e725f23fd52bd0042bdef5283c9ee3351a2a715ac5c426817e18cba12e77a3c2457e1082c17bf6339da6b851b75c3488be31d451bc1f6f06a2acb1ab30677d25edbb339bd98a271df09f5fe7abe4a4d";
    // 资源文件id，需保证唯一性；如资源文件为"hrms_android$1.0.0.zip"，则配置hrms
    public static final String RES_ID = "xasiPack";
    //口粮补贴私钥的一半
//    public static final String SUBSIDIES_PRIVATE_KEY = ConstantHelper.SUBSIDIES_PRIVATE_KEY;
//    public static final String SUBSIDIES_DES_KEY = ConstantHelper.SUBSIDIES_DES_KEY;
    //TODO =============所有安全相关的（信任的公钥 ） 写在这里==============

    //TODO =============缓存相关  写在这里=================
    //首页缓存 key
    public static final String CACHE_INDEX_DATA = "index_data";
    //全部服务缓存 key
    public static final String CACHE_ALL_SERVICE_DATA = "all_service_data";
    //H5 缓存 key
    public static final String CACHE_H5_DATA = "toH5Bean";
    //TODO =============缓存相关  写在这里=================


    //TODO =============WebView  写在这里=================
    //返回上一界面
    public static final String WEBVIEW_GOBACK = "goBack";
    //跳转登录页面
    public static final String WEBVIEW_DO_LOGINING = "doLogining";
    //跳转实名认证页面
    public static final String WEBVIEW_DO_AUTHORIZING = "doAuthorizing";
    //h5 获取用户信息
    public static final String WEBVIEW_GET_USERINFO = "getUserInfo";
    //h5 跳转到刷脸页面
    public static final String WEBVIEW_SHOW_FACE = "showFace";
    //h5 跳转到拍照、图库选择对话框
    public static final String WEBVIEW_SHOW_IMAGE_PICKER = "showImagePicker";
    //h5 跳转到新的webview
    public static final String WEBVIEW_SHOW_WEB_VIEW = "showWebView";
    //h5 扫描二维码或者条形码
    public static final String WEBVIEW_SCAN_QR_BAR= "getScanCode";
    //h5埋点
    public static final String WEBVIEW_DO_BURY_POINT= "doBuryPoint";
    //登录成功
    public static final String LOGIN_SUCCESS = "loginSuccess";

    //
    public static final String MESSAGE_FINISH_AccidentEntranceActivity = "finishAccidentEntranceActivity";

    //H5页面 未登录跳转到原生登录页面后，登录成功后回到WebviewActivity的onResume用到的判断标识
    public static boolean WEBVIEW_IS_DO_LOGINING = false;
    //H5页面 未实名认证跳转到原生实名认证页面后，认证成功后回到WebviewActivity的onResume用到的判断标识
    public static boolean WEBVIEW_IS_DO_AUTHORIZING = false;
    // 刷脸数据 base64字符串 key
    public static final String FACE_IMG_DATA = "faceImgData";
    //TODO =============WebView  写在这里=================

    /**
     * ==================================服务搜索===============================================
     **/
    //参保登记
    public static int EntranceActivity_FLAG_REGISTER = 1;
    //口粮补贴
    public static int EntranceActivity_FLAG_RATION = 2;
    //待遇申领
    public static int EntranceActivity_FLAG_TREATMENT = 3;

    /**==================================服务搜索===============================================**/

    /**
     * ==================================应用商店下载地址===============================================
     **/
    //百度微下载地址
    public static String WEI_BAIDU_DOWNLOAD_PAHT = "https://mobile.baidu.com/item?docid=27267908&f0=search_searchContent%400_appBaseNormal%400";
    //腾讯微下载地址
    public static String WEI_TENCENT_DOWNLOAD_PATH = "https://a.app.qq.com/o/simple.jsp?pkgname=com.bfxasi";
    //搜狗微下载地址
    public static String WEI_SOUGOU_DOWNLOAD_PATH = "http://zhushou.sogou.com/apps/detail/684993.html";
    //华为微下载地址
    public static String WEI_HUAWEI_DOWNLOAD_PATH = "https://appgallery.huawei.com/#/app/C102090297";
    //小米微下载地址
    public static String WEI_XIAOMI_DOWNLOAD_PATH = "http://app.mi.com/details?id=com.bfxasi";
    //阿里微下载地址
    public static String WEI_ALI_DOWNLOAD_PATH = "https://www.wandoujia.com/apps/8104717";
/**==================================应用商店下载地址===============================================**/


    /**
     * ==================================实名认证 模块===============================================
     **/
    //实名认证 提示语
    public static final String AUTH_TIP_DETAIL = "拍摄/上传参保人员身份证信息";
    //区分从哪跳转
    public static final String AUTH_FROM_DO_REDUCE_OTHER_INSURANCE = "doReduceOtherInsurance";
    //实名认证
    public static final String AUTH_TYPE_REAL_NAME = "realName";
    //参保登记
    public static final String AUTH_TYPE_INSURED_REGIST = "insuredRegist";
    //工伤保险项目减员--为他人
    public static final String AUTH_TYPE_INSURED_REDUCE = "insuredReduce";
    /**
     * ==================================实名认证 模块===============================================
     **/

    /**
     * ==================================拍照、图库选择 模块===============================================
     **/
    //国徽面
    public static final String PHOTO_SELECT_NATIONAL_EMBLEM = "nationalEmblem";
    //人脸面
    public static final String PHOTO_SELECT_HUMAN_FACE = "humanFace";
    //银行卡面
    public static final String PHOTO_SELECT_BANKCARD = "bankCard";
    /**
     * ==================================拍照、图库选择 模块===============================================
     **/

    /**
     * ==================================电子钱包 模块===============================================
     **/
    public static boolean eWalletYaoOpened = true;
    public static boolean showPassCodeDialog = true;
    public static final String ELECTRONIC_RESULT_SUCCESS = "ElectronicResultSuccess"; //电子钱包提交成功
    public static final String ELECTRONIC_RESULT_ERROR = "ElectronicResultError"; //电子钱包提交失败
    public static final String ELECTRONICWALLET_PAY = "ELECTRONICWALLET_PAY"; //电子钱包支付
    public static final String ELECTRONICWALLET_NO_SCAN= "ELECTRONICWALLET_NO_SCAN"; //电子钱包提交失败
    public static final String ELECTRONICWALLET_MESSAGE= "ELECTRONICWALLET_MESSAGE"; //电子钱包带提示内容
    public static final String ELECTRONICWALLET_CREATE= "ELECTRONICWALLET_CREATE"; //电子钱包开户结果处理
    public static final String ELECTRONICWALLET_FROM_MINE_OPENING= "ELECTRONICWALLET_FROM_MINE_OPENING"; //我的界面进电子钱包开户中或销户中
    public static final String ELECTRONICWALLET_FROM_MINE_DELETEING= "ELECTRONICWALLET_FROM_MINE_DELETEING"; //我的界面进电子钱包开户中或销户中

    /**
     * ==================================电子钱包 模块===============================================
     **/
}
