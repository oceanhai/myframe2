package com.wuhai.myframe2.ui.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Enventbus3Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Enventbus3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_enventbus3);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                Enventbus2Activity.startActivity(this);
                break;
        }
    }

    @Subscribe
    public void showEvnent1(BaseEvent baseEvent){
        Log.e(EnventbusActivity.TAG, "showEvnent1");
        String event = baseEvent.getEventStr();
        switch (event){
            case "event1":
                Log.e(EnventbusActivity.TAG, "Enventbus3Activity收到event1事件");
                break;
            case "event2":
                Log.e(EnventbusActivity.TAG, "Enventbus3Activity收到event2事件");
                break;
            case "event3":
                Log.e(EnventbusActivity.TAG, "Enventbus3Activity收到event3事件");
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
