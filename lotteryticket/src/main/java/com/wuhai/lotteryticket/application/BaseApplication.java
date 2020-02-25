package com.wuhai.lotteryticket.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wuhai.lotteryticket.BuildConfig;
import com.wuhai.lotteryticket.config.network.APIConstants;
import com.wuhai.lotteryticket.utils.LogProxy;
import com.wuhai.retrofit.retrofit.BaseApi;
import com.wuhai.retrofit.retrofit.NetProvider;
import com.wuhai.retrofit.retrofit.RequestHandler;

import java.lang.reflect.Type;
import java.util.Locale;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;
import me.jessyan.autosize.utils.LogUtils;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApplication extends Application {

    public static final String TAG = "BaseApplication";

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    public static BaseApplication getInstance(){
        return instance;
    }

    private void init() {

        //屏幕适配方案
        initScreenAdaptation();

        //初始化网络请求配置
        initNetRequest();

        //用google 翻墙
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            LogProxy.e("lottery", "Stetho init");
        }

        //register  Application 生命周期  头条屏幕适配方案用到相关知识点
        registerLife();
    }

    private void registerLife() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LogProxy.e(TAG, "onActivityCreated");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                LogProxy.e(TAG, "onActivityStarted");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                LogProxy.e(TAG, "onActivityResumed");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                LogProxy.e(TAG, "onActivityPaused");
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                LogProxy.e(TAG, "onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                LogProxy.e(TAG, "onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                LogProxy.e(TAG, "onActivityDestroyed");
            }
        });
    }

    private void initNetRequest() {
        BaseApi.INSTANCE.registerProvider(new NetProvider() {
            @Override
            public String configBaseUrl() {
                return APIConstants.NetWorkBaseURL;
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
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 20;
            }

            @Override
            public long configReadTimeoutMills() {
                return 30;
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

        if (BuildConfig.DEBUG) {
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

    private void initScreenAdaptation() {
        //当 App 中出现多进程, 并且您需要适配所有的进程, 就需要在 App 初始化时调用 initCompatMultiProcess()
        //在 Demo 中跳转的三方库中的 DefaultErrorActivity 就是在另外一个进程中, 所以要想适配这个 Activity 就需要调用 initCompatMultiProcess()
        AutoSize.initCompatMultiProcess(this);
        /**
         * 以下是 AndroidAutoSize 可以自定义的参数, {@link AutoSizeConfig} 的每个方法的注释都写的很详细
         * 使用前请一定记得跳进源码，查看方法的注释, 下面的注释只是简单描述!!!
         */
        AutoSizeConfig.getInstance()

                //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
                //如果没有这个需求建议不开启
                .setCustomFragment(true)

                //是否屏蔽系统字体大小对 AndroidAutoSize 的影响, 如果为 true, App 内的字体的大小将不会跟随系统设置中字体大小的改变
                //如果为 false, 则会跟随系统设置中字体大小的改变, 默认为 false
                .setExcludeFontScale(true)

                //屏幕适配监听器
                .setOnAdaptListener(new onAdaptListener() {
                    @Override
                    public void onAdaptBefore(Object target, Activity activity) {
                        //使用以下代码, 可以解决横竖屏切换时的屏幕适配问题
                        //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                        //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
//                        AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
//                        AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptBefore!", target.getClass().getName()));
                    }

                    @Override
                    public void onAdaptAfter(Object target, Activity activity) {
                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptAfter!", target.getClass().getName()));
                    }
                })

                //是否打印 AutoSize 的内部日志, 默认为 true, 如果您不想 AutoSize 打印日志, 则请设置为 false
//                .setLog(false)

        //是否使用设备的实际尺寸做适配, 默认为 false, 如果设置为 false, 在以屏幕高度为基准进行适配时
        //AutoSize 会将屏幕总高度减去状态栏高度来做适配
        //设置为 true 则使用设备的实际屏幕高度, 不会减去状态栏高度
//                .setUseDeviceSize(true)

        //是否全局按照宽度进行等比例适配, 默认为 true, 如果设置为 false, AutoSize 会全局按照高度进行适配
//                .setBaseOnWidth(false)

        //设置屏幕适配逻辑策略类, 一般不用设置, 使用框架默认的就好
//                .setAutoAdaptStrategy(new AutoAdaptStrategy())
        ;
    }

}
