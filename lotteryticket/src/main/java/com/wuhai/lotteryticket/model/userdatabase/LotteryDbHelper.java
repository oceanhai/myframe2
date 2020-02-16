package com.wuhai.lotteryticket.model.userdatabase;

/**
 * Created by wangC on 2017/4/27.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wuhai.lotteryticket.model.bean.Lottery;
import com.wuhai.lotteryticket.model.dao.DaoMaster;
import com.wuhai.lotteryticket.utils.DateUtils;
import com.wuhai.lotteryticket.utils.LogProxy;

import org.greenrobot.greendao.database.Database;

/**
 * 数据库升级类
 */
public class LotteryDbHelper extends DaoMaster.OpenHelper {

    public final static String TAG = "db_lottery";

    public LotteryDbHelper(Context context, String name) {
        super(context, name);
    }

    public LotteryDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        LogProxy.e(TAG, "  SCHEMA_VERSION=" + DaoMaster.SCHEMA_VERSION);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
        LogProxy.e(TAG, "  SCHEMA_VERSION=" + DaoMaster.SCHEMA_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogProxy.e(TAG, "开始升级数据库 " + DateUtils.getDateAllString());
        LogProxy.e(TAG, "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        MigrationHelper.migrate(db,
                Lottery.class
        );
        LogProxy.e(TAG, " 结束升级数据库 " + DateUtils.getDateAllString());

        RefreshDataManager.onUpgrade(db,oldVersion,newVersion);//升级之后，必要的刷数据操作
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        LogProxy.e(TAG, "onDowngrade  旧版本" + oldVersion + "    oldVersion   " + oldVersion + "  by dropping all tables");
    }
}
