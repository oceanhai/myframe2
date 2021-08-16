package com.wuhai.myframe2.ui.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DBActivity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.insert)
    Button insert;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.update_data)
    Button updateData;
    @BindView(R.id.update_db)
    Button updateDb;
    @BindView(R.id.create_db)
    Button createDb;
    @BindView(R.id.update_insert)
    Button updateInsert;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.height)
    EditText height;
    @BindView(R.id.show_info)
    TextView showInfo;

    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;

    private DBDAO dao;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DBActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void init() {
        //打开一个数据库 TODO 这样是不可以的
//        db = openOrCreateDatabase("myframe", MODE_PRIVATE, null);

        dao = new DBDAO(this);
    }

    private void initListener() {
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        updateData.setOnClickListener(this);
        updateDb.setOnClickListener(this);
        createDb.setOnClickListener(this);
        updateInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues values;
        switch (v.getId()) {
            case R.id.create_db:
                helper = new MySQLiteOpenHelper(this);
                db = helper.getWritableDatabase();
                break;
            case R.id.insert:
//                db.execSQL("insert into student (name,age) values ('路飞','30')");

//                dao.insert("insert into student (name,age) values ('路飞','30')");

                values = new ContentValues();
                values.put("name", name.getText().toString());
                values.put("age", age.getText().toString());
                dao.insert(values);
                break;
            case R.id.delete:
//                dao.delete("delete from student where _id=1");

                dao.delete("name=? AND age=?", new String[]{"索隆","18"});
                break;
            case R.id.update_data:
//                dao.update("update student set age=10 where name='路飞'");

                values = new ContentValues();
                values.put("name", "李七夜");
                values.put("age", 18);
                dao.update(values, "name=? and age=?", new String[]{name.getText().toString(), age.getText().toString()});
                break;
            case R.id.update_db:
                //VERSION 变后 在初始化时候就做了
                break;
            case R.id.update_insert:
//                db.execSQL("insert into student (name,age,height) values ('路飞','30','173')");
                values = new ContentValues();
                values.put("name", name.getText().toString());
                values.put("age", age.getText().toString());
                values.put("height", height.getText().toString());
                dao.insert(values);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }
}
