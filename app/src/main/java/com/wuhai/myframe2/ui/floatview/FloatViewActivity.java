package com.wuhai.myframe2.ui.floatview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

/** 
* 浮动view
* @author wuhai
* create at 2016/2/29 14:53
*/  
public class FloatViewActivity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;

    public static void startActivity(Context context){
        Intent intent = new Intent(context,FloatViewActivity.class);
        context.startActivity(intent);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                FloatView1Activity.startActivity(this);
                break;
            case R.id.btn02:
                FloatView2Activity.startActivity(this);
                break;
        }
    }
}
