package com.wuhai.myframe2.ui.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试页面跳转和回退，页面间生命周期的执行顺序
 * 2016腾讯新闻 面试题
 */
public class ThreadStartActivity extends BaseActivity2 {

    public static final String TAG = "ThreadStartActivity";
    @BindView(R.id.text1)
    TextView text1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ThreadStartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_start);
        ButterKnife.bind(this);

        Log.v(TAG, "onCreate-1");

        Log.e(TAG,"UI isMainThread:"+isMainThread1()+","+isMainThread2()+","+isMainThread3());

        //非Ui线程能否启动activity？
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"new Thread isMainThread:"+isMainThread1()+","+isMainThread2()+","+isMainThread3());

                Intent intent = new Intent(ThreadStartActivity.this, TargetActivity.class);
                startActivity(intent);
            }
        }).start();

//        Intent intent = new Intent(ThreadStartActivity.this,TargetActivity.class);
//        startActivity(intent);


        //这你妈肯定是主线程啊
        text1.post(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG,"threadd1:"+ Thread.currentThread().getName());
            }
        });

        Log.v(TAG, "threadd1-oncreate:" + Thread.currentThread().getName());

        //这也是主线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG,"threadd2:"+ Thread.currentThread().getName());
            }
        });
        Log.v(TAG, "threadd2-oncreate:" + Thread.currentThread().getName());

        //这个就不是在主线程了呢
        HandlerThread thread = new HandlerThread("test");
        thread.start();
        Handler handler1 = new Handler(thread.getLooper());
        handler1.post(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    Log.v(TAG,"threadd3:"+ Thread.currentThread().getName());
                }
            }
        });

        for(int x=0;x<10;x++){
            Log.v(TAG, "threadd3-oncreate:" + Thread.currentThread().getName());
        }
    }

    /**
     * 判断是否是主线程的三种方式
     */
    private boolean isMainThread1(){
        return Looper.getMainLooper() == Looper.myLooper();
    }
    private boolean isMainThread2(){
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
    private boolean isMainThread3(){
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart-1");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(ThreadStartActivity.TAG, "onStart-1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume-1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause-1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop-1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy-1");
    }
}
