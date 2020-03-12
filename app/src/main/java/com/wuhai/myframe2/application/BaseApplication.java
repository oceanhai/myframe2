package com.wuhai.myframe2.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.igexin.sdk.PushManager;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.wuhai.myframe2.business.getui.DemoIntentService;
import com.wuhai.myframe2.business.getui.DemoPushService;
import com.wuhai.myframe2.config.Config;
import com.wuhai.myframe2.ui.retrofit.base.MyRequestHandler;
import com.wuhai.myframe2.utils.DeviceUtil;
import com.wuhai.retrofit.retrofit.BaseApi;
import com.wuhai.retrofit.retrofit.NetProvider;
import com.wuhai.retrofit.retrofit.RequestHandler;
import com.xywy.component.datarequest.network.RequestManager;

import java.io.File;
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

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //网络请求框架初始化
        initNetRequest();
        //个推
        initGeTui();

        //XUpdate 轻量级升级框架 初始化
        XUpdateInit();

        //地址选择框架 数据初始化
        initAddressData();

        //Fresco init 并指定缓存地址
        initFresco(this);
        //Fresco init 默认
//        Fresco.initialize(this);

        //寻医问药 网络请求框架 初始化 网络请求框架要初始化
        initDataRequest();
    }

    private void initDataRequest() {
        /**
         * volley 初始化
         */
        RequestManager.init(this);
    }

    /**
     * Fresco init 并指定缓存地址
     * @param context
     */
    private static void initFresco(Context context) {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setMainDiskCacheConfig(getMainDiskCacheConfig(context))
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(context, config);
    }

    private static DiskCacheConfig getMainDiskCacheConfig(final Context context) {
        String baseCacheLocation = DeviceUtil.getBaseFilePath(context);
        File cacheDir = new File(baseCacheLocation + File.separator + Config.IMAGE_CACHE_DIR_NAME);
        return DiskCacheConfig.newBuilder(null)
                .setBaseDirectoryPath(cacheDir)
                .setBaseDirectoryName("images")
                .setMaxCacheSize(50 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(15 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(5 * ByteConstants.MB)
                .setCacheErrorLogger(new CacheErrorLogger() {
                    @Override
                    public void logError(CacheErrorCategory cacheErrorCategory, Class<?> clazz, String message, Throwable throwable) {
                    }
                })
                .build();
    }


    private void initAddressData() {
        /**
         * 预先加载三级列表显示省市区的数据
         */
        CityListLoader.getInstance().loadProData(this);
    }

    private void XUpdateInit() {
    }

    private void initGeTui() {
        // DemoPushService 为【步骤2.6】自定义的推送服务
        PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
        // DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(getApplicationContext(), DemoIntentService.class);
    }

    public static BaseApplication getApplication() {
        return application;
    }

    /**
     * 初始化网络请求配置
     * registerProvider 设置初始化网络配置
     * 必须在其它BaseApi.INSTANCE.getProvider使用前初始化
     */
    private void initNetRequest() {
        BaseApi.INSTANCE.registerProvider(new NetProvider() {
            @Override
            public String configBaseUrl() {
//                return "http://qjj.test01.qiandaodao.com/";//趣竞价 原测试线 服务器到期了
                return "https://api.qujingjia.com/";
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
                RequestHandler myRequestHandler = new MyRequestHandler.Builder()
                        .addGetParam("token", "61FB3EC035FD9AC8EA1B18C4EFDEDB0E")
                        .build();
                return myRequestHandler;
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
            Integer jsonVal = 0;
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
