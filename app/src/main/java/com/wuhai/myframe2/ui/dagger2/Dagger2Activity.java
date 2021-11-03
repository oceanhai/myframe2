package com.wuhai.myframe2.ui.dagger2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import javax.inject.Inject;


public class Dagger2Activity extends AppCompatActivity implements MainContract.View {

    private final String TAG = getClass().getSimpleName();

    @Inject
    MainPresenter presenter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Dagger2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        DaggerMainPresenterComponent.builder().
                mainPresenterModule(new MainPresenterModule(this)).
                build().inject(this);

        presenter.getData();
    }

    @Override
    public void updateUI() {
        Log.e(TAG, "结果 updateUI 执行了");
    }
}
