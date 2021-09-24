package com.wuhai.myframe2.ui.livedata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wuhai.myframe2.R;

/**
 * 作者 wh
 *
 * 创建日期 2021/9/17 15:55
 *
 * 描述：
 *
 */
public class MutableLiveDataActivity extends AppCompatActivity {
    private static final String TAG = "MutableLiveDataActivity";
    private Button mBtnAddData;
    private DemoViewModel2 mDemoViewModel;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MutableLiveDataActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);
        mBtnAddData = findViewById(R.id.btn_add_data);
//        mDemoViewModel = ViewModelProviders.of(this).get(DemoViewModel.class);//获取ViewModel,让ViewModel与此activity绑定
        mDemoViewModel =  new ViewModelProvider(this ,
                new ViewModelProvider.NewInstanceFactory()).get(DemoViewModel2.class);
        mDemoViewModel.getMyString().observe(this, new Observer<String>() { //注册观察者,观察数据的变化
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: 数据有更新 s="+s);
            }
        });

        mBtnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: 已经点击");
                mDemoViewModel.setMyString("测试"); //这里手动用按键点击更新数据

            }
        });
    }
}
