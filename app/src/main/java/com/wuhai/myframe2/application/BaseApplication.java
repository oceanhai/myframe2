package com.wuhai.myframe2.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wuhai.retrofit.retrofit.BaseApi;
import com.wuhai.retrofit.retrofit.NetProvider;
import com.wuhai.retrofit.retrofit.RequestHandler;

import java.lang.reflect.Type;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 应用application
 * Created by fanchang on 2017/2/14.
 */
public class BaseApplication extends Application {

    //上下文环境
    public static BaseApplication application;
    public static Context context;
    //handler
    private static Handler handler;

    //获取主线程的id
    private static int mainThreadId;
    //轮循器
    private static Looper mainThreadLooper;
    //获取当前线程对象
    private static Thread mainThread;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = this;
        handler = new Handler();
        //获取当前线程的id
        mainThreadId = android.os.Process.myTid();
        //主线程轮训器对象
        mainThreadLooper = getMainLooper();
        mainThread = Thread.currentThread();

        initNetRequest();
    }

    /**
     * 初始化网络请求配置
     */
    private void initNetRequest() {
        BaseApi.INSTANCE.registerProvider(new NetProvider() {
            @Override
            public String configBaseUrl() {
                return "http://qjj.test01.qiandaodao.com/";
            }

            @Override
            public Converter.Factory configConverterFactory() {
                return GsonConverterFactory.create(buildGson());
            }

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
//                RequestHandler myRequestHandler = new MyRequestHandler.Builder()
//                        .addGetParam(Common.Constant.TOKEN, AccountManager.getInstance().getToken())
//                        .addGetParam(Common.Constant.ACCESS_TOKEN, SettingManager.getInstance().getAccessToken())
//                        .addGetParam(Common.Constant.CV, AppUtils.getVersionName())
//                        .addGetParam(Common.Constant.V, WMsApi.API_VERSION)//api版本
//                        .addGetParam(Common.Constant.APP_KEY, WMsApi.APP_KEY)//服务端分配
////                        .addGetParam("test", "1")
//                        .addGetParam(Common.Constant.CLIENT, WMsApi.CLIENT)
////                        .addPostParam("client", "")
//                        .build();
//                return myRequestHandler;
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public int configSuccessCode() {
                return 0;
            }
        });

        if (true) {
            Stetho.initializeWithDefaults(this);
        }
    }

    //======================================= 处理json中个别字段和bean中对应字段 类型不一致导致报错的问题 ===========================================
    GsonBuilder gson;

    public Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
                    .registerTypeAdapter(String.class, new StringDefaultAdapter());
//                    .registerTypeAdapter(JsonObject.class, new JsonObjectDefaultAdapter());

        }
        return gson.create();
    }

    // int 类型默认为0
    public class IntegerDefaultAdapter implements JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            Integer jsonVal = -1;
            try {
                jsonVal = json.getAsInt();
            } catch (Exception e) {
                e.printStackTrace();
                return jsonVal;
            }
            return jsonVal;
        }
    }

    public class StringDefaultAdapter implements JsonDeserializer<String> {
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String jsonVal = "";
            try {
                jsonVal = json.getAsString();
            } catch (Exception e) {
                e.printStackTrace();
                return jsonVal;
            }
            return jsonVal;
        }
    }


}
