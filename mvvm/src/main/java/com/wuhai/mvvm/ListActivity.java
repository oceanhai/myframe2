package com.wuhai.mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wuhai.mvvm.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private StudentAdapter mAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layouts.activity_list);

        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        mAdapter = new StudentAdapter(this);
        binding.listview.setAdapter(mAdapter);
        initData();
    }

    private void initData() {
        List<Student> datas = new ArrayList<>();
        for(int x=0;x<20;x++){
            Student student = new Student();
            student.setAddr("地址"+x);
            student.setName("名字"+x);
            student.setPhoto("http://www.lecuntao.com/homenew/templates/default/images/hi.jpg?v=09e9caa5d6");
            datas.add(student);
        }
        mAdapter.setData(datas);
    }
}
