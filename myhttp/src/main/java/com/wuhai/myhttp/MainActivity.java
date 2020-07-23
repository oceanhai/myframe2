package com.wuhai.myhttp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myhttp.bean.RequestBean;
import com.wuhai.myhttp.bean.ResponseBean;
import com.wuhai.myhttp.http.HttpUtils;
import com.wuhai.myhttp.http.IDataListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.request1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.request1:
                String url = "";
                RequestBean requestBean = new RequestBean("","");
                HttpUtils.sendJsonRequest(url, requestBean, ResponseBean.class,
                        new IDataListener<ResponseBean>() {
                            @Override
                            public void onSuccess(ResponseBean responseBean) {
                                Log.e("myhttp", responseBean.toString());
                            }

                            @Override
                            public void onFailure() {

                            }
                        });
                break;
        }
    }
}
