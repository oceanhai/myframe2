package com.wuhai.xiangxue.p1_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.xiangxue.InjectUtils;
import com.wuhai.xiangxue.R;

public class P1_3Activity extends AppCompatActivity {


    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, P1_3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_p1_3);

        InjectUtils.injectClick(this);

        setListener();
    }

    private void setListener() {
    }

    @Click(R.id.btn01)
    public void abc(View view){
        Toast.makeText(this, "我的被点击了！",Toast.LENGTH_LONG).show();
    }

    @Click(R.id.btn02)
    public void hehe(View view){
        Toast.makeText(this, "我也被点击了！",Toast.LENGTH_LONG).show();
    }
}
