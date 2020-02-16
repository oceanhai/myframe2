package com.wuhai.lotteryticket.model.userdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.wuhai.lotteryticket.application.BaseApplication;
import com.wuhai.lotteryticket.model.dao.DaoMaster;
import com.wuhai.lotteryticket.model.dao.DaoSession;
import com.wuhai.lotteryticket.utils.LogProxy;

import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.QueryBuilder;

/**
 * 作者 wh
 *
 * 创建日期 2020/2/14 16:36
 *
 * 描述：创建数据库
 */
public class LotteryDbManager {

    private final static String dbName = "lottery.db";
    private LotteryDbHelper devOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static LotteryDbManager mInstance;
    private Context context;

    private AsyncSession asyncSession;


    private LotteryDbManager(   ) {
        this.context = BaseApplication.getInstance();
        devOpenHelper = new LotteryDbHelper(context, dbName, null);
        mDaoMaster=new DaoMaster(devOpenHelper.getReadableDatabase());
        mDaoSession=mDaoMaster.newSession();
        asyncSession=new AsyncSession(mDaoSession);

        //日志输出 默认false
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 获取单例引用
     *
     * @param
     * @return
     */
    public static LotteryDbManager getInstance( ) {
        if (mInstance == null) {
            synchronized (LotteryDbManager.class) {
                if (mInstance == null) {
                    mInstance = new LotteryDbManager();
                }
            }
        }
        return mInstance;
    }

    public  void setNull(){
        if(mInstance!=null){
            LogProxy.e(LotteryDbHelper.TAG, "清除缓存 关闭数据库链接 ");
            mDaoSession.clear();//清除缓存
            devOpenHelper.close();//关闭数据库链接
            mDaoMaster=null;
            mDaoSession=null;
            mInstance=null;
        }

    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase db = devOpenHelper.getReadableDatabase();
        return db ;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();

        return db;
    }

    public AsyncSession getAsyncSession() {
        return asyncSession;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        return mDaoSession;
    }
    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
