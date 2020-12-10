package com.wuhai.myframe2.ui.study;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcSnackBarBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：SnackBar
 */
public class SnackBarActivity extends BaseActivity {

    private AcSnackBarBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SnackBarActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_snack_bar);
    }

    private void setListener() {
    }

    public void showSnackBar(View view){

        //LENGTH_INDEFINITE：无穷
        Snackbar snackbar = Snackbar.make(view,"您的Wifi已经开启！",Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SnackBarActivity.this, "确定啦", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                Toast.makeText(SnackBarActivity.this, "SnackBar消失了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShown(Snackbar snackbar) {
                Toast.makeText(SnackBarActivity.this, "SnackBar出现了", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setActionTextColor(Color.BLUE);
        snackbar.setDuration(3000);//Snackbar.LENGTH_INDEFINITE 下 要自己定以时间 毫秒值
        snackbar.show();

    }
}
