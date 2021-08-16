package com.wuhai.myframe2.ui.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//操作数据库的封装类
public class DBDAO {
	private SQLiteDatabase db;

	public DBDAO(Context context) {
		MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
		db = helper.getWritableDatabase();
	}

	/**
	 * 插入数据库
	 * @param values
	 */
	public void insert(ContentValues values) {
		db.insert("student", null, values);
	}

	/**
	 * 插入数据库
	 * @param sqlStr
	 */
	public void insert(String sqlStr){
		//"insert into 表名（列名，……） values （值，……）";
		db.execSQL(sqlStr);
	}

	/**
	 * 删除数据
	 * @param whereCaluse
	 * @param whereArgs
	 */
	public void delete(String whereCaluse, String... whereArgs) {
		db.delete("student", whereCaluse, whereArgs);
	}

	/**
	 * 删除数据
	 * @param sqlStr
	 */
	public void delete(String sqlStr){
		db.execSQL(sqlStr);
	}

	/**
	 * 更新数据库
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 */
	public void update(ContentValues values,String whereClause,String[] whereArgs){
		db.update("student", values, whereClause, whereArgs);
	}

	/**
	 * 更新数据
	 * @param sqlStr
	 */
	public void update(String sqlStr){
		db.execSQL(sqlStr);
	}

	/**
	 * 插入时，某条记录不存在则插入，存在则更新。或更新时，某条记录存在则更新，不存在则插入
	 * @param values
	 */
	public void replace(ContentValues values){
		db.replace("student",null,values);
	}

	/**
	 * 查询语句
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @return
	 */
	public Cursor query(String[] columns,String selection,String[] selectionArgs){
		Cursor c = db.query("student", columns, selection, selectionArgs, null, null, null);
		return c;
	}

	/**
	 * 查询所有
	 * @return
	 */
	public Cursor queryAll(){
		Cursor c = db.query("student", null,null,null, null, null, null);
		return c;
	}
	
}
