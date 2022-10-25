package com.wuhai.myframe2.ui.okhttp3;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.GsonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Android OkHttp3 知识总结
 *
 */
public class OkHttpActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = "OkHttpActivity";

    @BindView(R.id.get)
    Button get;
    @BindView(R.id.post_string)
    Button postString;
    @BindView(R.id.show_info)
    TextView showInfo;
    @BindView(R.id.multipart)
    Button multipart;
    @BindView(R.id.post_json)
    Button postJson;
    @BindView(R.id.post_file)
    Button postFile;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.post_form_parameters)
    Button postFormParameters;

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showInfo.setText(msg.obj.toString());
//            ToastUtils.showShort(OkHttpActivity.this, "我是handler1");
        }
    };

    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(OkHttpActivity.this, "我是handler2", Toast.LENGTH_LONG).show();
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OkHttpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_ok_http);
        ButterKnife.bind(this);

        initListener();
    }

    @Override
    public void setStatistical() {

    }

    private void initListener() {
        get.setOnClickListener(this);
        postString.setOnClickListener(this);
        postJson.setOnClickListener(this);
        postFile.setOnClickListener(this);
        postFormParameters.setOnClickListener(this);
        multipart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get:
                okhttpGet();
                break;
            case R.id.post_string:
                okhttpPostString();
                break;
            case R.id.post_json:
                okhttpPostJson();
                break;
            case R.id.post_file:
                okhttpPostFile();
            case R.id.post_form_parameters:
                okhttpPostFormParameters();
                break;
            case R.id.multipart:
                okhttpMultipart();
                break;
        }
    }




    /**
     * get请求    TODO 测试OK
     */
    private void okhttpGet() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
//                .url("https://www.jianshu.com/p/3c082b26b1d8")
                .url("http://192.168.10.213:80/book.json")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String resultFail = e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("fail" + resultFail);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resutlt = response.body().string();

                //TODO 这里返回流，k4等一下图片或者文件下载流量需要计费，所以要走下载接口监测流
                // 说实话，这种不能直接通过服务器监测反而走接口方式降低了效率
//                response.body().byteStream()

                Log.v(TAG, "response.body():" + resutlt);
                Message message = new Message();
                message.obj = resutlt;
//                handler1.obtainMessage(1,message);//TODO 可删
//                handler1.sendMessage(message);

//                handler2.obtainMessage();//TODO 可删
//                handler2.sendMessage(message);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    /**
                     * 多个handler依然对应 同一个looper和MessageQueue
                     */
                    Log.v(TAG, "handler1.looper：" + handler1.getLooper() + ",looper.MessageQueue："
                            + handler1.getLooper().getQueue());
                    Log.v(TAG, "handler2.looper：" + handler2.getLooper() + ",looper.MessageQueue："
                            + handler2.getLooper().getQueue());
                }

                //或者
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("success" + resutlt.substring(0, 20));
                    }
                });
            }
        });

//        try {
//            Response response =call.execute();//TODO 阻塞方式 response处理结果
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * "application/x-www-form-urlencoded"，是默认的MIME内容编码类型，一般可以用于所有的情况，
     * 但是在传输比较大的二进制或者文本数据时效率低。
     * 这时候应该使用"multipart/form-data"。如上传文件或者二进制数据和非ASCII数据。
     */
    public static final MediaType MEDIA_TYPE_NORAML_FORM =
            MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

    //既可以提交普通键值对，也可以提交(多个)文件键值对。
    public static final MediaType MEDIA_TYPE_MULTIPART_FORM =
            MediaType.parse("multipart/form-data;charset=utf-8");

    //只能提交二进制，而且只能提交一个二进制，如果提交文件的话，只能提交一个文件,后台接收参数只能有一个，而且只能是流（或者字节数组）
    public static final MediaType MEDIA_TYPE_STREAM =
            MediaType.parse("application/octet-stream");

    public static final MediaType MEDIA_TYPE_TEXT =
            MediaType.parse("text/plain;charset=utf-8");

    public static final MediaType MEDIA_TYPE_JSON =
            MediaType.parse("application/json;charset=utf-8");

    /**
     * POST String
     */
    private void okhttpPostString() {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain;charset=utf-8");
        String str = "AndroidContent";
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_TEXT, str);
        Request requestPost = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String resultFail = e.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("fail" + resultFail);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("success" + result.substring(0, 60));
                    }
                });
            }
        });

    }

    /**
     * POST Json  TODO 测试OK
     */
    private void okhttpPostJson() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

        Map<String, String> map = new HashMap<>();
        map.put("token", "61FB3EC035FD9AC8EA1B18C4EFDEDB0E");//token 正确字段
//        map.put("token1","61FB3EC035FD9AC8EA1B18C4EFDEDB0E");//token1 错误字段
        String jsonStr = GsonUtils.getInstance().mapToJson(map);

        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonStr);
        Request requestPost = new Request.Builder()
                .url("http://qjj.test01.qiandaodao.com/userBillLading/ladinglist?")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String resultFail = e.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("post_json fail" + resultFail);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("post_json success:" + result);
                    }
                });
            }
        });
    }

    /**
     * POST File
     * TODO mmp tomcat启动后，只能模拟取数据，不能模拟上传数据
     */
    private void okhttpPostFile() {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
//        File file = new File("README.md");
        File file = new File(Environment.getExternalStorageDirectory() + "/Huawei/MagazineUnlock/" + "a.jpg");
        Request request = new Request.Builder()
//                .url("http://192.168.10.213:80/okHttpServer/fileUpload")
//                .url("http://192.168.10.213:80/")
                .url("http://192.168.10.213:8080/file/upload")//TODO 孙磊jar包
//                .url("http://192.168.10.213:8080/upload?")//TODO 孙磊jar包
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

//        try {
//            Response response = mOkHttpClient.newCall(request).execute();
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            System.out.println("post_json success:"+response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//            showInfo.setText("post_json fail:" + e.getMessage());
//        }

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String resultFail = e.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG,"PostFile fail" + resultFail);
                        showInfo.setText("PostFile fail" + resultFail);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        showInfo.setText("PostFile success" + result);
                        Log.e(TAG,"result="+result);
                        webView.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
                    }
                });
            }
        });
    }

    /**
     * POST Form Parameters
     */
    private void okhttpPostFormParameters() {

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String resultFail = e.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("fail" + resultFail);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo.setText("success" + result);
                    }
                });
            }
        });

    }

    /**
     * POST Stream
     */
    private void okhttpPostStream() {
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        File file = new File("README.md");
        FileInputStream fileInputStream1 = null;
        try {
            fileInputStream1 = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream finalFileInputStream = fileInputStream1;
        RequestBody requestBody1 = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                OutputStream outputStream = sink.outputStream();
                int length;
                byte[] buffer = new byte[1024];
                while ((length = finalFileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
            }
        };

        FileInputStream finalFileInputStream1 = fileInputStream1;
        RequestBody requestBody2 = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                int length;
                byte[] buffer = new byte[1024];
                while ((length = finalFileInputStream1.read(buffer)) != -1) {
                    sink.write(buffer, 0, length);
                }
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody1)
                .build();
    }

    /**
     * 文件+post
     */
    private void okhttpMultipart() {
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//
//        File file = new File(Environment.getExternalStorageDirectory(), "goldfallen.mp3");
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
//
//        RequestBody requestBody = new MultipartBuilder()
//                .type(MultipartBuilder.FORM)
//                .addPart(Headers.of("Content-Disposition", "form-data; name='username'"),
//                        RequestBody.create(null,"胖大海"))
//                .addPart(Headers.of("Content-Disposition","form-data;name='mFile'; filename='goldfallen.mp3'"),
//                        fileBody)
//                .build();
//        //http://localhost/okHttpServer/fileUpload
//        Request request = new Request.Builder()
//                .url("http://192.168.1.3:80/okHttpServer/fileUpload")
//                .post(requestBody)
//                .build();
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                L.e(TAG,e.toString());
//                final String resultFail = request.toString();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showInfo.setText("fail"+resultFail);
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                final String result = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showInfo.setText("success"+result);
//                    }
//                });
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler1.removeCallbacksAndMessages(null);
        handler2.removeCallbacksAndMessages(null);
    }
}
