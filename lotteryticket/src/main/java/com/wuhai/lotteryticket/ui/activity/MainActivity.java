package com.wuhai.lotteryticket.ui.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.AcMainBinding;
import com.wuhai.lotteryticket.presenter.HomePresenter;
import com.wuhai.lotteryticket.ui.base.BaseActivity;
import com.wuhai.lotteryticket.utils.StatusBarUtil;

public class MainActivity extends BaseActivity {

    private AcMainBinding binding;

    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_main);//※注意 这是把布局add到父容器里

        //TODO DataBinding和BaseActivity多状态布局使用  冲突
        //这里我们直接继承 BaseActivity，但 其他界面ac,如果想使用基类多状态布局，到时候再研究吧
        binding = DataBindingUtil.setContentView(this, R.layout.ac_main);

        //AppCompatActivity提供ActionBar,但我以前做开发还真没怎么用过ActionBar
        //所以如果不用ActionBar 直接继承FragmentActivity就行
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

    }

    @Override
    public void setStatistical() {

    }

    /**
     * 沉浸式 开发       系统栏
     */
    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

}
