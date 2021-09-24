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
 * 参考
 * https://www.cnblogs.com/guanxinjing/p/11544273.html
 * LiveData与MutableLiveData区别
 * LiveData与MutableLiveData的其实在概念上是一模一样的.唯一几个的区别如下:
 * 1.MutableLiveData的父类是LiveData
 * 2.LiveData在实体类里可以通知指定某个字段的数据更新.
 * 3.MutableLiveData则是完全是整个实体类或者数据类型变化后才通知.不会细节到某个字段
 *
 * https://blog.csdn.net/weixin_43493559/article/details/109003746
 * 用ViewModelProvider(ViewModelProviders过时)
 *
 *
 */
public class LiveDataActivity extends AppCompatActivity {
    private static final String TAG = "LiveDataActivity";
    private Button mBtnAddData;
    private DemoViewModel mDemoViewModel;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LiveDataActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);
        mBtnAddData = findViewById(R.id.btn_add_data);
//        mDemoViewModel = ViewModelProviders.of(this).get(DemoViewModel.class);//获取ViewModel,让ViewModel与此activity绑定
        mDemoViewModel =  new ViewModelProvider(this ,
                new ViewModelProvider.NewInstanceFactory()).get(DemoViewModel.class);
        mDemoViewModel.getDemoData().observe(this, new Observer<DemoData>() { //注册观察者,观察数据的变化
            @Override
            public void onChanged(DemoData demoData) {
                Log.e(TAG, "onChanged: 数据有更新");
                Log.e(TAG, "DemoData："+demoData.toString());
            }
        });

        mBtnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: 已经点击");
                mDemoViewModel.getDemoData().setTag1(123); //这里手动用按键点击更新数据

            }
        });
    }
}
