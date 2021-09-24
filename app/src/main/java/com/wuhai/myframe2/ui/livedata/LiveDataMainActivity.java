package com.wuhai.myframe2.ui.livedata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
public class LiveDataMainActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LiveDataMainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata_main);

        findViewById(R.id.LiveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataActivity.startActivity(LiveDataMainActivity.this);
            }
        });

        findViewById(R.id.MutableLiveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MutableLiveDataActivity.startActivity(LiveDataMainActivity.this);
            }
        });

    }
}
