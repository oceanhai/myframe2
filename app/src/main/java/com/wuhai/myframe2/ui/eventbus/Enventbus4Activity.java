package com.wuhai.myframe2.ui.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 单独一个新的进程
 */
public class Enventbus4Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Enventbus4Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_enventbus4);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01://我处在单独的一个进程里，现在post事件，看其他页面能收到吗
                /**
                 * 确认后的结果：
                 * eventbus 不能跨进程
                 * 它简化了应用程序内各个组件之间进行通信的复杂度，尤其是碎片之间进行通信的问题，
                 * 可以避免由于使用广播通信而带来的诸多不便。
                 */
                EventBus.getDefault().post(new BaseEvent("event4"));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
