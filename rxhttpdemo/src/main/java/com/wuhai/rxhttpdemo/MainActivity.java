package com.wuhai.rxhttpdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.OkHttpClient;
import rxhttp.RxHttp;
import rxhttp.wrapper.callback.IConverter;
import rxhttp.wrapper.converter.FastJsonConverter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void method01(){
        RxHttp.get("http://...")  //第一步, 通过get、postXxx、putXxx等方法，确定请求类型
                .asString()           //第二步, 通过asXxx系列方法，确定返回数据类型
                .subscribe(s -> {     //第三步, 订阅回调(此步骤同RxJava订阅观察者)
                    //请求成功
                }, throwable -> {
                    //请求失败
                });

//        RxHttp.get(String)              //get请求    参数拼接在url后面
//        RxHttp.head(String)             //head请求   参数拼接在url后面
//        RxHttp.postForm(String)         //post请求   参数以{application/x-www-form-urlencoded}形式提交
//        RxHttp.postJson(String)         //post请求   参数以{application/json; charset=utf-8}形式提交，发送Json对象
//        RxHttp.postJsonArray(String)    //post请求   参数以{application/json; charset=utf-8}形式提交，发送Json数组
//        RxHttp.putForm(String)          //put请求    参数以{application/x-www-form-urlencoded}形式提交
//        RxHttp.putJson(String)          //put请求    参数以{application/json; charset=utf-8}形式提交，发送Json对象
//        RxHttp.putJsonArray(String)     //put请求    参数以{application/json; charset=utf-8}形式提交，发送Json数组
//        RxHttp.patchForm(String)        //patch请求  参数以{application/x-www-form-urlencoded}形式提交
//        RxHttp.patchJson(String)        //patch请求  参数以{application/json; charset=utf-8}形式提交，发送Json对象
//        RxHttp.patchJsonArray(String)   //patch请求  参数以{application/json; charset=utf-8}形式提交，发送Json数组
//        RxHttp.deleteForm(String)       //delete请求 参数以{application/x-www-form-urlencoded}形式提交
//        RxHttp.deleteJson(String)       //delete请求 参数以{application/json; charset=utf-8}形式提交，发送Json对象
//        RxHttp.deleteJsonArray(String)  //delete请求 参数以{application/json; charset=utf-8}形式提交，发送Json数组

        /**
         * 添加参数/请求头
         */
        RxHttp.get("/service/...")       //发送get请求
                .add("key", "value")         //添加参数
                .addAll(new HashMap<>())     //通过Map添加多个参数
                .addHeader("deviceType", "android");     //添加请求头

        //postJson请求方法下会有更多addAll等方法可供调用
        RxHttp.postJson("/service/...") //发送post Json请求
                .addAll(new JsonObject())   //通过json对象添加多个参数
                .addAll("{\"height\":180,\"weight\":70}"); //通过json字符串添加多个参数

        //postForm请求方法下会有一系列addFile方法可供调用
        RxHttp.postForm("/service/...")  //发送post表单请求
                .addFile("file", new File("xxx/1.png")) //添加单个文件
                .addFile("fileList", new ArrayList<>()); //添加多个文件

        /**
         * 3.3.2、第二部曲：确定返回数据类型
         */
        //返回一个Student对象
        RxHttp.postForm("/service/...")  //发送post表单请求
                .add("key", "value")         //添加参数，可调用多次
                .asClass(Student.class)    //返回Student类型
                .subscribe(student -> {
                    //请求成功，这里就能拿到 Student对象
                }, throwable -> {
                    //请求失败
                });
        //返回Student对象列表
        RxHttp.postForm("/service/...")  //发送post表单请求
                .add("key", "value")         //添加参数，可调用多次
                .asList(Student.class)       //返回List<Student>类型
                .subscribe(students -> {
                    //请求成功，这里就能拿到 Student对象列表
                }, throwable -> {
                    //请求失败
                });
        //解析Response<T>类型数据
        //TODO asResponse 没这个方法
//        RxHttp.postForm("/service/...")   //发送post表单请求
//                .add("key", "value")          //添加参数，可调用多次
//                .asResponse(Student.class)    //返回Student类型
//                .subscribe(student -> {
//                    //请求成功，这里能拿到 Student对象
//                }, throwable -> {
//                    //请求失败
//                });
        //asResponseList(Class<T>)
        //TODO asResponseList 没这个方法
//        RxHttp.postForm("/service/...")   //发送post表单请求
//                .add("key", "value")          //添加参数，可调用多次
//                .asResponseList(Student.class)    //返回List<Student>类型
//                .subscribe(students -> {
//                    //请求成功，这里能拿到List<Student>列表对象
//                }, throwable -> {
//                    //请求失败
//                });
        //asResponsePageList(Class<T>)
//        RxHttp.postForm("/service/...")   //发送post表单请求
//                .add("key", "value")          //添加参数，可调用多次
//                .asResponsePageList(Student.class)    //返回PageList<Student>类型
//                .subscribe(pageList -> {
//                    //请求成功，这里能拿到PageList<Student>列表对象
//                    int totalPage = pageList.getTotalPage();   //总页数
//                    List<Student> students = pageList.getData();  //单页列表数据
//                }, throwable -> {
//                    //请求失败
//                });

//        RxHttp.postForm("/service/...")   //发送post表单请求
//                .add("key", "value")          //添加参数，可调用多次
//                .asResponse(Student.class)    //返回Student类型
//                .subscribe(student -> {
//                    //请求成功，这里能拿到 Student对象
//                }, (OnError) error -> {     //注意，这里要用OnError接口，其中error是一个ErrorInfo对象
//                    //失败回调
//                    //拿到code字段，此时就可以对code做判断，执行不同的业务逻辑
//                    int code = error.getErrorCode();
//                    String errorMsg = error.getErrorMsg()  //拿到msg字段
//                });

        /**
         * 3.3.3、第三部曲：订阅回调
         */
        //不处理任何回调
//        RxHttp.postForm("/service/...")   //发送post表单请求
//                .add("key", "value")          //添加参数，可调用多次
//                .asResponseList(Student.class)    //返回List<Student>类型
//                .subscribe();    //不订阅任何回调

        //仅订阅成功回调
//        RxHttp.postForm("/service/...")   //发送post表单请求
//                .add("key", "value")          //添加参数，可调用多次
//                .asResponseList(Student.class)    //返回List<Student>类型
//                .subscribe(students -> {
//                    //请求成功，这里能拿到List<Student>列表对象
//                });

        //订阅成功与失败回调
//        RxHttp.postForm("/service/...")   //发送post表单请求
//                .add("key", "value")          //添加参数，可调用多次
//                .asResponseList(Student.class)    //返回List<Student>类型
//                .subscribe(students -> {
//                    //请求成功，这里能拿到List<Student>列表对象
//                }, throwable -> {
//                    //请求失败
//                });

        //我们还可以订阅请求开始/结束的回调
//        RxHttp.get("/service/...")
//                .asString()
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(disposable -> {
//                    //请求开始，当前在主线程回调
//                })
//                .doFinally(() -> {
//                    //请求结束，当前在主线程回调
//                })
//                .as(RxLife.as(this))  //感知生命周期
//                .subscribe(s -> {
//                    //成功回调，当前在主线程回调
//                }, (OnError) error -> {
//                    //失败回调，当前在主线程回调
//                });

        /**
         * 3.4、初始化
         */
//        //设置debug模式，默认为false，设置为true后，发请求，过滤"RxHttp"能看到请求日志
//        RxHttp.setDebug(boolean debug)
//        //非必须,只能初始化一次，第二次将抛出异常
//        RxHttp.init(OkHttpClient okHttpClient)
//        //或者，调试模式下会有日志输出
//        RxHttp.init(OkHttpClient okHttpClient, boolean debug)

        /**
         * 3.5、公共参数/请求头
         */
//        RxHttp.setOnParamAssembly(new Function() {
//            @Override
//            public Param apply(Param p) { //此方法在子线程中执行，即请求发起线程
//                Method method = p.getMethod();
//                if (method.isGet()) {     //可根据请求类型添加不同的参数
//                } else if (method.isPost()) {
//                }
//                return p.add("versionName", "1.0.0")//添加公共参数
//                        .addHeader("deviceType", "android"); //添加公共请求头
//            }
//        });
        //如果希望某个请求不回调该接口，即不添加公共参数/请求头，
        // 则可以调用setAssemblyEnabled(boolean)方法，并传入false即可
        RxHttp.get("/service/...")       //get请求
                .setAssemblyEnabled(false)   //设置是否添加公共参数/头部，默认为true
                .asString()                  //返回字符串数据
                .subscribe(s -> {            //这里的s为String类型
                    //请求成功
                }, throwable -> {
                    //请求失败
                });

        /**
         * 3.6、多域名/动态域名
         */
        //3.6.1、多域名  TODO Url类
        /**
         * 通过@Domain()注解标注非默认域名，就会在RxHttp类中生成setDomainToXxxIfAbsent()方法，其中Xxx就是注解中取的别名。
         * 上面我们使用了两个@Domain()注解，此时(需要Rebuild一下项目)就会在RxHttp类中生成setDomainToBaseUrlBaiduIfAbsent()、
         * setDomainToBaseUrlGoogleIfAbsent()这两方法，此时发请求，我们就可以使用指定的域名，
         *
         * RxHttp共有3种指定域名的方式，按优先级排名分别是：手动输入域名 > 指定非默认域名 > 使用默认域名。
         */
        //使用默认域名，则无需添加任何额外代码
        //此时 url = "https://www.wanandroid.com/service/..."
        RxHttp.get("/service/...")
                .asString()
                .subscribe();

        //手动输入域名，此时 url = "https://www.mi.com/service/..."
        RxHttp.get("https://www.mi.com/service/...")
                .asString()
                .subscribe();

        //手动输入域名时，若再次指定域名，则无效
        //此时 url = "https://www.mi.com/service/..."
        RxHttp.get("https://www.mi.com/service/...")
                .setDomainToBaseUrlBaiduIfAbsent()  //此时指定Baidu域名无效
                .asString()
                .subscribe();

        //使用谷歌域名，此时 url = "https://www.google.com/service/..."
        RxHttp.get("/service/...")
                .setDomainToBaseUrlGoogleIfAbsent() //指定使用Google域名
                .asString()
                .subscribe();

        /**
         * 3.6.2、动态域名
         */
        //此时 url = "https://www.wanandroid.com/service/..."
        RxHttp.get("/service/...")
                .asString()
                .subscribe();

        Url.baseUrl = "https://www.qq.com"; //动态更改默认域名，改完立即生效，非默认域名同理
        //此时 url = "https://www.qq.com/service/..."
        RxHttp.get("/service/...")
                .asString()
                .subscribe();

        /**
         * 3.7、关闭请求
         */
        //3.7.1、自动关闭请求  需要引入本人开源的另一个库RxLife
        //以下代码均在FragmentActivty/Fragment中调用
        /**
         * 上面的this为LifecycleOwner接口对象，我们的FragmentActivity/Fragment均实现了这个接口，
         * 所有我们在FragmentActivity/Fragment中可以直接传this。
         */
//        RxHttp.postForm("/service/...")
//                .asString()
//                .as(RxLife.as(this)) //页面销毁、自动关闭请求
//                .subscribe();
        //或者
//        RxHttp.postForm("/service/...")
//                .asString()
//                .as(RxLife.asOnMain(this)) //页面销毁、自动关闭请求 并且在主线程回调观察者
//                .subscribe();

        //kotlin用户，请使用life或lifeOnMain方法，如下：
//        RxHttp.postForm("/service/...")
//                .asString()
//                .life(this) //页面销毁、自动关闭请求
//                .subscribe();
        //或者
//        RxHttp.postForm("/service/...")
//                .asString()
//                .lifeOnMain(this) //页面销毁、自动关闭请求 并且在主线程回调观察者
//                .subscribe();

        /**
         * 3.7.2、手动关闭请求
         */
        /**
         * 手动关闭请求，我们只需要在订阅回调的时候拿到Disposable对象，
         * 通过该对象可以判断请求是否结束，如果没有，就可以关闭请求
         */
        //订阅回调，可以拿到Disposable对象
        Disposable disposable = RxHttp.get("/service/...")
                .asString()
                .subscribe(s -> {
                    //成功回调
                }, throwable -> {
                    //失败回调
                });

        if (!disposable.isDisposed()) {  //判断请求有没有结束
            disposable.dispose();       //没有结束，则关闭请求
        }

        /**
         * 3.8、文件上传/下载/进度监听
         */
        //通过addFile系列方法添加文件
        RxHttp.postForm("/service/...") //发送Form表单形式的Post请求
                .addFile("file1", new File("xxx/1.png"))  //添加单个文件
                .addFile("fileList", new ArrayList<>())   //通过List对象，添加多个文件
                .asString()
                .subscribe(s -> {
                    //上传成功
                }, throwable -> {
                    //上传失败
                });
        //通过upload系列方法监听上传进度  TODO 方法参数跟文章顺序不一致
        //第一个参数是进度监听接口，每当进度有更新时，都会回调该接口，第二个参数是指定回调的线程，这里我们指定了在UI线程中回调。
        RxHttp.postForm("/service/...") //发送Form表单形式的Post请求
                .addFile("file1", new File("xxx/1.png"))
                .addFile("file2", new File("xxx/2.png"))
                .upload(AndroidSchedulers.mainThread(),
                        progress -> {
                    //上传进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已上传的字节大小
                    long totalSize = progress.getTotalSize();     //要上传的总字节大小
                })   //指定回调(进度/成功/失败)线程,不指定,默认在请求所在线程回调
                .asString()
                .subscribe(s -> {
                    //上传成功
                }, throwable -> {
                    //上传失败
                });

        /**
         * 3.8.2、下载
         */
        //下载使用asDownload(String)方法，传入本地路径即可
        //文件存储路径
        String destPath = getExternalCacheDir() + "/" + System.currentTimeMillis() + ".apk";
        RxHttp.get("http://update.9158.com/miaolive/Miaolive.apk")
                .asDownload(destPath) //注意这里使用asDownload操作符，并传入本地路径
                .subscribe(s -> {
                    //下载成功,回调文件下载路径
                }, throwable -> {
                    //下载失败
                });

        /**
         * 3.8.3、带进度下载 TODO 方法参数跟文章顺序不一致
         */
        //带进度下载使用asDownload(String,Consumer,Scheduler)方法
        //文件存储路径
//        String destPath = getExternalCacheDir() + "/" + System.currentTimeMillis() + ".apk";
        RxHttp.get("http://update.9158.com/miaolive/Miaolive.apk")
                .asDownload(destPath,
                        AndroidSchedulers.mainThread(),
                        progress -> {
                    //下载进度回调,0-100，仅在进度有更新时才会回调，最多回调101次，最后一次回调文件存储路径
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                }) //指定主线程回调
                .subscribe(s -> {//s为String类型，这里为文件存储路径
                    //下载完成，处理相关逻辑
                }, throwable -> {
                    //下载失败，处理相关逻辑
                });

        /**
         * 3.8.4、断点下载
         */
        //断点下载相较于下载，仅需要调用setRangeHeader(long startIndex, long endIndex)
        //方法传入开始及结束位置即可（结束位置不传默认为文件末尾），其它没有任何差别
//        String destPath = getExternalCacheDir() + "/" + "Miaobo.apk";
        long length = new File(destPath).length(); //已下载的文件长度
        RxHttp.get("http://update.9158.com/miaolive/Miaolive.apk")
                .setRangeHeader(length)  //设置开始下载位置，结束位置默认为文件末尾
                .asDownload(destPath)
                .subscribe(s -> { //s为String类型
                    //下载成功，处理相关逻辑
                }, throwable -> {
                    //下载失败，处理相关逻辑
                });

        /**
         * 3.8.5、带进度断点下载
         */
        //带进度断点下载相较于带进度下载仅需要调用setRangeHeader方法传入开始及结束位置即可
        // （结束位置不传默认为文件末尾），其它没有任何差别
//        String destPath = getExternalCacheDir() + "/" + "Miaobo.apk";
//        long length = new File(destPath).length(); //已下载的文件长度
        RxHttp.get("http://update.9158.com/miaolive/Miaolive.apk")
                .setRangeHeader(length)  //设置开始下载位置，结束位置默认为文件末尾
                .asDownload(destPath,
                        AndroidSchedulers.mainThread(),
                        progress -> {
                    //下载进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                }) //指定主线程回调
                .subscribe(s -> { //s为String类型
                    //下载成功，处理相关逻辑
                }, throwable -> {
                    //下载失败，处理相关逻辑
                });
        /*
        注：上面带进度断点下载中，返回的进度会从0开始，如果需要衔接上次下载的进度，
        则调用``setRangeHeader(long startIndex, long endIndex, boolean connectLastProgress)`
        方法第三个参数传入true即可，默认为false，
         */
//        String destPath = getExternalCacheDir() + "/" + "Miaobo.apk";
//        long length = new File(destPath).length(); //已下载的文件长度
        RxHttp.get("http://update.9158.com/miaolive/Miaolive.apk")
                .setRangeHeader(length, true)  //设置开始下载位置，结束位置默认为文件末尾
                .asDownload(destPath,
                        AndroidSchedulers.mainThread(),
                        progress -> {
                    //下载进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                }) //指定主线程回调
                .subscribe(s -> { //s为String类型
                    //下载成功，处理相关逻辑
                }, throwable -> {
                    //下载失败，处理相关逻辑
                });

        /**
         * 3.9.1、设置全局超时
         */
        //设置读、写、连接超时时间为15s
        //RxHttp内部默认的读、写、连接超时时间均为10s，如需修改，请自定义OkHttpClient对象，
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
//        RxHttp.init(client);

        /**
         * 3.9.2、为单个请求设置超时
         */
        //为单个请求设置超时，使用的是RxJava的timeout(long timeout, TimeUnit timeUnit)方法，
//        RxHttp.get("/service/...")
//                .asString()
//                .timeout(5, TimeUnit.SECONDS)//设置总超时时间为5s
//                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
//                .subscribe(s -> {
//                    //成功回调
//                }, (OnError) error -> {
//                    //失败回调
//                });

        /**
         * 3.10、设置Converter
         */
        /**
         * 3.10.1、设置全局Converter
         */
        IConverter converter = FastJsonConverter.create();
//        RxHttp.setConverter(converter);

        /**
         * 3.10.2、为请求设置单独的Converter
         */
        //首先需要在任意public类中通过@Converter注解声明Converter
//        public class RxHttpManager {
//            @Converter(name = "XmlConverter") //指定Converter名称
//            public static IConverter xmlConverter = XmlConverter.create();
//        }
        //rebuild 一下项目，就在自动在RxHttp类中生成setXmlConverter()方法，
        // 随后就可以调用此方法为单个请求指定Converter，
//        RxHttp.get("/service/...")
//                .setXmlConverter()   //指定使用XmlConverter，不指定，则使用全局的Converter
//                .asClass(NewsDataXml.class)
//                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
//                .subscribe(dataXml -> {
//                    //成功回调
//                }, (OnError) error -> {
//                    //失败回调
//                });

        /**
         * 3.11、请求加解密
         */
        /**
         * 3.11.1、加密
         * 请求加密，需要自定义Param，非常简单，详情请查看本文5.2章节----自定义Param
         */
        /**
         * 3.11.2、解密
         */
        //设置数据解密/解码器
        //有些时候，请求会返回一大串的密文，此时就需要将密文转化为明文，直接来看代码
        //通过RxHttp.setResultDecoder(Function<String, String>)静态方法，传入一个接口对象，
        // 此接口会在每次请求成功的时候被回调，并传入请求返回的密文，只需要将密文解密后返回即可。
//        RxHttp.setResultDecoder(new Function<String, String>() {
//            //每次请求成功，都会回调这里，并传入请求返回的密文
//            @Override
//            public String apply(String s) throws Exception {
//                String plaintext = decode(s);   //将密文解密成明文，解密逻辑自己实现
//                return plaintext;    //返回明文
//            }
//        });
        //有些请求是不需求解密的，此时就可以调用setDecoderEnabled(boolean)方法，并传入false即可
        RxHttp.get("/service/...")
                .setDecoderEnabled(false)  //设置本次请求不需要解密，默认为true
                .asString()
                .subscribe(s -> {
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                });

        /**
         * 3.12、同步请求/指定回调线程
         */
        //RxHttp默认在Io线程执行请求，也默认在Io线程回调，即默认在同一Io线程执行请求并回调，
        // 当然，我们也可以指定请求/回调所在线程。

        /**
         * 3.12.1、同步请求
         */
        //RxHttp默认在IO线程发起请求，即异步请求，如果需要同步请求，调用setSync方法即可
        //指定请求所在线程，需要在第二部曲前任意位置调用，第二部曲后调用无效
        RxHttp.get("/service/...")
                .setSync() //同步执行，
                .asString()
                .subscribe();

        /**
         * 3.12.2、指定回调所在线程
         */
        //指定回调所在线程，依然使用RxJava的线程调度器
        //指定回调所在线程，需要在第二部曲后调用
        RxHttp.get("/service/...")
                .asString()
                .observeOn(AndroidSchedulers.mainThread()) //指定在主线程回调
                .subscribe(s -> { //s为String类型，主线程回调
                    //成功回调
                }, throwable -> {
                    //失败回调
                });

        /**
         * 3.13、 Retrofit用户
         */
        /*
        时常会有童鞋问我，我是Retrofit用户，喜欢把接口写在一个类里，然后可以直接调用，RxHttp如何实现？其实，
        这个问题压根就不是问题，在介绍第二部曲的时候，我们知道，使用asXxx方法后，就会返回Observable<T>对象，
        因此，我们就可以这样实现
         */
//        public class HttpWrapper {
//
//            public static Observable<List<Student>> getStudent(int page) {
//                return RxHttp.get("/service/...")
//                        .add("page", page)
//                        .asList(Student.class);
//            }
//        }

        //随后在其它地方就可以直接调用
//        HttpWrapper.getStudent(1)
//                .as(RxLife.asOnMain(this))  //主线程回调，并在页面销毁自动关闭请求(如果还未关闭的话)
//                .subscribe(students -> { //学生列表
//                    //成功回调
//                }, throwable -> {
//                    //失败回调
//                });

        /*
        还有的同学问，我们获取列表的接口，页码是和url拼接在一起的，Retrofit可以通过占位符，那RxHttp又如何实现？简单，如下：
         */
        /*
        这一点跟Retrofit不同，Retrofit是通过注解指定占位符的，而RxHttp是使用标准的占位符，我们只需要在url中声明占位符，随后在传入url的后面，带上对应的参数即可。
         */
//        public class HttpWrapper {
//
//            //单个占位符
//            public static Observable<Student> getStudent(int page) {
//                return RxHttp.get("/service/%d/...", page)  //使用标准的占位符协议
//                        .asClass(Student.class);
//            }
//
//            //多个占位符
//            public static Observable<Student> getStudent(int page, int count) {
//                return RxHttp.get("/service/%1$d/%2$d/...", page, count)  //使用标准的占位符协议
//                        .asClass(Student.class);
//            }
//        }

        /**
         * 4、原理剖析
         * RxHttp使用到当下流行的注解处理器工具(Annotation Processing Tool，以下简称APT)，像知名的Eventbus、
         * ButterKnife、Dagger2、Glide以及Jetpack库里非常好用Room数据库框架，都使用到了APT，它能够在编译时检索注解信息，
         * 通过Javapoet框架生成Java类、方法等相关代码(想生成Kotlin相关代码，使用kotlinpoet)，并因此在运行时做到零性能损耗。
         * 那么，APT给RxHttp带来了哪些优势？RxHttp又是如何使用APT的？继续往下看
         * 说起APT，大家脑海里第一个想到的可能是解耦，没错，解耦是它的一大优势，其实它还有一个更大有优势，
         * 那就是根据配置，生成不同的代码逻辑；比如在RxHttp中，默认是不依赖RxJava的，但是如果你需要使用
         * RxHttp + RxJava方式发送请求，就可以在annotationProcessorOptions标签中的rxhttp_rxjava
         * 参数来配置RxJava大版本，可传入RxJava2或RxJava3，内部根据传入的RxJava版本，生成不同的代码，
         * 这样就可做到一套代码同时兼通RxJava2和RxJava3，如果后续出了RxJava4、RxJava5等新版本，一样可以兼容，而且非常简单。
         *
         */
        /**
         * RxHttp是如何使用APT？在RxHttp中，一共定义了6个注解，如下：
         * @DefaultDomain：用它来指定默认的baseUrl，只能使用一次
         * @Domain：指定非默认的baseUrl，可使用多次
         * @Parser： 指定自定义的解析器，可使用多次，这个非常强大，可在解析器里写自己数据解析逻辑，
         * 并返回任意类型的数据，完美解决服务端返回的数据不规范问题
         * @Param：指定自定义的Param，可使用多次，发送统一加密请求时用到
         * @OkClient：为不同请求配置不同的OkHttpClient对象，可多次使用
         * @Converter：为不同请求配置不同的Converter对象，可多次使用
         *
         */
    }



//    private static OkHttpClient getDefaultOkHttpClient() {
//        X509TrustManager trustAllCert = new X509TrustManagerImpl();
//        SSLSocketFactory sslSocketFactory = new SSLSocketFactoryImpl(trustAllCert);
//        return new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .sslSocketFactory(sslSocketFactory, trustAllCert) //添加信任证书
//                .hostnameVerifier((hostname, session) -> true) //忽略host验证
//                .build();
//    }

}