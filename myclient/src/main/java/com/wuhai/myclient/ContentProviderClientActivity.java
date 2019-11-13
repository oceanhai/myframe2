package com.wuhai.myclient;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentProviderClientActivity extends AppCompatActivity {

    private final static String TAG = "ContentProvider";

    @BindView(R.id.tv01)
    TextView tv01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ContentProviderClientActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_content_provider_client);
        ButterKnife.bind(this);

    }

    public void click1(View v) {
        String uriStr = "content://com.qihoo360.byod.info";
        //将String类型uri转换为Uri类型的Uri
        Uri uri = Uri.parse(uriStr);
        // 获得内容解析者
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if(cursor == null){
            tv01.setText("查询结果1：Cursor null");
            return;
        }
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
        }
    }

    public void click2(View v) {
        String uriStr = "content://com.wuhai.myframe2.ui.contentprovider.MyProvider2";
        // 将String类型uri转换为Uri类型的Uri
        Uri uri = Uri.parse(uriStr);
        Cursor c = getContentResolver().query(uri,
                new String[]{"name", "age"}, "_id=?", new String[]{"2"},
                null);
        if(c == null){
            tv01.setText("查询结果2：Cursor null");
            return;
        }

        while (c.moveToNext()) {
            String name = c.getString(0);
            int age = c.getInt(1);
            tv01.setText("查询结果2：name=" + name + ",age=" + age);
        }
    }

    public void click3(View v) {
        String uriStr = "content://com.wuhai.myframe2.ui.contentprovider.MyProvider2/persons";
        Uri uri = Uri.parse(uriStr);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor == null){
            tv01.setText("查询结果3：Cursor null");
            return;
        }

        List<Person> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));

            Person person = new Person();
            person.setId(id);
            person.setName(name);
            person.setAge(age);
            list.add(person);
        }

        if (!list.isEmpty()) {
            for (int x = 0; x < list.size(); x++) {
                stringBuilder.append("id="+list.get(x).getId() +
                        ",name="+list.get(x).getName()+",age="+list.get(x).getAge()+ "\n");
            }
        }

        tv01.setText("查询结果6：\n" + stringBuilder.toString());
    }

    public void click4(View v) {
        String uriStr = "content://com.wuhai.myframe2.ui.contentprovider.MyProvider2/persons/name";
        Uri uri = Uri.parse(uriStr);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor == null){
            tv01.setText("查询结果4：Cursor null");
            return;
        }
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            tv01.setText("查询结果4：name=" + name);
        }
    }

    public void click5(View v) {
        String uriStr = "content://com.wuhai.myframe2.ui.contentprovider.MyProvider2/persons/123";
        Uri uri = Uri.parse(uriStr);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor == null){
            tv01.setText("查询结果5：Cursor null");
            return;
        }
        while (cursor.moveToNext()) {
            int age = cursor.getInt(0);
            tv01.setText("查询结果5：age=" + age);
        }
    }

    public void click6(View v) {
        String uriStr = "content://com.wuhai.myframe2.ui.contentprovider.MyProvider2/persons/id";
        Uri uri = Uri.parse(uriStr);
        Cursor cursor = getContentResolver().query(uri, new String[]{"_id"}, null, null,
                null);
        if(cursor == null){
            tv01.setText("查询结果6：Cursor null");
            return;
        }

        List<Person> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            Person person = new Person();
            person.setId(id);
            list.add(person);
        }

        if (!list.isEmpty()) {
            for (int x = 0; x < list.size(); x++) {
                stringBuilder.append(list.get(x).getId() + "\n");
            }
        }

        tv01.setText("查询结果6：\n" + stringBuilder.toString());
    }

    /**
     * 草 既然能直接查询 我为啥还要用内容提供者？
     * @param v
     */
    public void click7(View v) {
//        PersonDAO dao = new PersonDAO(this);
//        List<Person> list = dao.getAllData();
//        StringBuilder stringBuilder = new StringBuilder();
//        if (!list.isEmpty()) {
//            for (int x = 0; x < list.size(); x++) {
//                stringBuilder.append(list.get(x).getName() + "," + list.get(x).getAge() + "\n");
//            }
//        }
//
//        tv01.setText(stringBuilder.toString());
    }
}
