package com.wuhai.myframe2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Intent+bundle实现跨进程通讯 接收方ac
 *
 * 有道
 * 面试/2019/小米/应用权限的自定义 那块
 */
public class ActionReceiveActivity2 extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.btn01)
    Button btn01;

    public static final String TAG = "ActionReceiveActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_action_receive2);
        ButterKnife.bind(this);

        //  获得其他应用程序传递过来的数据
        if (getIntent() != null){
            Log.e(TAG, getIntent().toString());
        }
        if (getIntent().getData() != null) {
            //  获得Host，也就是info://后面的内容
            String host = getIntent().getData().getHost();
        }

        Bundle bundle = getIntent().getExtras();
        //  其他的应用程序会传递过来一个value值，在该应用程序中需要获得这个值
        String value = bundle.getString("value");
        //  将Host和Value组合在一下显示在EditText组件中
        tv01.setText("value =" + value);

        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                Intent intent = new Intent();
                //  设置要返回的属性值
                intent.putExtra("result", "你是猪吗");
                //  设置返回码和Intent对象
                setResult(RESULT_OK, intent);
                //  关闭Activity
                finish();
                break;
        }
    }
}
