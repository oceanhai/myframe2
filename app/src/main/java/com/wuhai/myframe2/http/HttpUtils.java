package com.wuhai.myframe2.http;

/**
 * 作者 wh
 *
 * 创建日期 2019/11/6 17:57
 *
 * 描述：lct 所用上传工具
 */
public class HttpUtils {
    //获取 apiversion code
     private static String apiVersion= "1.0";

    //client version
    private static String client= "android";

    //version code
    private static String versioCode= "1.0";

    //test Flag
    private  static Boolean isTest=false;

    //appsrckey
    private static String appKey  ="VRMEWKYY";
   // private static String appKey  ="A1B5MBYQ";


    private static String appSecret="8f00b204e9800998sagdrmvdphykyrud";

    //  private static String  appSecret="c02e7a17b7f00c29cpl3gey1yfaep93z";
    /***
     * 使用get方法 获取json
     *
     *
     * @param parMap
     * @param callBack
     * @param tag
     */
//    public static void doGet(String methdeName, Map<String, String> parMap, BaseRequestCallBack callBack, Object tag) {
//
//        String buildedUrl=buildParmarsToUrl(PosUrlConfing.getUrl()+"r="+methdeName ,methdeName ,parMap);
//        logMap(parMap,buildedUrl);
//        HttpManager.getInstance().doGetAsync(buildedUrl, parMap, callBack, tag);
//
//    }
//
//
//
//    /***
//     * 使用Post方法 获取json
//     *
//     *
//     * @param parMap
//     * @param callBack
//     * @param tag
//     */
//    public static void doPost(String methdeName, Map<String, String> parMap, BaseRequestCallBack callBack, Object tag) {
//        String buildedUrl=buildParmarsToUrl(PosUrlConfing.getUrl()+ "r="+methdeName   ,methdeName ,parMap);
//        logMap(parMap,buildedUrl);
//        HttpManager.getInstance().doPostAsync(buildedUrl, parMap, callBack, tag);
//    }
//
//    /***
//     * 下载文件
//     *
//     *
//     * @param
//     * @param callBack
//     * @param tag
//     */
//    public static void donwLoadFile(String path, String fileName, String url, BaseRequestCallBack callBack, Object tag) {
//        HttpManager.getInstance().doDownFile( path,fileName,url, callBack );
//    }
//
//    /***
//     * 上传文件
//     *
//     *
//     * @param
//     * @param callBack
//     * @param tag
//     */
//    public static void upLoadFile(String methdeName, String filename, Map<String, String> parMap , Map<String, File> fileMap, LctCallBackInterFace callBack, Object tag) {
//        String buildedUrl=buildParmarsToUrl(PosUrlConfing.getUrl()+ "r="+methdeName   ,methdeName ,parMap);
//        logMap(parMap,buildedUrl);
//        HttpManager.getInstance().doPostUpLoadFile( buildedUrl,filename,parMap,fileMap, callBack,tag);
//    }
//
//    /***
//     * 多文件
//     *
//     *
//     * @param
//     * @param callBack
//     * @param tag
//     */
//    public static void upLoadFileMany(String methdeName, Map<String, String> parMap , Map<String,UpLoadFile> fileMap, LctCallBackInterFace callBack, Object tag) {
//        String buildedUrl=buildParmarsToUrl(PosUrlConfing.getUrl()+ "r="+methdeName   ,methdeName ,parMap);
//        logMap(parMap,buildedUrl);
//        HttpManager.getInstance().doPostUpLoadFileMany( buildedUrl, parMap,fileMap, callBack,tag);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    /**
//     * 拼接get url
//     * @param url
//     * @param map
//     * @return
//     */
//    private static String buildParmarsToUrl(String url, String medthName, Map<String, String> map){
//        TreeMap<String, String> parMap=new TreeMap<String, String>();
//        parMap.put("v", String.valueOf(apiVersion));
//        parMap.put("client",client);
//        parMap.put("deviceName", DeviceUtils.getDeviceName());
//        parMap.put("cv",  String.valueOf( AppUtils.getVersionCode()));
//        parMap.put("cvname",  String.valueOf( AppUtils.getVersionName()));
//        if(isTest){
//            parMap.put("test", String.valueOf( isTest));
//        }
//
//       // parMap.put("accessToken","7de01k9u1v1eflptbolfhjtnk5") ;
//        if(!TextUtils.isEmpty( SPUtils.get(CommonValues.SP_KEY_ACCESSTOKEN,"").toString()) ){
//           parMap.put("accessToken",SPUtils.get(CommonValues.SP_KEY_ACCESSTOKEN,"").toString());
//
//        }
//
//        parMap.put("device_id",SPUtils.get(CommonValues.SP_KEY_DEVICE_ID,"").toString());
//
//        parMap.put("db_version", DaoMaster.SCHEMA_VERSION+"");
//
//        /*// parMap.put("accessToken","7de01k9u1v1eflptbolfhjtnk5") ;
//        if(!TextUtils.isEmpty( SPUtils.get(CommonValues.SP_KEY_USER_TOKEN,"").toString())  ){
//            parMap.put("accessToken",SPUtils.get(CommonValues.SP_KEY_ACCESSTOKEN,"").toString());
//
//        }*/
//        parMap.put("app_key",appKey);
//
//        parMap.put("r",medthName);
//        String sign=getSignString(parMap,map);
//
//        parMap.put("sign",sign);
//        StringBuffer sbf=new StringBuffer(url );
//        for (Map.Entry<String, String> entry : parMap.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            if(!key.equals("r")){
//                sbf.append( "&"  );
//                sbf.append(key);
//                sbf.append("=");
//                sbf.append(value);
//            }
//        }
//
//        return sbf.toString();
//
//    }
//
//
//    /**
//     * 计算 sign签名
//     * @return
//     */
//    public static String getSignString(Map<String, String> publicPararms,
//                                       Map<String, String> pararms) {
//        TreeMap<String, String> parAll=new TreeMap<String, String>();
//        parAll.putAll(publicPararms);
//        if( pararms!=null) {
//            parAll.putAll(pararms);
//        }
//
//        StringBuffer sbf=new StringBuffer();
//        if (parAll == null) {
//
//        } else {
//            String keyValue = "";
//            for (Map.Entry<String, String> entry : parAll.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//
//                sbf.append(  key  + value );
//            }
//         }
//
//
//        sbf.append(appSecret);
//      //  LogUtil.e(" q签名之前  "+sbf.toString());
//        String md5Sign= MD5Util.MD5(sbf.toString()).toUpperCase() ;
//        //    LogUtil.e(" q签名之后  "+md5Sign);
//        return md5Sign  ;
//    }
//
//
//
//
//
//    public static void logMap(Map<String, String> parMap, String url){
//
//        StringBuffer sbf=new StringBuffer();
//        if(parMap!=null) {
//            for (Map.Entry<String, String> entry : parMap.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                sbf.append("&" + key + "=" + value);
//            }
//        }
//        LogUtil.e( CommonValues.httpTag, url + sbf.toString());
//    }



}
