package com.wuhai.myframe2.ui.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 进程间ContentProvider通信-数据准备 db插入数据
 */
public class ContentProviderServerActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.btn02)
    Button btn02;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ContentProviderServerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_content_provider_server);
        ButterKnife.bind(this);

        dao = new PersonDAO(this);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    private PersonDAO dao;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                ContentValues values = new ContentValues();
                values.put("name", "wuhai");
                values.put("age", 32);
                dao.insert(values);

                List<Person> list = dao.getAllData();
                StringBuilder stringBuilder = new StringBuilder();
                if (!list.isEmpty()) {
                    for (int x = 0; x < list.size(); x++) {
                        stringBuilder.append(list.get(x).getName() + "," + list.get(x).getAge() + "\n");
                    }
                }

                tv01.setText(stringBuilder.toString());

                break;
            case R.id.btn02:
                ContentValues values1 = new ContentValues();
                values1.put("name", "路飞");
                values1.put("age", 18);
                dao.insert(values1);

                List<Person> list1 = dao.getAllData();
                StringBuilder stringBuilder1 = new StringBuilder();
                if (!list1.isEmpty()) {
                    for (int x = 0; x < list1.size(); x++) {
                        stringBuilder1.append(list1.get(x).getName() + "," + list1.get(x).getAge() + "\n");
                    }
                }

                tv01.setText(stringBuilder1.toString());

                break;
        }
    }
}
