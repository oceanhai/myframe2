package com.wuhai.myclient.teststartactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.hint)
    TextView hint;
    @BindView(R.id.standard)
    Button standard;
    @BindView(R.id.singleTop)
    Button singleTop;
    @BindView(R.id.singleTask)
    Button singleTask;
    @BindView(R.id.singleInstance)
    Button singleInstance;
    @BindView(R.id.message)
    EditText message;
    @BindView(R.id.message_show)
    TextView messageShow;
    @BindView(R.id.num)
    TextView num;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, StandardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_single_top);
        ButterKnife.bind(this);

        hint.setText("Standard模式");
        standard.setOnClickListener(this);
        singleTop.setOnClickListener(this);
        singleTask.setOnClickListener(this);
        singleInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.standard:
                StandardActivity.startActivity(this);
                break;
            case R.id.singleTop:
                SingleTopActivity.startActivity(this, message.getText().toString());
                break;
            case R.id.singleTask:
                SingleTaskActivity.startActivity(this, message.getText().toString());
                break;
            case R.id.singleInstance:
                Intent intent = new Intent();
                intent.setClassName("com.myframe.www",
                        "com.myframe.www.teststartactivityforresult.SingleInstanceActivity");
//                intent.setComponent(new ComponentName("com.myframe.www",
//                        "com.myframe.www.teststartactivityforresult.SingleInstanceActivity"));
                startActivity(intent);
                break;
        }
    }

}
