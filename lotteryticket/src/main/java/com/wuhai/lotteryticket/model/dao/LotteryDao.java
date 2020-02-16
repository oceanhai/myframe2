package com.wuhai.lotteryticket.model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wuhai.lotteryticket.model.bean.Lottery;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Lottery".
*/
public class LotteryDao extends AbstractDao<Lottery, String> {

    public static final String TABLENAME = "Lottery";

    /**
     * Properties of entity Lottery.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Lottery_id = new Property(0, String.class, "lottery_id", true, "lottery_id");
        public final static Property Lottery_type = new Property(1, String.class, "lottery_type", false, "lottery_type");
        public final static Property Lottery_name = new Property(2, String.class, "lottery_name", false, "lottery_name");
        public final static Property Lottery_red_ball = new Property(3, String.class, "lottery_red_ball", false, "lottery_red_ball");
        public final static Property Lottery_blue_ball = new Property(4, String.class, "lottery_blue_ball", false, "lottery_blue_ball");
        public final static Property Lottery_red_ball_count = new Property(5, int.class, "lottery_red_ball_count", false, "lottery_red_ball_count");
        public final static Property Lottery_blue_ball_count = new Property(6, int.class, "lottery_blue_ball_count", false, "lottery_blue_ball_count");
        public final static Property Lottery_bet_num = new Property(7, int.class, "lottery_bet_num", false, "lottery_bet_num");
        public final static Property Lottery_bet_money = new Property(8, int.class, "lottery_bet_money", false, "lottery_bet_money");
        public final static Property Lottery_produce_method = new Property(9, int.class, "lottery_produce_method", false, "lottery_produce_method");
        public final static Property Lottery_no = new Property(10, String.class, "lottery_no", false, "lottery_no");
        public final static Property Create_time = new Property(11, String.class, "create_time", false, "create_time");
        public final static Property Last_modified = new Property(12, String.class, "last_modified", false, "last_modified");
    }


    public LotteryDao(DaoConfig config) {
        super(config);
    }
    
    public LotteryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Lottery\" (" + //
                "\"lottery_id\" TEXT PRIMARY KEY NOT NULL ," + // 0: lottery_id
                "\"lottery_type\" TEXT," + // 1: lottery_type
                "\"lottery_name\" TEXT," + // 2: lottery_name
                "\"lottery_red_ball\" TEXT," + // 3: lottery_red_ball
                "\"lottery_blue_ball\" TEXT," + // 4: lottery_blue_ball
                "\"lottery_red_ball_count\" INTEGER NOT NULL ," + // 5: lottery_red_ball_count
                "\"lottery_blue_ball_count\" INTEGER NOT NULL ," + // 6: lottery_blue_ball_count
                "\"lottery_bet_num\" INTEGER NOT NULL ," + // 7: lottery_bet_num
                "\"lottery_bet_money\" INTEGER NOT NULL ," + // 8: lottery_bet_money
                "\"lottery_produce_method\" INTEGER NOT NULL ," + // 9: lottery_produce_method
                "\"lottery_no\" TEXT," + // 10: lottery_no
                "\"create_time\" TEXT NOT NULL ," + // 11: create_time
                "\"last_modified\" TEXT NOT NULL );"); // 12: last_modified
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Lottery\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Lottery entity) {
        stmt.clearBindings();
 
        String lottery_id = entity.getLottery_id();
        if (lottery_id != null) {
            stmt.bindString(1, lottery_id);
        }
 
        String lottery_type = entity.getLottery_type();
        if (lottery_type != null) {
            stmt.bindString(2, lottery_type);
        }
 
        String lottery_name = entity.getLottery_name();
        if (lottery_name != null) {
            stmt.bindString(3, lottery_name);
        }
 
        String lottery_red_ball = entity.getLottery_red_ball();
        if (lottery_red_ball != null) {
            stmt.bindString(4, lottery_red_ball);
        }
 
        String lottery_blue_ball = entity.getLottery_blue_ball();
        if (lottery_blue_ball != null) {
            stmt.bindString(5, lottery_blue_ball);
        }
        stmt.bindLong(6, entity.getLottery_red_ball_count());
        stmt.bindLong(7, entity.getLottery_blue_ball_count());
        stmt.bindLong(8, entity.getLottery_bet_num());
        stmt.bindLong(9, entity.getLottery_bet_money());
        stmt.bindLong(10, entity.getLottery_produce_method());
 
        String lottery_no = entity.getLottery_no();
        if (lottery_no != null) {
            stmt.bindString(11, lottery_no);
        }
        stmt.bindString(12, entity.getCreate_time());
        stmt.bindString(13, entity.getLast_modified());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Lottery entity) {
        stmt.clearBindings();
 
        String lottery_id = entity.getLottery_id();
        if (lottery_id != null) {
            stmt.bindString(1, lottery_id);
        }
 
        String lottery_type = entity.getLottery_type();
        if (lottery_type != null) {
            stmt.bindString(2, lottery_type);
        }
 
        String lottery_name = entity.getLottery_name();
        if (lottery_name != null) {
            stmt.bindString(3, lottery_name);
        }
 
        String lottery_red_ball = entity.getLottery_red_ball();
        if (lottery_red_ball != null) {
            stmt.bindString(4, lottery_red_ball);
        }
 
        String lottery_blue_ball = entity.getLottery_blue_ball();
        if (lottery_blue_ball != null) {
            stmt.bindString(5, lottery_blue_ball);
        }
        stmt.bindLong(6, entity.getLottery_red_ball_count());
        stmt.bindLong(7, entity.getLottery_blue_ball_count());
        stmt.bindLong(8, entity.getLottery_bet_num());
        stmt.bindLong(9, entity.getLottery_bet_money());
        stmt.bindLong(10, entity.getLottery_produce_method());
 
        String lottery_no = entity.getLottery_no();
        if (lottery_no != null) {
            stmt.bindString(11, lottery_no);
        }
        stmt.bindString(12, entity.getCreate_time());
        stmt.bindString(13, entity.getLast_modified());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public Lottery readEntity(Cursor cursor, int offset) {
        Lottery entity = new Lottery( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // lottery_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // lottery_type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lottery_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // lottery_red_ball
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // lottery_blue_ball
            cursor.getInt(offset + 5), // lottery_red_ball_count
            cursor.getInt(offset + 6), // lottery_blue_ball_count
            cursor.getInt(offset + 7), // lottery_bet_num
            cursor.getInt(offset + 8), // lottery_bet_money
            cursor.getInt(offset + 9), // lottery_produce_method
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // lottery_no
            cursor.getString(offset + 11), // create_time
            cursor.getString(offset + 12) // last_modified
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Lottery entity, int offset) {
        entity.setLottery_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setLottery_type(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLottery_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLottery_red_ball(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLottery_blue_ball(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLottery_red_ball_count(cursor.getInt(offset + 5));
        entity.setLottery_blue_ball_count(cursor.getInt(offset + 6));
        entity.setLottery_bet_num(cursor.getInt(offset + 7));
        entity.setLottery_bet_money(cursor.getInt(offset + 8));
        entity.setLottery_produce_method(cursor.getInt(offset + 9));
        entity.setLottery_no(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCreate_time(cursor.getString(offset + 11));
        entity.setLast_modified(cursor.getString(offset + 12));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Lottery entity, long rowId) {
        return entity.getLottery_id();
    }
    
    @Override
    public String getKey(Lottery entity) {
        if(entity != null) {
            return entity.getLottery_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Lottery entity) {
        return entity.getLottery_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
