package com.wuhai.myframe2.ui.theme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcThemeMainBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：ThemeMain
 */
public class ThemeMainActivity extends BaseActivity implements View.OnClickListener {

    private AcThemeMainBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThemeMainActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_theme_main);
    }

    private void setListener() {
        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
        binding.btn03.setOnClickListener(this);
        binding.btn04.setOnClickListener(this);
        binding.btn05.setOnClickListener(this);
        binding.btn06.setOnClickListener(this);
        binding.btn07.setOnClickListener(this);
        binding.btn08.setOnClickListener(this);
        binding.btn09.setOnClickListener(this);
        binding.btn10.setOnClickListener(this);
        binding.btn11.setOnClickListener(this);
        binding.btn12.setOnClickListener(this);
        binding.btn13.setOnClickListener(this);
        binding.btn14.setOnClickListener(this);
        binding.btn15.setOnClickListener(this);
        binding.btn16.setOnClickListener(this);
        binding.btn17.setOnClickListener(this);
        binding.btn18.setOnClickListener(this);
        binding.btn19.setOnClickListener(this);
        binding.btn20.setOnClickListener(this);
        binding.btn21.setOnClickListener(this);
        binding.btn22.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                ThemeActivity.startActivity(this, 1);
                break;
            case R.id.btn02:
                ThemeActivity.startActivity(this, 2);
                break;
            case R.id.btn03:
                ThemeActivity.startActivity(this, 3);
                break;
            case R.id.btn04:
                ThemeActivity.startActivity(this, 4);
                break;
            case R.id.btn05:
                ThemeActivity.startActivity(this, 5);
                break;
            case R.id.btn06:
                ThemeActivity.startActivity(this, 6);
                break;
            case R.id.btn07:
                ThemeActivity.startActivity(this, 7);
                break;
            case R.id.btn08:
                ThemeActivity.startActivity(this, 8);
                break;
            case R.id.btn09:
                ThemeActivity.startActivity(this, 9);
                break;
            case R.id.btn10:
                ThemeActivity.startActivity(this, 10);
                break;
            case R.id.btn11:
                ThemeActivity.startActivity(this, 11);
                break;
            case R.id.btn12:
                ThemeActivity.startActivity(this, 12);
                break;
            case R.id.btn13:
                ThemeActivity.startActivity(this, 13);
                break;
            case R.id.btn14:
                ThemeActivity.startActivity(this, 14);
                break;
            case R.id.btn15:
                ThemeActivity.startActivity(this, 2, 1);
                break;
            case R.id.btn16:
                ThemeActivity.startActivity(this, 2, 2);
                break;
            case R.id.btn17:
                ThemeActivity.startActivity(this, 2, 3);
                break;
            case R.id.btn18:
                ThemeActivity.startActivity(this, 2, 4);
                break;
            case R.id.btn19:
                ThemeActivity.startActivity(this, 2, 5);
                break;
            case R.id.btn20:
                ThemeActivity.startActivity(this, 2, 6);
                break;
            case R.id.btn21:
                ThemeActivity.startActivity(this, 1, 7);
                break;
            case R.id.btn22:
                ThemeActivity.startActivity(this, 1, 8);
                break;
        }
    }
}
