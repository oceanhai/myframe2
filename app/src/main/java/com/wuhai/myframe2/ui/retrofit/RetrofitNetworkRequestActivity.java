package com.wuhai.myframe2.ui.retrofit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.contract.IHomeContract;
import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.myframe2.presenter.HomePresenter;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RequestNetCallBack;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RootResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * android lib retrofitnetworkrequest(捎货帮 网络请求框架 )
 * 的测试ac
 */
public class RetrofitNetworkRequestActivity extends AppCompatActivity implements View.OnClickListener, IHomeContract.View {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.tv01)
    TextView tv01;

    HomePresenter presenter = null;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.tv02)
    TextView tv02;

    private static final String TAG = "retrofitRx";

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
        btn02.setOnClickListener(this);

        presenter = new HomePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                presenter.activityhome(0);
                break;
            case R.id.btn02:
                getRx();
                break;
        }
    }



    /**
     * retrofit + rxjava
     */
    private void getRx() {
        new ServiceProvider().activityhomeRx(new RequestNetCallBack<RootResponse<ActivityHomeEntity>>() {
            @Override
            public void onSuccess(RootResponse<ActivityHomeEntity> dataResponse) {
                Log.e(TAG, "code = "+dataResponse.getCode());
                Log.e(TAG, "success = "+dataResponse.isSuccess());
                Log.e(TAG, "message = "+dataResponse.getMessage());
                Log.e(TAG, "result = "+dataResponse.getResult());

                if (dataResponse == null) {
                    return;
                }

                if (dataResponse.getResult().getTodayActivity() != null) {
                    tv02.append("" + dataResponse.getResult().getTodayActivity().getGoodsName());
                }
            }

//            /**
//             * TODO 这里如果重写 降不走默认的方法
//             * @param responseError
//             */
//            @Override
//            public void onFailure(ResponseError responseError) {
//                Log.e(TAG, "err = "+responseError.toString());
//            }

            @Override
            public Dialog baseGetLoadingDialog() {
                return null;
            }
        });
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
