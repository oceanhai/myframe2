package com.wuhai.mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wuhai.mvvm.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

public class List2Activity extends AppCompatActivity {

    private StudentAdapter2 mAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, List2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);

        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        mAdapter = new StudentAdapter2(this);
        binding.listview.setAdapter(mAdapter);
        initData();
    }

    private void initData() {
        List<Student2> datas = new ArrayList<>();
        for(int x=0;x<20;x++){
            Student2 student = new Student2();
            student.setAddr("地址"+x);
            student.setName("名字"+x);
            student.setPhoto("http://www.lecuntao.com/homenew/templates/default/images/hi.jpg?v=09e9caa5d6");
            datas.add(student);
        }
        mAdapter.setData(datas);
    }
}
