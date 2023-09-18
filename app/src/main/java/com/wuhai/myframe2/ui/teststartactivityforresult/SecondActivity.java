package com.wuhai.myframe2.ui.teststartactivityforresult;

import android.app.Activity;
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

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "activity生命周期";

    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.button1)
    Button button1;

    private final int SECOND_REQUEST_CODE = 2;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        Log.e(TAG,"second-onCreate");

        button1.setOnClickListener(this);

        if (getIntent().getStringExtra("request_text_for_main") != null) {
            text = getIntent().getStringExtra("request_text_for_main");
            text1.setText(text);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"second-onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"second-onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"second-onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"second-onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"second-onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
//                i.putExtra("request_text_for_second", text+"\n"+"从SecondActivity传递到ThirdActivity");
                i.putExtra("request_text_for_second", "从SecondActivity传递到ThirdActivity");
                startActivityForResult(i, SECOND_REQUEST_CODE);
                finish();//销毁了，所以从第三个activity回传不到第一个activity了
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG,"second:resultCode="+resultCode+",resultCode="+resultCode);
        if(requestCode == SECOND_REQUEST_CODE && data != null){
            setResult(Activity.RESULT_OK, data);
            Log.e(TAG,"second:"+data.getStringExtra("request_text_for_third"));
            finish();
        }
    }
}
