package com.wuhai.myframe2.ui.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcViewModelBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：
 * 当使用Android中的androidx.lifecycle:lifecycle-viewmodel和androidx.lifecycle:lifecycle-livedata库时，
 * 通常是为了在ViewModel和LiveData之间建立联系，以便在应用程序组件
 * （如Activity和Fragment）的生命周期变化时保持数据的一致性和更新。
 * 这种结合使用的例子通常可以在MVC（Model-View-Controller）或MVVM（Model-View-ViewModel）架构中看到。
 */
public class ViewModelActivity extends BaseActivity {

    private AcViewModelBinding binding;

    private CounterViewModel counterViewModel;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ViewModelActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
        initData();
    }

    private void initData() {
        // 获取CounterViewModel的实例
        counterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        // 观察LiveData，当计数器的值改变时更新UI
        counterViewModel.getCounterLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer counterValue) {
                binding.counterTextView.setText(String.valueOf(counterValue));
            }
        });


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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_view_model);
    }

    private void setListener() {
        binding.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 增加计数器的值
                counterViewModel.incrementCounter();
            }
        });
    }
}
