package com.wuhai.myframe2.ui.ioc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;


/**
 * 作者 wh
 *
 * 创建日期 2020/11/30 16:31
 *
 * 描述：ioc
 */
public class IOCActivity extends AppCompatActivity {

    @ViewById(R.id.icon)
    private ImageView mIconIv;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, IOCActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioc);

        ViewUtils.inject(this);
        mIconIv.setImageResource(R.drawable.cat_1);
    }

    @OnClick(R.id.icon)
    private void onClick(View view) {
//        int i = 2 / 0;//TODO 这里是做了个测试，ViewUtils里直接捕获了异常，但这样不抛出异常，开发怎么定位呢是吧
//        Toast.makeText(this, "图片点击了"+i, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "图片点击了", Toast.LENGTH_LONG).show();
    }

}