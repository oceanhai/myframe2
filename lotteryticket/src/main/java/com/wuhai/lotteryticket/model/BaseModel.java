package com.wuhai.lotteryticket.model;


/**
 * 作者 wh
 *
 * 创建日期 2020/2/14 17:43
 *
 * 描述：
 */
public class BaseModel {


    public String mClassName = this.getClass().getSimpleName();


//    public void insertLog(OperateLog log) {
//         LogDao.getInstance().addLog(log);
//
//    }
//
//
//    public String getShopId() {
//        String shopId = (String) SPUtils.get(CommonValues.SP_KEY_SHOP_ID, "");
//        return shopId;
//    }
//
//
//   /* public String getUserToken() {
//         String userToken = (String) SPUtils.get(CommonValues.SP_KEY_USER_TOKEN, "");
//         return userToken;
//    }*/
//
//    public String getAccessToken() {
//        return SPUtils.get(CommonValues.SP_KEY_ACCESSTOKEN, "").toString();
//    }
//
//
//    /**
//     * 解密
//     *
//     * @param msg 传入需要加密的字符串
//     * @return
//     */
//    public static String decryptAES(String msg) {
//        return PosEncryptUtils.decryptAES(msg);
//    }
//
//    /**
//     * 加密
//     *
//     * @param msg 传入需要加密的字符串
//     * @return
//     */
//    public static String encryptAES(String msg) {
//        return PosEncryptUtils.encryptAES(msg);
//    }

}
