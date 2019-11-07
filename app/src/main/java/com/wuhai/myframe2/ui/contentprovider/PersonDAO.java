package com.wuhai.myframe2.ui.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wuhai.retrofit.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
	private SQLiteDatabase db;

	public PersonDAO(Context context) {
		MyHelper helper = new MyHelper(context);
		db = helper.getWritableDatabase();
	}

	public void insert(ContentValues values) {
		long id = db.insert("person", null, values);
		LogUtil.e("PersonDAO", "id="+id);
	}

	public List<Person> getAllData(){
		List<Person> list = new ArrayList<>();

		Cursor cursor= db.query("person",null,null,null,null,null,null);
		while (cursor.moveToNext()){
			int id=cursor.getInt(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			LogUtil.e("PersonDAO","id"+id+",name"+name+",age="+age);

			Person person = new Person(name, age);
			list.add(person);

		}
		cursor.close();

		return list;
	}
}
