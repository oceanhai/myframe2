package com.wuhai.myframe2.ui.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcHandlerThreadBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：HandlerThread
 *
 * 参考文章
 *  https://www.jianshu.com/p/9c10beaa1c95
 *  https://www.jianshu.com/p/ed9e15eff47a
 *
 */
public class HandlerThreadActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "HandlerThreadActivity";

    private AcHandlerThreadBinding binding;

    private Handler mainHandler,workHandler;
    private HandlerThread mHandlerThread;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HandlerThreadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_handler_thread);

        // 创建与主线程关联的Handler
        mainHandler = new Handler();
        Log.e(TAG, "mainHandler 所在线程 " + Thread.currentThread().getName());

        /**
         * 步骤1：创建HandlerThread实例对象
         * 传入参数 = 线程名字，作用 = 标记该线程
         */
        mHandlerThread = new HandlerThread("handlerThread");

        /**
         * 步骤2：启动线程
         */
        mHandlerThread.start();

        /**
         * 步骤3：创建工作线程Handler & 复写handleMessage（）
         * 作用：关联HandlerThread的Looper对象、实现消息处理操作 & 与其他线程进行通信
         * 注：消息处理操作（HandlerMessage（））的执行线程 = mHandlerThread所创建的工作线程中执行
         */

        workHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            // 消息处理的操作
            public void handleMessage(Message msg)
            {
                //设置了两种消息处理操作,通过msg来进行识别
                switch(msg.what){
                    // 消息1
                    case 1:
                        Log.e(TAG, "workHandler 所在线程 " + Thread.currentThread().getName());
                        try {
                            //延时操作
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 通过主线程Handler.post方法进行在主线程的UI更新操作
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run () {
                                binding.text1.setText("我爱学习");
                            }
                        });
                        break;

                    // 消息2
                    case 2:
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run () {
                                binding.text1.setText("我不喜欢学习");
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void setListener() {
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                // 通过sendMessage（）发送
                // a. 定义要发送的消息
                Message msg = Message.obtain();
                msg.what = 1; //消息的标识
                msg.obj = "A"; // 消息的存放
                // b. 通过Handler发送消息到其绑定的消息队列
                workHandler.sendMessage(msg);
                break;
            case R.id.button2:
                // 通过sendMessage（）发送
                // a. 定义要发送的消息
                Message msg2 = Message.obtain();
                msg2.what = 2; //消息的标识
                msg2.obj = "B"; // 消息的存放
                // b. 通过Handler发送消息到其绑定的消息队列
                workHandler.sendMessage(msg2);
                break;
            case R.id.button3:
                // 作用：退出消息循环
                mHandlerThread.quit();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workHandler.removeCallbacksAndMessages(null);
    }
}
