package com.wuhai.myframe2.ui.ioc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;


/**
 * 作者 wh
 * <p>
 * 创建日期 2020/11/30 16:31
 * <p>
 * 描述：ioc
 */
public class IOCActivity extends AppCompatActivity {


    @ViewById(R.id.icon)
    private ImageView mIcon;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, IOCActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioc);

//        ViewUtils.inject(this);
//        mIconIv.setImageResource(R.drawable.cat_1);
    }


    @OnClick(R.id.icon)
    private void iconClick() {
    }
}