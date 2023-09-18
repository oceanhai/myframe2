package com.wuhai.myframe2.ui.teststartactivityforresult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.text1)
    TextView text1;

    private final int FIRST_REQUEST_CODE = 1;
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

    private String messageStr;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FirstActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String message) {
        Intent intent = new Intent(context, FirstActivity.class);
        intent.putExtra("message",message);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(SecondActivity.TAG,"first-onCreate");
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        initParse();

        button1.setOnClickListener(this);
        standard.setOnClickListener(this);
        singleTop.setOnClickListener(this);
        singleTask.setOnClickListener(this);
        singleInstance.setOnClickListener(this);
    }

    private void initParse() {
        Intent intent = getIntent();
        if(intent!=null){
            messageStr = intent.getStringExtra("message");
        }
        if(TextUtils.isEmpty(messageStr)){
            text1.setText(messageStr);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(SecondActivity.TAG,"first-onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(SecondActivity.TAG,"first-onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(SecondActivity.TAG,"first-onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(SecondActivity.TAG,"first-onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(SecondActivity.TAG,"first-onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent i = new Intent(this, SecondActivity.class);
                i.putExtra("request_text_for_main", "从Main传递到SecondActivity");
                startActivityForResult(i, FIRST_REQUEST_CODE);
                finish();
                break;
            case R.id.standard:
                FirstActivity.startActivity(this);
                break;
            case R.id.singleTop:
                SingleTopActivity.startActivity(this,message.getText().toString());
                break;
            case R.id.singleTask:
                SingleTaskActivity.startActivity(this,message.getText().toString());
                break;
            case R.id.singleInstance:
                SingleInstanceActivity.startActivity(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FIRST_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                text1.setText(data.getStringExtra("request_text_for_third"));
                Log.e("onActivityResult","first:"+data.getStringExtra("request_text_for_third"));
            }
        }
    }
}
