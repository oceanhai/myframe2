package com.wuhai.myframe2.ui.asynctask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncTaskActivity extends BaseActivity2 implements View.OnClickListener {


    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.imageview01)
    ImageView imageview01;
    @BindView(R.id.imageview02)
    ImageView imageview02;
    @BindView(R.id.imageview03)
    ImageView imageview03;
    @BindView(R.id.imageview04)
    ImageView imageview04;
    @BindView(R.id.imageview05)
    ImageView imageview05;
    @BindView(R.id.imageview06)
    ImageView imageview06;

    private static final String TAG = AsyncTaskActivity.class.getSimpleName();

    private String imageviewUrl1 = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    private String imageviewUrl2 = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2352764292,448478286&fm=80&w=179&h=119&img.JPEG";
//    private Executor exec = new ThreadPoolExecutor(15, 200, 10,
//            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private Executor exec = new ThreadPoolExecutor(3, 5, 10,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AsyncTaskActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_async_task);
        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void init() {
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                new MyAsyncTask().execute(imageviewUrl1, "imageview01");
                new MyAsyncTask().execute(imageviewUrl2, "imageview02");
                new MyAsyncTask().execute(imageviewUrl2, "imageview03");
                new MyAsyncTask().execute(imageviewUrl2, "imageview04");
                new MyAsyncTask().execute(imageviewUrl2, "imageview05");
                new MyAsyncTask().execute(imageviewUrl2, "imageview06");
                //没多大意义，一直排队执行而已
                for(int x=0;x<200;x++){
                    new MyAsyncTask().execute(imageviewUrl2, "imageview06");
                }
                break;
            case R.id.btn02:
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl1, "imageview01");
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl2, "imageview02");
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl2, "imageview03");
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl2, "imageview04");
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl2, "imageview05");
                new MyAsyncTask().executeOnExecutor(exec, imageviewUrl2, "imageview06");
                /**
                 * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，
                 * 并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。
                 * 也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、
                 * 最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务
                 * （抛出RejectedExecutionException）。
                 *
                 * java.util.concurrent.RejectedExecutionException: Task android.os.AsyncTask$3@df839d3
                 * rejected from java.util.concurrent.ThreadPoolExecutor@811910[Running, pool size = 5,
                 * active threads = 5, queued tasks = 10, completed tasks = 0]
                 */
//                for(int x=0;x<100;x++){
//                    new MyAsyncTask().executeOnExecutor(exec, imageviewUrl2, "imageview06");
//                }
                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private String flag = null;

        @Override
        protected Bitmap doInBackground(String... params) {
            String urlStr = params[0];
            flag = params[1];
            //TODO 访问网络获取数据
            URL url = null;
            try {
                url = new URL(urlStr);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                printHeadInfo(conn);
                conn.connect();

                if (conn.getResponseCode() == 200) {
                    printHeadInfo(conn);
                    InputStream inputStream = conn.getInputStream();
                    try {//TODO 卧槽 你着还睡了5000毫秒啊 我说怎么这么慢呢
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return BitmapFactory.decodeStream(inputStream);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if ("imageview01".equals(flag)) {
                imageview01.setImageBitmap(result);
            } else if ("imageview02".equals(flag)) {
                imageview02.setImageBitmap(result);
            } else if ("imageview03".equals(flag)) {
                imageview03.setImageBitmap(result);
            } else if ("imageview04".equals(flag)) {
                imageview04.setImageBitmap(result);
            } else if ("imageview05".equals(flag)) {
                imageview05.setImageBitmap(result);
            } else if ("imageview06".equals(flag)) {
                imageview06.setImageBitmap(result);
            }
//            Bitmap bitmap = imageview01.getDrawingCache();
//            if(bitmap == null){
//                imageview01.setImageBitmap(result);
//            }else{
//                imageview02.setImageBitmap(result);
//            }
        }

    }

    private void printHeadInfo(HttpsURLConnection conn) {
        Log.e(TAG, "ContentEncoding:" + conn.getContentEncoding());
        Log.e(TAG, "ContentLength:" + conn.getContentLength());
        Log.e(TAG, "ContentType:" + conn.getContentType());
        Log.e(TAG, "Date:" + conn.getDate());
        Log.e(TAG, "Expiration:" + conn.getExpiration());
        Log.e(TAG, "Modified:" + conn.getLastModified());
    }
}
