package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：Lottie动画
 * http://airbnb.io/lottie/#/android
 */
public class LottieActivity extends BaseActivity {

    private LottieAnimationView animationView;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LottieActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_lottie);
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
        animationView = (LottieAnimationView)findViewById(R.id.animation_view);

        //远程网络加载
//        animationView.setAnimationFromUrl("http://39.99.156.40:8499/group1/M00/00/0C/J2OcKF80-YqAGgoLAAJJtoRsB-w50.json");
        animationView.setAnimationFromUrl("http://cdn.trojx.me/blog_raw/lottie_data_edit.json");
//        loadUrl();
    }

    private void setListener() {
    }

//    private void loadUrl() {
//
//        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .build();
//        Request request = new Request.Builder()
//                .url("http://39.99.156.40:8499/group1/M00/00/0C/J2OcKF80-YqAGgoLAAJJtoRsB-w50.json")
//                .build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "onFailure");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String resutlt = response.body().string();
//                Log.e(TAG, "response.body():" + resutlt);
//                try {
//                    JSONObject json = new JSONObject(resutlt);
//                    LottieComposition.Factory.fromJson(getResources(), json, new OnCompositionLoadedListener() {
//                        @Override
//                        public void onCompositionLoaded(LottieComposition composition) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    setComposition(composition);
//                                }
//                            });
//                        }
//                    });
//                } catch (JSONException e) {
//                }
//
//            }
//        });
//    }
    private  void setComposition(LottieComposition composition){
        animationView.setProgress(0);
        animationView.loop(true);
        animationView.setComposition(composition);
        animationView.playAnimation();
    }
}
