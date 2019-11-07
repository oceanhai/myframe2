package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.contract.IHomeContract;
import com.wuhai.myframe2.presenter.HomePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetrofitNetworkRequestActivity extends AppCompatActivity implements View.OnClickListener, IHomeContract.View {

    @BindView(R.id.btn01)
    Button btn01;

    HomePresenter presenter = null;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RetrofitNetworkRequestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_retrofit_network_request);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);

        presenter = new HomePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                presenter.activityhome(0);
                break;
        }
    }

    @Override
    public void setActivityhome(ActivityHomeEntity result) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dimssLoading() {

    }
}
