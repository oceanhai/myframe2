package com.wuhai.myframe2.ui.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Enventbus2Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.btn03)
    Button btn03;
    @BindView(R.id.btn04)
    Button btn04;
    @BindView(R.id.btn05)
    Button btn05;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Enventbus2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_enventbus2);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                EventBus.getDefault().post(new BaseEvent("event1"));
                break;
            case R.id.btn02:
                EventBus.getDefault().post(new BaseEvent("event2"));
                break;
            case R.id.btn03:
                EventBus.getDefault().post(new BaseEvent("event3"));
                break;
            case R.id.btn04:
                List<String> mdata = new ArrayList<>();
                mdata.add("lufei1");
                mdata.add("lufei2");
                mdata.add("lufei3");
                EventBus.getDefault().post(new ListEvent("listevent", mdata));
                break;
            case R.id.btn05:
                EventBus.getDefault().post(new Event1());
                break;
        }
    }
}
