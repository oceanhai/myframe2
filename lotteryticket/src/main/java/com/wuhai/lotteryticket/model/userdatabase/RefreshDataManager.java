package com.wuhai.lotteryticket.model.userdatabase;


import android.database.sqlite.SQLiteDatabase;

import com.wuhai.lotteryticket.utils.LogProxy;

/**
 * Created by wuhai on 2017/09/19 10:00.
 * 描述：数据库升级带来的一些刷数据问题
 */

public class RefreshDataManager {

    /**
     * 刷数据
     */
    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogProxy.e(LotteryDbHelper.TAG,"RefreshDataManager oldVersion="+oldVersion+",newVersion="+newVersion);

        String sql = "";

        for(int i=oldVersion;i<newVersion;i++){
            switch (i){
                case 2://version 1 -> version 2
                    //刷：total_member_credit>0时member_state=1
                    // （本版本追加退货功能，total_member_credit可更改，所以需要member_state来判断会员是否赊过账）
//                    try {
//                        sql = "UPDATE pos_shop_member SET member_state=1 WHERE total_member_credit > 0;";
//                        LogProxy.e(PosDbHpler.TAG,"sql="+sql);
//                        db.execSQL(sql);
//                    }catch (Exception e){
//                        OperateLog operateLog = OperateLog.getModelLogInstance("RefreshDataManager", "数据库从3升级到4");
//                        operateLog.setLog_type(OperateLog.LOG_TYPE_ERROR);
//                        StringBuffer msg = new StringBuffer( "升级失败 异常信息");
//                        if(e!=null&& !TextUtils.isEmpty( e.getMessage())) {
//                            msg.append( " :"+e.getMessage());
//                        }
//                        operateLog.addErrorLog(msg.toString());
//                        LogDao.getInstance().addLog(operateLog);
//                    }
                    break;
            }
        }
    }

}
