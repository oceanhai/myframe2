package com.wuhai.myframe2.ui.teststartactivityforresult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleInstanceActivity extends BaseActivity2 implements View.OnClickListener {


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

    private static final String TAG = SingleInstanceActivity.class.getSimpleName();
    private String messageStr;
    private int numInt;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SingleInstanceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
        ButterKnife.bind(this);

        Log.e(TAG, "执行了onCreate");
//        parseData("onCreate");
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "执行了onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "执行了onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "执行了onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "执行了onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "执行了onDestroy()");
    }

//    private void parseData(String from) {
//        Log.e(TAG, from + ":parseData()");
//        Intent intent = getIntent();
//        if (intent != null) {
//            messageStr = intent.getStringExtra("message");
//        }
//        if (!TextUtils.isEmpty(messageStr)) {
//            messageShow.setText(messageStr);
//        } else {
//            messageShow.setText("null");
//        }
//
//        numInt = 100;
//        num.setText(num.getText().toString() + numInt);
//    }

    private void init() {
        hint.setText("SingleInstance模式");
        standard.setOnClickListener(this);
        singleTop.setOnClickListener(this);
        singleTask.setOnClickListener(this);
        singleInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.standard:
                FirstActivity.startActivity(this);
                break;
            case R.id.singleTop:
                SingleTopActivity.startActivity(this, message.getText().toString());
                break;
            case R.id.singleTask:
                SingleTaskActivity.startActivity(this, message.getText().toString());
                break;
            case R.id.singleInstance:
                SingleInstanceActivity.startActivity(this);
                break;
        }
    }

//    /**
//     * TODO SingleTop启动模式，执行onNewIntent的时候并不会执行onCreate方法
//     * 区域医疗里有调用一次startActivity其实多此一举，其实在onNewIntent里初始化就好了
//     * 而且原先持有的属性不变，例如int变量num,原先是啥就是啥
//     * 但onStart()，onResume()还是要执行的
//     */
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Log.e(TAG, "执行了onNewIntent");
//        if (intent != null) {
//            messageStr = intent.getStringExtra("message");
//        }
//        if (!TextUtils.isEmpty(messageStr)) {
//            messageShow.setText(messageStr);
//        } else {
//            messageShow.setText("null");
//        }
//        Log.e(TAG, "onNewIntent时候numInt="+numInt+"属性还在");
//    }
}
