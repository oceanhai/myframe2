package com.wuhai.myframe2.ui.rxbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：RxBus 接收界面
 */
public class RxBusAcceptActivity extends BaseActivity {

    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.btn03)
    Button btn03;//TODO 没用到

    private Subscription subscription1;
    private Subscription subscription2;
    private Subscription subscription3;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RxBusAcceptActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_rx_bus_accept);
        ButterKnife.bind(this);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {

        subscription1 = RxBus.getDefault()
                .toObservable(String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tv01.append(s);
                    }
                });

        subscription2 = RxBus.getDefault()
                .toObservable(Event1.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Event1>() {
                    @Override
                    public void call(Event1 event1) {
                        tv02.append("\n" + event1.toString());
                    }
                });

        /**
         * 不过滤
         * PublishSubject仅会向Observer释放在订阅之后Observable释放的数据
         */
        subscription3 = RxBus.getDefault().getSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if(o instanceof String){
                            tv03.append("\n string:"+o.toString());
                        }else if(o instanceof Event1){
                            tv03.append("\n event1:"+((Event1)o).toString());
                        }
                    }
                });

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBusPostActivity.startActivity(RxBusAcceptActivity.this);
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "线程名:" + Thread.currentThread().getName());
                        RxBus.getDefault().post(new Event1(new Random().nextInt(100), "msg"));
                    }
                }).start();
            }
        });
    }

    private void setListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscription1 != null && !subscription1.isUnsubscribed()) {
            subscription1.unsubscribe();
        }
        if (subscription2 != null && !subscription2.isUnsubscribed()) {
            subscription2.unsubscribe();
        }
        if (subscription3 != null && !subscription3.isUnsubscribed()) {
            subscription3.unsubscribe();
        }
    }

    class Event1 {
        private int code;
        private String msg;

        public Event1(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Event1{" +
                    "code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
