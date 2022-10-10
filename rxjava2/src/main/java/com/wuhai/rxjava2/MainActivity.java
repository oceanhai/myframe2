package com.wuhai.rxjava2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * https://www.jianshu.com/p/0cd258eecf60
 *
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "rxJava2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 拿着第一个接口的返回值作为第二个接口的入参使用。
     * TODO 说实话 感觉这是啥玩意啊
     * @param view
     */
    public void method050(View view){
        Observable<String> test1 = Observable.just("111").delay(6, TimeUnit.SECONDS);
        Observable.just(1)
                .flatMap(new Function<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> apply(Integer integer) throws Exception {
                        return test1;
                    }
                })
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {
                        return Observable.just(s+"22");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.e(TAG, "method050 返回结果:"+o.toString());
                    }
                });
    }

    /**
     * 想必这种情况也在实际情况中比比皆是，例如用户注册成功后需要自动登录，我们只需要先通过注册接口注册用户信息，
     * 注册成功后马上调用登录接口进行自动登录即可。
     *
     * 我们的 flatMap 恰好解决了这种应用场景，flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，
     * 然后将它们发射的数据合并后放到一个单独的 Observable，利用这个特性，我们很轻松地达到了我们的需求。
     *
     */
    public void method05(View view){

        //https://www.wanandroid.com/tree/json  TODO 单独一个接口使用
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Request.Builder builder = new Request.Builder()
//                        .url("https://www.wanandroid.com/tree/json")
//                        .get();
//                Request request = builder.build();
//                Call call = new OkHttpClient().newCall(request);
//                Response response = call.execute();
//                if (response.isSuccessful()) {
//                    ResponseBody body = response.body();
//                    if (body != null) {
//                        JsonObject jsonObject = (JsonObject) new JsonParser().parse(body.string());
//                        String data = "";
//                        if(jsonObject.get("errorCode").getAsInt() == 0){
//                            //TODO 这样是不对的，getAsString所get的字段必须是字符串
////                            data = jsonObject.get("data").getAsString();
//                            //TODO 只能这样写
//                            data = jsonObject.get("data").toString();
////                            JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
////                            Log.e(TAG, "map data =" + data);
//                        }
//                        List<KnowledgeTreeBody> list = new Gson().fromJson(data,
//                                new TypeToken<List<KnowledgeTreeBody>>(){}.getType());
//                        //我们取第一个的id
//                        int id = list.get(0).getChildren().get(0).getId();
//                        String name = list.get(0).getChildren().get(0).getName();
//                        e.onNext(""+id);
//
////                        e.onNext(""+jsonObject.get("errorCode").getAsInt());
//                    }
//                }else{
//                    e.onError(new Throwable("接口请求失败"));
//                }
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                //TODO new Subscriber 已经废弃这中的入参了，只能用Observer
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.e(TAG, "onSubscribe ");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.e(TAG, "onNext 结果cid="+s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError 结果="+e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "onComplete ");
//                    }
//                });

        //https://www.wanandroid.com/article/list/0/json?cid=60 TODO 单独一个接口使用
//        Observable.create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> e) throws Exception {
//                        Request.Builder builder = new Request.Builder()
//                                .url("https://www.wanandroid.com/article/list/0/json?cid=60")
//                                .get();
//                        Request request = builder.build();
//                        Call call = new OkHttpClient().newCall(request);
//                        Response response = call.execute();
//                        if (response.isSuccessful()) {
//                            ResponseBody body = response.body();
//                            if (body != null) {
//                                JsonObject jsonObject = (JsonObject) new JsonParser().parse(body.string());
//                                String data = "";
//                                if(jsonObject.get("errorCode").getAsInt() == 0){
//                                    data = jsonObject.get("data").toString();
//
//                                    ArticleResponseBody articleResponseBody =
//                                            new Gson().fromJson(data, ArticleResponseBody.class);
//                                    //我们取第一个的id
//                                    List<Article> list = articleResponseBody.getDatas();
//                                    String chapterName = list.get(0).getChapterName();
//                                    String link = list.get(0).getLink();
//                                    e.onNext("chapterName="+chapterName+", link="+link);
//                                }
//                            }
//                        }else{
//                            e.onError(new Throwable("接口请求失败"));
//                        }
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                //TODO new Subscriber 已经废弃这中的入参了，只能用Observer
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.e(TAG, "onSubscribe ");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.e(TAG, "onNext 文章="+s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError 结果="+e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "onComplete ");
//                    }
//                });

        //TODO 用okhttp网络请求 实现
        // 参考https://www.jianshu.com/p/6bcda635bb82 这篇文章网络请求用的retrofit框架
        //https://www.wanandroid.com/tree/json  TODO 第一个接口的结果作为第二个接口的入参
        //https://www.wanandroid.com/article/list/0/json?cid=60
        Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Request.Builder builder = new Request.Builder()
                                .url("https://www.wanandroid.com/tree/json")
                                .get();
                        Request request = builder.build();
                        Call call = new OkHttpClient().newCall(request);
                        Response response = call.execute();
                        if (response.isSuccessful()) {
                            ResponseBody body = response.body();
                            if (body != null) {
                                JsonObject jsonObject = (JsonObject) new JsonParser().parse(body.string());
                                String data = "";
                                if(jsonObject.get("errorCode").getAsInt() == 0){
                                    data = jsonObject.get("data").toString();
                                }
                                List<KnowledgeTreeBody> list = new Gson().fromJson(data,
                                        new TypeToken<List<KnowledgeTreeBody>>(){}.getType());
                                //我们取第一个的id
                                int id = list.get(0).getChildren().get(0).getId();
                                String name = list.get(0).getChildren().get(0).getName();
                                e.onNext(""+id);

                            }
                        }else{
                            e.onError(new Throwable("接口请求失败"));
                        }
                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String cid) throws Exception {
                        Log.e(TAG, "flatMap apply cid="+cid);
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> e) throws Exception {
                                Request.Builder builder = new Request.Builder()
                                        .url("https://www.wanandroid.com/article/list/0/json?cid="+cid)
                                        .get();
                                Request request = builder.build();
                                Call call = new OkHttpClient().newCall(request);
                                Response response = call.execute();
                                if (response.isSuccessful()) {
                                    ResponseBody body = response.body();
                                    if (body != null) {
                                        JsonObject jsonObject = (JsonObject) new JsonParser().parse(body.string());
                                        String data = "";
                                        if(jsonObject.get("errorCode").getAsInt() == 0){
                                            data = jsonObject.get("data").toString();

                                            ArticleResponseBody articleResponseBody =
                                                    new Gson().fromJson(data, ArticleResponseBody.class);
                                            //我们取第一个的id
                                            List<Article> list = articleResponseBody.getDatas();
                                            String chapterName = list.get(0).getChapterName();
                                            String link = list.get(0).getLink();
                                            e.onNext("chapterName="+chapterName+", link="+link);
                                        }
                                    }
                                }else{
                                    e.onError(new Throwable("接口请求失败"));
                                }
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe d="+d.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext s="+s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError e="+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });

    }

    /**
     * 想必在实际应用中，很多时候（对数据操作不敏感时）都需要我们先读取缓存的数据，如果缓存没有数据，
     * 再通过网络请求获取，随后在主线程更新我们的UI。
     *
     * concat 操作符简直就是为我们这种需求量身定做。
     *
     * 利用 concat 的必须调用 onComplete 后才能订阅下一个 Observable 的特性，
     * 我们就可以先读取缓存数据，倘若获取到的缓存数据不是我们想要的，再调用 onComplete() 以执行获取网络数据的 Observable，
     * 如果缓存数据能应我们所需，则直接调用 onNext()，防止过度的网络请求，浪费用户的流量。
     *
     * TODO 注释掉的代码 ，看逻辑即可
     */
    public void method04(View view){
//        Observable<FoodList> cache = Observable.create(new ObservableOnSubscribe<FoodList>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<FoodList> e) throws Exception {
//                Log.e(TAG, "create当前线程:"+Thread.currentThread().getName() );
//                FoodList data = CacheManager.getInstance().getFoodListData();
//
//                // 在操作符 concat 中，只有调用 onComplete 之后才会执行下一个 Observable
//                if (data != null){ // 如果缓存数据不为空，则直接读取缓存数据，而不读取网络数据
//                    isFromNet = false;
//                    Log.e(TAG, "\nsubscribe: 读取缓存数据:" );
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mRxOperatorsText.append("\nsubscribe: 读取缓存数据:\n");
//                        }
//                    });
//
//                    e.onNext(data);
//                }else {
//                    isFromNet = true;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mRxOperatorsText.append("\nsubscribe: 读取网络数据:\n");
//                        }
//                    });
//                    Log.e(TAG, "\nsubscribe: 读取网络数据:" );
//                    e.onComplete();
//                }
//
//
//            }
//        });
//
//        Observable<FoodList> network = Rx2AndroidNetworking.get("http://www.tngou.net/api/food/list")
//                .addQueryParameter("rows",10+"")
//                .build()
//                .getObjectObservable(FoodList.class);
//
//
//        // 两个 Observable 的泛型应当保持一致
//
//        Observable.concat(cache,network)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<FoodList>() {
//                    @Override
//                    public void accept(@NonNull FoodList tngouBeen) throws Exception {
//                        Log.e(TAG, "subscribe 成功:"+Thread.currentThread().getName() );
//                        if (isFromNet){
//                            mRxOperatorsText.append("accept : 网络获取数据设置缓存: \n");
//                            Log.e(TAG, "accept : 网络获取数据设置缓存: \n"+tngouBeen.toString() );
//                            CacheManager.getInstance().setFoodListData(tngouBeen);
//                        }
//
//                        mRxOperatorsText.append("accept: 读取数据成功:" + tngouBeen.toString()+"\n");
//                        Log.e(TAG, "accept: 读取数据成功:" + tngouBeen.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        Log.e(TAG, "subscribe 失败:"+Thread.currentThread().getName() );
//                        Log.e(TAG, "accept: 读取数据失败："+throwable.getMessage() );
//                        mRxOperatorsText.append("accept: 读取数据失败："+throwable.getMessage()+"\n");
//                    }
//                });
    }

    /**
     * 想必大家都知道，很多时候我们在使用 RxJava 的时候总是和 Retrofit 进行结合使用，而为了方便演示，
     * 这里我们就暂且采用 OkHttp3 进行演示，配合 map，doOnNext ，线程切换进行简单的网络请求：
     * 1）通过 Observable.create() 方法，调用 OkHttp 网络请求；
     * 2）通过 map 操作符集合 gson，将 Response 转换为 bean 类；
     * 3）通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作；
     * 4）调度线程，在子线程中进行耗时操作任务，在主线程中更新 UI ；
     * 5）通过 subscribe()，根据请求成功或者失败来更新 UI 。
     *
     */
    public void method03(View view){
        Observable.create(new ObservableOnSubscribe<Response>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                        Request.Builder builder = new Request.Builder()
                                .url("https://www.wanandroid.com/banner/json")
                                .get();
                        Request request = builder.build();
                        Call call = new OkHttpClient().newCall(request);
                        Response response = call.execute();
                        e.onNext(response);
                    }
                }).map(new Function<Response, BannerResult>() {
                    @Override
                    public BannerResult apply(@NonNull Response response) throws Exception {
                        if (response.isSuccessful()) {
                            ResponseBody body = response.body();
                            if (body != null) {
                                Log.e(TAG, "map:转换前:" + response.body());
                                return new Gson().fromJson(body.string(), BannerResult.class);
                            }
                        }
                        return null;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<BannerResult>() {
                    @Override
                    public void accept(@NonNull BannerResult s) throws Exception {
                        Log.e(TAG, "doOnNext: 保存成功：" + s.toString() + "\n");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerResult>() {
                               @Override
                               public void accept(@NonNull BannerResult data) throws Exception {
                                   Log.e(TAG, "成功:" + data.toString() + "\n");
                               }},
                            new Consumer<Throwable>() {
                                   @Override
                                   public void accept(@NonNull Throwable throwable) throws Exception {
                                       Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                                   }
                               });
    }

    /**
     * 分别用 Schedulers.newThread() 和 Schedulers.io() 对发射线程进行切换，并采用 observeOn(AndroidSchedulers.mainThread() 和 Schedulers.io() 进行了接收线程的切换。可以看到输出中发射线程仅仅响应了第一个 newThread，但每调用一次 observeOn() ，线程便会切换一次，因此如果我们有类似的需求时，便知道如何处理了。
     *
     * RxJava 中，已经内置了很多线程选项供我们选择，例如有：
     *
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作；
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作；
     * Schedulers.newThread() 代表一个常规的新线程；
     * AndroidSchedulers.mainThread() 代表Android的主线程
     *
     */
    public void method02(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                        Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                        e.onNext(1);
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(io)，Current thread is " + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * 创建
     *
     * RxJava 2.x 与 1.x 还是存在着一些区别的。首先，创建 Observable 时，回调的是 ObservableEmitter ，
     * 字面意思即发射器，并且直接 throws Exception。其次，在创建的 Observer 中，也多了一个回调方法：onSubscribe，
     * 传递参数为Disposable，Disposable 相当于 RxJava 1.x 中的 Subscription， 用于解除订阅。
     * 可以看到示例代码中，在 i 自增到 2 的时候，订阅关系被切断。
     *
     */
    public void method01(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                Log.e(TAG, "Observable emit 4" + "\n" );
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() { // 第三步：订阅

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(TAG, "Observer onNext integer="+integer);
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n" );
            }
        });
    }
}