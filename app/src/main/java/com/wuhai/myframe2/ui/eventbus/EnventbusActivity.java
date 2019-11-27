package com.wuhai.myframe2.ui.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnventbusActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = EnventbusActivity.class.getSimpleName();

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.textview01)
    TextView textview01;
    @BindView(R.id.btn03)
    Button btn03;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, EnventbusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_enventbus);
        ButterKnife.bind(this);

        Log.e(TAG, "EventBus.getDefault()地址:" + EventBus.getDefault().hashCode());
        EventBus.getDefault().register(this);

        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }

    @Subscribe
    public void showEvnent1(BaseEvent baseEvent) {
        Log.e(TAG, "showEvnent1");
        String event = baseEvent.getEventStr();
        switch (event) {
            case "event1":
                Log.e(TAG, "EnventbusActivity收到event1事件");
                textview01.setText(event);
                break;
            case "event2":
                Log.e(TAG, "EnventbusActivity收到event2事件");
                textview01.setText(event);
                break;
            case "event3":
                Log.e(TAG, "EnventbusActivity收到event3事件");
                textview01.setText(event);
                break;
        }

    }

    @Subscribe
    public void showEvent2(BaseEvent baseEvent) {
        Log.e(TAG, "showEvnent2");
        String event = baseEvent.getEventStr();
        switch (event) {
            case "listevent":
                Log.e(TAG, "showEvent2收到listevent事件");
                ListEvent listEvent = (ListEvent) baseEvent;
                StringBuilder stringBuilder = new StringBuilder();
                List<String> mdata = listEvent.getmData();
                for (int x = 0; x < mdata.size(); x++) {
                    stringBuilder.append(mdata.get(x)).append(" ");
                }
                textview01.setText(stringBuilder.toString());
                break;
        }
    }

    @Subscribe
    public void showEvent3(Event1 event1) {
        Log.e(TAG, "非继承base event");
    }

    @Subscribe
    public void showEvent4(ListEvent baseEvent) {
        Log.e(TAG, "showEvnent4");
        String event = baseEvent.getEventStr();
        switch (event) {
            case "listevent":
                Log.e(TAG, "showEvent4收到listevent事件");
                StringBuilder stringBuilder = new StringBuilder();
                List<String> mdata = baseEvent.getmData();
                for (int x = 0; x < mdata.size(); x++) {
                    stringBuilder.append(mdata.get(x)).append(" ");
                }
                textview01.setText(stringBuilder.toString());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01://左右fragment之间使用
                Enventbus1Activity.startActivity(this);
                break;
            case R.id.btn02://Enventbus3Activity界面
                Enventbus3Activity.startActivity(this);
                break;
            case R.id.btn03://启动一个在单独进程的页面
                Enventbus4Activity.startActivity(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
