package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.contract.IHomeContract;
import com.wuhai.myframe2.databinding.AcMvvmBinding;
import com.wuhai.myframe2.presenter.HomePresenter;
import com.wuhai.myframe2.viewmodel.ActivityHomeViewModel;

/**
 * android lib retrofitnetworkrequest(捎货帮 网络请求框架 )
 * 的测试ac
 * mvvm
 */
public class MvvmActivity extends AppCompatActivity implements View.OnClickListener, IHomeContract.View {

    private AcMvvmBinding binding;
    private HomePresenter presenter = null;
    private ActivityHomeViewModel viewModel;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MvvmActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_mvvm);

        binding = DataBindingUtil.setContentView(this, R.layout.ac_mvvm);

        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);

        presenter = new HomePresenter(this);

        //mvvm直接初始化 请求数据了
        viewModel = new ActivityHomeViewModel(this,binding);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01://走以前的网络请求
                presenter.activityhome(0);
                break;
            case R.id.btn02://变更绑定数据体  UI跟着变更
                viewModel.change();
                break;
        }
    }

    @Override
    public void setActivityhome(ActivityHomeEntity result) {
        if(result == null){
            return;
        }

        if(result.getTodayActivity() != null){
            binding.setHome(result);
//            binding.tv01.setText(""+result.getTodayActivity().getGoodsName());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dimssLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.onDestroy();
        }
    }
}
