package com.wuhai.myframe2.ui.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.bean.UserDetailEntity;
import com.wuhai.myframe2.contract.IRetrofitRxJavaRxLifecycleContract;
import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.myframe2.presenter.RetrofitRxJavaRxLifecyclePresenter;
import com.wuhai.myframe2.ui.base.BaseRxActivity;
import com.wuhai.myframe2.ui.retrofit.bean.BannerResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Retrofit+RxJava+RxLifecycle
 */
public class RetrofitRxJavaRxLifecycleActivity extends BaseRxActivity implements View.OnClickListener, IRetrofitRxJavaRxLifecycleContract.View {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.tv01)
    TextView tv01;

    RetrofitRxJavaRxLifecyclePresenter presenter = null;

    private static final String TAG = "retrofitRx";
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.tv02)
    TextView tv02;

    @BindView(R.id.btn03)
    Button btn03;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RetrofitRxJavaRxLifecycleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_retrofit_rxjava_rxlifecycle);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);

        presenter = new RetrofitRxJavaRxLifecyclePresenter(this);
    }

    @Override
    public void setStatistical() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                presenter.activityhome(0);
                break;
            case R.id.btn02:
                presenter.detail(null);
                break;
            case R.id.btn03:
                new ServiceProvider().getBanners(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:"+e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        BannerResult result = (BannerResult) o;
                        result.getData();
                        Log.e(TAG, "onNext:"+result.toString());
                    }
                },getLifecycleTransformer());
                break;
        }
    }

    @Override
    public void setActivityhome(ActivityHomeEntity result) {
        if (result == null) {
            return;
        }

        if (result.getTodayActivity() != null) {
            tv01.append("" + result.getTodayActivity().getGoodsName());
        }
    }

    @Override
    public void setUserDetail(UserDetailEntity entity) {
        if(entity == null){
            return;
        }
        tv02.append(entity.toString());
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
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

}
