package com.wuhai.myframe2.ui.dagger2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

import javax.inject.Inject;

/**
 * 作者 wh
 * 
 * 创建日期 2021/11/3 16:43
 * 
 * 描述：mvp 结合dagger
 *
 *  https://www.jianshu.com/p/2cd491f0da01      可以去了解，但最后设计mvp没有太过多讲解
 *
 *  TODO 忘记了 这个类结合mvp 是参考哪个文章的了 还是自己根据 dagger试着用在mvp所写的研究
 */
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
        Log.e(TAG, "结果 updateUI 执行了---1,"+Thread.currentThread().getName());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "结果 updateUI 执行了---2,"+Thread.currentThread().getName());
                Toast.makeText(Dagger2Activity.this, "结果 updateUI 执行了", Toast.LENGTH_LONG ).show();
            }
        });

    }
}
