package com.wuhai.myframe2.ui.rxjava;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.bean.ActivityHomeEntity;
import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.myframe2.ui.contentprovider.Person;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RequestNetCallBack;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.ResponseError;
import com.wuhai.myframe2.ui.retrofit.networknormalrx.RootResponse;
import com.wuhai.myframe2.utils.BitmpUtils;
import com.wuhai.retrofit.utils.LogUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 参考文章
 * http://gank.io/post/560e15be2dca930e00da1083
 *
 * 注意 TODO 这个ac所在进程  android:process=":newprocess"
 */
public class RxJavaActivity extends RxAppCompatActivity implements View.OnClickListener {

    public static final String TAG = "rxjava";

    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.method02)
    TextView method02;
    @BindView(R.id.method03)
    TextView method03;
    @BindView(R.id.method04)
    TextView method04;
    @BindView(R.id.method05)
    TextView method05;
    @BindView(R.id.method06)
    TextView method06;
    @BindView(R.id.method07)
    Button method07;
    @BindView(R.id.method08)
    TextView method08;
    @BindView(R.id.method09)
    Button method09;
    @BindView(R.id.method10)
    TextView method10;
    @BindView(R.id.method11)
    TextView method11;
    @BindView(R.id.method12)
    Button method12;
    @BindView(R.id.method13)
    TextView method13;
    @BindView(R.id.method14)
    TextView method14;
    @BindView(R.id.method15)
    Button method15;
    @BindView(R.id.ofType1)
    Button ofType1;
    @BindView(R.id.ofType2)
    Button ofType2;
    @BindView(R.id.interval)
    Button interval;
    @BindView(R.id.rxlifecycle1)
    Button rxlifecycle1;
    @BindView(R.id.rxlifecycle2)
    Button rxlifecycle2;
    @BindView(R.id.iv02)
    ImageView iv02;
    @BindView(R.id.throttle_first)
    TextView throttleFirst;
    @BindView(R.id.lift)
    TextView lift;
    @BindView(R.id.compose)
    TextView compose;
    @BindView(R.id.scheduler2)
    TextView scheduler2;
    @BindView(R.id.doOnSubscribe)
    TextView doOnSubscribe;
    @BindView(R.id.retrofit)
    TextView retrofit;
    @BindView(R.id.doOnNext)
    TextView doOnNext;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_rx_java);
        ButterKnife.bind(this);

        method02.setOnClickListener(this);
        method03.setOnClickListener(this);
        method04.setOnClickListener(this);
        method05.setOnClickListener(this);
        method06.setOnClickListener(this);
        method07.setOnClickListener(this);
        method08.setOnClickListener(this);
        method09.setOnClickListener(this);
        method10.setOnClickListener(this);
        method11.setOnClickListener(this);
        method12.setOnClickListener(this);
        method13.setOnClickListener(this);
        method14.setOnClickListener(this);
        method15.setOnClickListener(this);

        ofType1.setOnClickListener(this);
        ofType2.setOnClickListener(this);

        interval.setOnClickListener(this);
        rxlifecycle1.setOnClickListener(this);
        rxlifecycle2.setOnClickListener(this);

        throttleFirst.setOnClickListener(this);
        lift.setOnClickListener(this);
        compose.setOnClickListener(this);
        scheduler2.setOnClickListener(this);
        doOnSubscribe.setOnClickListener(this);
        retrofit.setOnClickListener(this);
        doOnNext.setOnClickListener(this);

    }


    /**
     * 变换的原理：lift()  TODO 不太理解
     */
    public void method15() {
        Observable observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

            }
        });
        observable.lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext("" + integer);
                    }
                };
            }
        });
    }

    private ServiceProvider serviceProvider;

    /**
     * retrofit + rxJava
     * TODO 这个去查看RetrofitNetworkRequestActivity的 get2 rx
     * TODO RetrofitRxJavaRxLifecycleActivity 更完美 追加了RxLifecycle
     */
    private void method14() {
//        networkClient.token() // 返回 Observable<String>，在订阅时请求 token，并在响应后发送 token
//                .flatMap(new Func1<String, Observable<Messages>>() {
//                    @Override
//                    public Observable<Messages> call(String token) {
//                        // 返回 Observable<Messages>，在订阅时请求消息列表，并在响应后发送请求到的消息列表
//                        return networkClient.messages();
//                    }
//                })
//                .subscribe(new Action1<Messages>() {
//                    @Override
//                    public void call(Messages messages) {
//                        // 处理显示消息列表
//                        showMessages(messages);
//                    }
//                });

        serviceProvider = new ServiceProvider();
        //方式一  TODO retrofit 返回Observable
        serviceProvider.activityhomeRx2();

        //方式二 捎货帮的形式  TODO 相比方式一 进一步封装
        serviceProvider.activityhomeRx(new RequestNetCallBack<RootResponse<ActivityHomeEntity>>() {
            @Override
            public void onSuccess(RootResponse<ActivityHomeEntity> dataResponse) {
                LogUtil.e(TAG, "code = " + dataResponse.getCode());
                LogUtil.e(TAG, "success = " + dataResponse.isSuccess());
                LogUtil.e(TAG, "message = " + dataResponse.getMessage());
                LogUtil.e(TAG, "result = " + dataResponse.getResult());
            }

            /**
             * 重写
             * @param responseError
             */
            @Override
            public void onFailure(ResponseError responseError) {
                LogUtil.e(TAG, "err = " + responseError.toString());
            }

            @Override
            public Dialog baseGetLoadingDialog() {
                return null;
            }
        });

    }

    /**
     * doOnNext
     * Retrofit 的结合 拓展出 doOnNext()
     */
    private void doOnNext(){
        /**
         * subscribeOn，observeOn 都不存在的话，默认所有都是在主线程
         *
         * 只有②存在，
         * 结果  create call 所在线程main
         *      doOnNext call 所在线程RxNewThreadScheduler-1
         *      call: 100,所在线程RxNewThreadScheduler-1
         */
        Observable.create(new Observable.OnSubscribe<Person>() {
                    @Override
                    public void call(Subscriber<? super Person> subscriber) {
                        Log.e(TAG, "create call 所在线程"+Thread.currentThread().getName());
                        Person person = new Person("wh",201);
                        Log.e(TAG, "create call person="+person.toString());
                        subscriber.onNext(person);
                    }
                })
//                .subscribeOn(Schedulers.io())//① 执行线程 影响的是Observable.OnSubscribe
                .observeOn(Schedulers.newThread())//② 指定线程 影响的是下一个指定线程前的doOnNext，map,flatMap,lift
                .doOnNext(new Action1<Person>() {//如果②不存在，所在线程RxIoScheduler-x
                    @Override
                    public void call(Person person) {
                        Log.e(TAG, "doOnNext call 所在线程"+Thread.currentThread().getName());
                        person.setAge(100);
                        Log.e(TAG, "doOnNext call person="+person.toString());
                    }
                })
//                .observeOn(AndroidSchedulers.mainThread())//③ 通②
//                .subscribeOn(AndroidSchedulers.mainThread())//④ 如果①存在，④无效
                //TODO  这个 onNext person=Person{id=0, name='wh', age=100} 打印结果
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError "+e.toString());
                    }

                    @Override
                    public void onNext(Person person) {
                        Log.e(TAG, "onNext person="+person.toString());
                    }
                });
                //TODO 这个不知道为啥不打印结果了
//                .subscribe(new Action1<Person>() {
//                    @Override
//                    public void call(Person person) {
//                        Log.d(TAG, "call: " + person.getAge()+",所在线程"+Thread.currentThread().getName());//输出100
//                    }
//                });


    }

    /**
     * Retrofit 的结合
     */
    private void retrofit() {
        /**
         * Retrofit 的传统 API
         */
//        @GET("/user")
//        public void getUser(@Query("userId") String userId, Callback<User> callback);
//
//        getUser(userId, new Callback<User>() {
//            @Override    public void success(User user) {
//                userView.setUser(user);
//            }
//            @Override
//            public void failure(RetrofitError error) {        // Error handling
//                ...
//            }
//        };

        /**
         * 使用 RxJava 形式的 API
         */
//        @GET("/user")
//        public Observable<User> getUser(@Query("userId") String userId);
//
//        getUser(userId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onNext(User user) {
//                        userView.setUser(user);
//                    }
//                    @Override
//                    public void onCompleted() {
//
//                    }
//                    @Override
//                    public void onError(Throwable error) {            // Error handling
//                        ...
//                    }
//                });

        //------------------------------------------------------------------------------

        /**
         * 那 Retrofit 为什么还要提供 RxJava 的支持呢？
         * 假设这么一种情况：你的程序取到的 User 并不应该直接显示，而是需要先与数据库中的数据进行比对和修正后再显示。
         *
         * 有问题吗？
         * 很简便，但不要这样做。为什么？因为这样做会影响性能。数据库的操作很重，一次读写操作花费 10~20ms 是很常见的，
         * 这样的耗时很容易造成界面的卡顿。
         */
//        getUser(userId, new Callback<User>() {
//            @Override
//            public void success(User user) {
//                processUser(user); // 尝试修正 User 数据  TODO 需求：需要先与数据库中的数据进行比对和修正
//                userView.setUser(user);
//            }
//            @Override
//            public void failure(RetrofitError error) {        // Error handling
//                ...
//            }
//        };

        /**
         * 性能问题解决，但……这代码实在是太乱了，迷之缩进啊！杂乱的代码往往不仅仅是美观问题，因为代码越乱往往就越难读懂，
         * 而如果项目中充斥着杂乱的代码，无疑会降低代码的可读性，造成团队开发效率的降低和出错率的升高。
         */
//        getUser(userId, new Callback<User>() {
//            @Override
//            public void success(User user) {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        processUser(user); // 尝试修正 User 数据
//                        runOnUiThread(new Runnable() { // 切回 UI 线程
//                            @Override
//                            public void run() {
//                                userView.setUser(user);
//                            }
//                        });
//                    }).start();
//                }
//                @Override
//                public void failure(RetrofitError error) {        // Error handling
//                    ...
//                }
//            };
//
//        });
//
//        getUser(userId)
//                .doOnNext(new Action1<User>() {
//                    @Override
//                    public void call(User user) {
//                        processUser(user);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onNext(User user) {
//                        userView.setUser(user);
//                    }
//                    @Override
//                    public void onCompleted() {
//
//                    }
//                    @Override
//                    public void onError(Throwable error) {            // Error handling
//                        ...
//                    }
//                });

        //------------------------------------------------------------------------------
        /**
         * 再举一个例子：假设 /user 接口并不能直接访问，而需要填入一个在线获取的 token ，代码应该怎么写？
         */
//        @GET("/token")
//        public void getToken(Callback<String> callback);
//        @GET("/user")
//        public void getUser(@Query("token") String token, @Query("userId") String userId, Callback<User> callback);
//        ...
//        getToken(new Callback<String>() {
//            @Override
//            public void success(String token) {
//                getUser(token, userId, new Callback<User>() {
//                    @Override
//                    public void success(User user) {
//                        userView.setUser(user);
//                    }
//                    @Override
//                    public void failure(RetrofitError error) {                // Error handling
//                        ...
//                    }
//                };
//            }
//            @Override
//            public void failure(RetrofitError error) {        // Error handling
//                // ...
//            }
//        });

        /**
         * rxjava 优化
         */
//        @GET("/token")
//        public Observable<String> getToken();
//        @GET("/user")
//        public Observable<User> getUser(@Query("token") String token, @Query("userId") String userId);
//        ...
//        getToken()
//                .flatMap(new Func1<String, Observable<User>>() {
//                @Override
//                public Observable<User> onNext(String token) {
//                    return getUser(token, userId);
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onNext(User user) {
//                        userView.setUser(user);
//                    }
//                    @Override
//                    public void onCompleted() {
//
//                    }
//                    @Override
//                    public void onError(Throwable error) {            // Error handling
//                        ...
//                    }
//                });

    }

    /**
     * doOnSubscribe
     */
    private void doOnSubscribe() {
        /**
         * 在 doOnSubscribe()的后面跟一个 subscribeOn() ，就能指定准备工作的线程了。
         */
        final int drawableRes = R.drawable.f0;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                LogUtil.e(TAG, "创建observable：" + Thread.currentThread().getName());

                //耗时操作
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {//TODO doOnSubscribe 不知道有何作用
                    @Override
                    public void call() {
                        Log.e(TAG,"doOnSubscribe 所在线程"+Thread.currentThread().getName());
                        iv01.setVisibility(View.VISIBLE); // 需要在主线程执行
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程  TODO subscribeOn 不是说只有第一个有效吗
//                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onStart() {//TODO 多此一举 本身就在main线程啊  不知道doOnSubscribe有啥用
                        super.onStart();
                        Log.e(TAG, "onStart 所在线程" + Thread.currentThread().getName());
                        iv01.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        Log.e(TAG, "onNext 所在线程" + Thread.currentThread().getName());
                        iv01.setImageDrawable(drawable);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted 所在线程" + Thread.currentThread().getName());
                    }
                });

        /**
         * 自己鼓捣了半天，确实，不理解，正如上面的 TODO 描述的，onStart 本身就是在主线程
         * 为啥还要用doOnSubscribe
         */
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e(TAG, "1---call 执行所在线程 " + Thread.currentThread().getName());
                subscriber.onNext("我在测试所在线程-完毕");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(Schedulers.newThread())
          .map(new Func1<String, String>() {
              @Override
              public String call(String s) {
                  Log.e(TAG, "1---map 执行所在线程 " + Thread.currentThread().getName());
                  return "起一个新线程执行耗时操作";
              }
          })
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.e(TAG, "1---onStart 所在线程 " + Thread.currentThread().getName());
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "1---onCompleted 所在线程 " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "1---onNext 回调所在线程 " + Thread.currentThread().getName());
                Log.e(TAG, "1---onNext 结果：" + s);
            }
        });
    }

    /**
     * 线程控制：Scheduler (二)
     */
    private void scheduler2() {
        /**
         * 多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可
         * 当使用了多个 subscribeOn() 的时候，只有第一个 subscribeOn() 起作用。
         */
        Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(new Func1<Integer, Object>() {
                    @Override
                    public Object call(Integer integer) {
                        Log.e(TAG, "所在线程1:" + Thread.currentThread().getName() + ",integer=" + integer);
                        return integer;
                    }
                }) // 新线程，由 observeOn() 指定
                .observeOn(Schedulers.io())
                .map(new Func1<Object, Object>() {
                    @Override
                    public Object call(Object o) {
                        Log.e(TAG, "所在线程2:" + Thread.currentThread().getName());
                        return o;
                    }
                }) // IO 线程，由 observeOn() 指定
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {// Android 主线程，由 observeOn() 指定
                    @Override
                    public void call(Object o) {
                        Log.e(TAG, "所在线程3:" + Thread.currentThread().getName() + ",Object=" + o.toString());
                    }
                });
    }

    /**
     * compose
     */
    private void compose() {
        /**
         *举个例子，假设在程序中有多个 Observable ，并且他们都需要应用一组相同的 lift() 变换
         */
//        observable1.lift1().lift2().lift3().lift4().subscribe(subscriber1);
//        observable2.lift1().lift2().lift3().lift4().subscribe(subscriber2);
//        observable3.lift1().lift2().lift3().lift4().subscribe(subscriber3);
//        observable4.lift1().lift2().lift3().lift4().subscribe(subscriber4);

        /**
         * 你觉得这样太不软件工程了，于是你改成了这样
         */
//        private Observable liftAll(Observable observable) {
//            return observable.lift1().lift2().lift3().lift4();
//        }
//        ...
//        liftAll(observable1).subscribe(subscriber1);
//        liftAll(observable2).subscribe(subscriber2);
//        liftAll(observable3).subscribe(subscriber3);
//        liftAll(observable4).subscribe(subscriber4);

        /**
         * 上述方案
         * 可读性、可维护性都提高了。可是 Observable 被一个方法包起来，这种方式对于 Observale 的灵活性似乎还是增添了那么点限制。怎么办？
         * 这个时候，就应该用 compose() 来解决了
         */
//        public class LiftAllTransformer implements Observable.Transformer<Integer, String> {
//            @Override
//            public Observable<String> call(Observable<Integer> observable) {
//                return observable.lift1().lift2().lift3().lift4();
//            }}
//            ...
//        Transformer liftAll = new LiftAllTransformer();
//        observable1.compose(liftAll).subscribe(subscriber1);
//        observable2.compose(liftAll).subscribe(subscriber2);
//        observable3.compose(liftAll).subscribe(subscriber3);
//        observable4.compose(liftAll).subscribe(subscriber4);

        /**
         * 我们来实践下
         */
        LiftAllTransformer liftAllTransformer = new LiftAllTransformer();
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
            }
        }).compose(liftAllTransformer).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "compose 最终结果是 =" + s);
            }
        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(2);
            }
        }).compose(liftAllTransformer).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "compose 最终结果是 =" + s);
            }
        });

        /**
         * 拆分的，没有建造者模式 一路下去
         */
        Observable<Integer> observable3 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(3);
            }
        });
        Subscriber<String> subscriber3 = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "compose 最终结果是 =" + s);
            }
        };
        observable3.compose(liftAllTransformer).subscribe(subscriber3);
    }

    public class LiftAllTransformer implements Observable.Transformer<Integer, String> {
        @Override
        public Observable<String> call(Observable<Integer> observable) {
            return observable.lift(new Observable.Operator<String, Integer>() {
                @Override
                public Subscriber<? super Integer> call(Subscriber<? super String> subscriber) {
                    return new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                            Log.e(TAG, "lift 第一次 onNext integer =" + integer);
                            subscriber.onNext(String.valueOf(integer + 1));
                        }
                    };
                }
            }).lift(new Observable.Operator<Boolean, String>() {
                @Override
                public Subscriber<? super String> call(Subscriber<? super Boolean> subscriber) {
                    return new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            Log.e(TAG, "lift 第二次 onNext String =" + s);
                            if ("2".equals(s)) {
                                subscriber.onNext(true);
                            } else {
                                subscriber.onNext(false);
                            }
                        }
                    };
                }
            }).lift(new Observable.Operator<String, Boolean>() {
                @Override
                public Subscriber<? super Boolean> call(Subscriber<? super String> subscriber) {
                    return new Subscriber<Boolean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            Log.e(TAG, "lift 第三次 onNext String =" + aBoolean);
                            if (aBoolean) {
                                subscriber.onNext("成功");
                            } else {
                                subscriber.onNext("失败");
                            }
                        }
                    };
                }
            });
        }
    }

    /**
     * lift()
     */
    private void lift() {
        // 注意：这不是 lift() 的源码，而是将源码中与性能、兼容性、扩展性有关的代码剔除后的核心代码。
        // 如果需要看源码，可以去 RxJava 的 GitHub 仓库下载。
//       public <R> Observable<R> lift(Operator<? extends R, ? super T> operator) {
//           return Observable.create(new OnSubscribe<R>() {
//               @Override
//               public void call(Subscriber subscriber) {
//                   Subscriber newSubscriber = operator.call(subscriber);
//                   newSubscriber.onStart();
//                   onSubscribe.call(newSubscriber);
//               }
//           });
//       }

        /**
         * 讲述 lift() 的原理只是为了让你更好地了解 RxJava ，从而可以更好地使用它。然而不管你是否理解了 lift() 的原理，
         * RxJava 都不建议开发者自定义 Operator 来直接使用 lift()，而是建议尽量使用已有的 lift() 包装方法（如 map() flatMap() 等）
         * 进行组合来实现需求，因为直接使用 lift() 非常容易发生一些难以发现的错误。
         */
        Observable.just(1)
                .lift(new Observable.Operator<String, Integer>() {
                    @Override
                    public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                        // 将事件序列中的 Integer 对象转换为 String 对象
                        return new Subscriber<Integer>() {
                            @Override
                            public void onNext(Integer integer) {
                                subscriber.onNext("字符串" + integer);
                            }

                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }
                        };
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "lift() onNextAction:" + s);
            }
        });

        /**
         * 我自己弄的一个lift链子
         * 逻辑如下：
         * int 转 string
         * string 转 boolean     是否等于"2"  等于:true 不等于:false
         * boolean 转 string     true:成功     false:失败
         */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
            }
        }).lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "lift 第一次 onNext integer =" + integer);
                        subscriber.onNext(String.valueOf(integer + 1));
                    }
                };
            }
        }).lift(new Observable.Operator<Boolean, String>() {
            @Override
            public Subscriber<? super String> call(Subscriber<? super Boolean> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "lift 第二次 onNext String =" + s);
                        if ("2".equals(s)) {
                            subscriber.onNext(true);
                        } else {
                            subscriber.onNext(false);
                        }
                    }
                };
            }
        }).lift(new Observable.Operator<String, Boolean>() {
            @Override
            public Subscriber<? super Boolean> call(Subscriber<? super String> subscriber) {
                return new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Log.e(TAG, "lift 第三次 onNext String =" + aBoolean);
                        if (aBoolean) {
                            subscriber.onNext("成功");
                        } else {
                            subscriber.onNext("失败");
                        }
                    }
                };
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "最终结果是 =" + s);
            }
        });
    }

    /**
     * throttleFirst()
     * 在每次事件触发后的一定时间间隔内丢弃新的事件。常用作去抖动过滤，例如按钮的点击监听器
     * <p>
     * 其实就是跟我之前的防快速点击utils 是一样的用途
     */
    private void throttleFirst() {
//        RxView.clickEvents(button) // RxBinding 代码，后面的文章有解释  TODO RxBinding
//            .throttleFirst(500, TimeUnit.MILLISECONDS) // 设置防抖间隔为 500ms
//            .subscribe(subscriber);

        RxView.setOnClickListener(throttleFirst, view -> {
            //设置日期格式精确到毫秒 SSS代表毫秒
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            // new Date()为获取当前系统时间
            String date= df.format(new Date());
            Log.e(TAG,"当前时间:"+date);

        });
    }

    /**
     * 打印 Student 数组 学生的课程表     1对多
     * flatMap() 1对多  ※相对于map()
     */
    private void method13() {
        List<Course> list1 = new ArrayList<>();
        list1.add(new Course("数学"));
        list1.add(new Course("语文"));
        list1.add(new Course("英语"));
        Student student1 = new Student("张三", 19, "173");
        student1.setCourses(list1);
        Student student2 = new Student("李四", 29, "175");
        student2.setCourses(list1);

        //集合方式一
        List<Student> stuList = new ArrayList<>();
        stuList.add(student1);
        stuList.add(student2);

        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                LogUtil.e(TAG, "course name:" + course.getName());
            }
        };
        Observable.from(stuList)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber);

        //数组 方式二
        Student[] students = {student1, student2};
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.e(TAG, "方式二 数组 course name:" + course.getName());
                    }
                });

        //扩展    嵌套的网络请求  ※网络请求拿到token，再去请求message
//        networkClient.token() // 返回 Observable<String>，在订阅时请求 token，并在响应后发送 token
//            .flatMap(new Func1<String, Observable<Messages>>() {
//                @Override
//                public Observable<Messages> call(String token) {
//                    // 返回 Observable<Messages>，在订阅时请求消息列表，并在响应后发送请求到的消息列表
//                    return networkClient.messages();
//                }
//            })
//            .subscribe(new Action1<Messages>() {
//                @Override
//                public void call(Messages messages) {
//                    // 处理显示消息列表
//                    showMessages(messages);
//                }
//            });

    }

    /**
     * 打印 Student 数组 学生的课程表
     * student 拿到课程集合进行遍历
     */
    private void method12() {
        List<Course> list1 = new ArrayList<>();
        list1.add(new Course("数学"));
        list1.add(new Course("语文"));
        list1.add(new Course("英语"));
        Student student1 = new Student("张三", 19, "173");
        student1.setCourses(list1);
        Student student2 = new Student("李四", 29, "175");
        student2.setCourses(list1);
        Student[] students = {student1, student2};
        Subscriber<Student> subscriber = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                List<Course> list = student.getCourses();
                for (int x = 0; x < list.size(); x++) {
                    LogUtil.e(TAG, student.getName() + ":" + list.get(x).getName());
                }
            }
        };
        Observable.from(students)
                .subscribe(subscriber);
    }

    /**
     * 打印 Student 数组 学生姓名
     * map() 是一对一的转化
     */
    private void method11() {
        Student[] students = {new Student("张三", 19, "173"),
                new Student("李四", 29, "175")};
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String name) {
                LogUtil.e(TAG, "name=" + name);
            }
        };
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(subscriber);
    }

    /**
     * Func1 返回值有
     * map(): 事件对象的直接变换，具体功能上面已经介绍过。它是 RxJava 最常用的变换
     */
    private void method10() {
        //sdk 读取图片
        Observable.just(Environment.getExternalStorageDirectory() +
                File.separator + "Pictures" + File.separator + "Screenshots" + File.separator + "p0.jpg")
                .subscribeOn(Schedulers.io())// 我理解为执行线程  这里其实就是just的地方(如果是create的话，其实就是call所在线程)
                .observeOn(Schedulers.io())//map 执行所在线程
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        Log.e(TAG, "map call 所在线程是" + Thread.currentThread().getName());
                        return BitmpUtils.getimage(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//回调线程 iv01 只能在UI即主线程渲染
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        Log.e(TAG, "Action1 所在线程是" + Thread.currentThread().getName());
                        iv01.setImageBitmap(bitmap);
                    }
                });

        /**
         * 自己弄了个create的形式
         */
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e(TAG, "Observable.OnSubscribe-----");
                subscriber.onNext("xxx");
            }
        }).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                return BitmpUtils.getimage(s);
            }
        }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                if (bitmap != null) {
                    iv01.setImageBitmap(bitmap);
                } else {
                    Log.e(TAG, "bitmap null");//TODO 居然不是走 err
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "err msg=" + throwable.getMessage());
            }
        });

        //直接加载drawable下的图片
        Observable.just(R.drawable.f0)
                .map(new Func1<Integer, Bitmap>() {
                    @Override
                    public Bitmap call(Integer integer) {
                        return BitmapFactory.decodeResource(getResources(), integer);
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        iv02.setImageBitmap(bitmap);
                    }
                });

        /**
         * 文章中的例子
         */
//        Observable.just("images/logo.png") // 输入类型 String
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String filePath) { // 参数类型 String
//                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
//                        }
//                })
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
//                        showBitmap(bitmap);
//                    }
//                });

    }

    /**
     * 加载资源图片   异步
     * 加载图片将会发生在 IO 线程，而设置图片则被设定在了主线程。这就意味着，
     * 即使加载图片耗费了几十甚至几百毫秒的时间，也不会造成丝毫界面的卡顿。
     */
    private void method09() {
        final int drawableRes = R.drawable.f0;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                LogUtil.e(TAG, "创建observable：" + Thread.currentThread().getName());

                //耗时操作
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "onCompleted():" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        LogUtil.e(TAG, "onNext():" + Thread.currentThread().getName());
                        iv01.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * Scheduler 调度器
     * .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程                     订阅所在线程
     * .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程       回调所在线程
     */
    private void method08() {
        //方式一
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        LogUtil.e(TAG, "int=" + integer + "," + Thread.currentThread().getName());
                    }
                });

        //方式二
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                LogUtil.e(TAG, "创建observable：" + Thread.currentThread().getName());
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onNext(4);
                subscriber.onCompleted();
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        LogUtil.e(TAG, "onStart():" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "onCompleted():" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "onError():" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtil.e(TAG, "onNext():" + "integer=" + integer + "," + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * 同步 加载资源图片
     */
    private void method07() {
        final int drawableRes = R.drawable.f0;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {
                LogUtil.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError");
                Toast.makeText(RxJavaActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                LogUtil.e(TAG, "onNext");
                iv01.setImageDrawable(drawable);
            }
        });
    }

    //打印字符串
    private void method06() {
        String[] names = {"路飞", "索隆", "娜美"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                LogUtil.e(TAG, "name:" + s);
            }
        });
    }

    /**
     * 除了 subscribe(Observer) 和 subscribe(Subscriber) ，
     * subscribe() 还支持不完整定义的回调，RxJava 会自动根据定义创建出 Subscriber 。
     */
    private void method05() {
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                LogUtil.e(TAG, "onNextAction onNext:" + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtil.e(TAG, "onErrorAction onError");
            }
        };
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                LogUtil.e(TAG, "onCompletedAction onCompleted");
            }
        };

        Observable observable = Observable.just("hello", "hi", "gameover");

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    /**
     * 创建Observable的快捷方法    just 和 from
     * 并 实现了  Observable订阅 Observer
     * <p>
     * ※
     * 有人可能会注意到， subscribe() 这个方法有点怪：
     * 它看起来是『observalbe 订阅了 observer / subscriber』而不是『observer / subscriber 订阅了 observalbe』，
     * 这看起来就像『杂志订阅了读者』一样颠倒了对象关系。这让人读起来有点别扭，
     * 不过如果把 API 设计成 observer.subscribe(observable) / subscriber.subscribe(observable) ，
     * 虽然更加符合思维逻辑，但对流式 API 的设计就造成影响了，比较起来明显是得不偿失的。
     */
    private void method04() {
        /**
         * just
         */
        Observable observable = Observable.just("hello", "hi", "gameover");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                LogUtil.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                LogUtil.e(TAG, "onNext:" + s);
            }
        };

        //实现订阅关系，observable调用了三次onNext和一次onCompleted
        /**
         * subscriber() 做了3件事：
         * 1.调用 Subscriber.onStart() 。这个方法在前面已经介绍过，是一个可选的准备方法。
         * 2.调用 Observable 中的 OnSubscribe.call(Subscriber) 。在这里，事件发送的逻辑开始运行。
         * 从这也可以看出，在 RxJava 中， Observable 并不是在创建的时候就立即开始发送事件，
         * 而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
         * 3.将传入的 Subscriber 作为 Subscription 返回。这是为了方便 unsubscribe().
         */
        Subscription subscription = observable.subscribe(observer);
        //true:已取消订阅  false：未取消
        LogUtil.e(TAG, "subscription.isUnsubscribed()=" + subscription.isUnsubscribed());
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        /**
         * from
         */
        String[] words = {"hello", "hi", "gameover"};
        Observable observable2 = Observable.from(words);

        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtil.e(TAG, "Subscriber onNext:" + s);
            }
        };
        observable2.subscribe(subscriber);
        if (!subscriber.isUnsubscribed()) {//TODO 这个其实应该在 onDestroy或onStrop
            subscriber.unsubscribe();
        }

    }

    /**
     * 创建 Observable create
     */
    private void method03() {
        /**
         * Observable 即被观察者
         * 当 Observable 被订阅的时候，OnSubscribe 的 call() 方法会自动被调用
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("hi");
                subscriber.onNext("aliluya");
                subscriber.onCompleted();
            }
        });

        /**
         * subscriber 形式
         */
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {//subscriber 有onStart()， 这是和observer 的区别之一
                super.onStart();
                LogUtil.e(TAG, "onStart"+"---isUnsubscribed="+isUnsubscribed());
            }

            @Override
            public void onCompleted() {
                LogUtil.e(TAG, "onCompleted"+"---isUnsubscribed="+isUnsubscribed());
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError"+"---isUnsubscribed="+isUnsubscribed());
            }

            @Override
            public void onNext(String s) {
                LogUtil.e(TAG, "onNext:" + s+"---isUnsubscribed="+isUnsubscribed());
            }
        };

        Subscription subscription = observable.subscribe(subscriber);
        //TODO rxjava是自动取消订阅
        LogUtil.e(TAG, "subscription.isUnsubscribed()=" + subscription.isUnsubscribed());
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            LogUtil.e(TAG, "subscription.isUnsubscribed()222=" + subscription.isUnsubscribed());
        }
        //subscriber 可以直接调用unsubscribe， 这是和observer 的区别之二
        LogUtil.e(TAG, "subscriber.isUnsubscribed()=" + subscriber.isUnsubscribed());
        if (!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
            LogUtil.e(TAG, "subscriber.isUnsubscribed()222=" + subscriber.isUnsubscribed());
        }

        //-----------------------------------------------------------------------------------

        Observable<Integer> observable1 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        });

        /**
         * observer 形式
         */
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                LogUtil.e(TAG, "observer onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "observer onError");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtil.e(TAG, "observer onNext=" + integer);
            }
        };

        Subscription subscription1 = observable1.subscribe(observer);
        LogUtil.e(TAG, "subscription1.isUnsubscribed()=" + subscription1.isUnsubscribed());
        if (!subscription1.isUnsubscribed()) {
            subscription1.unsubscribe();
        }
    }

    /**
     * observer,subscriber
     */
    private void method02() {
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                LogUtil.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                LogUtil.e(TAG, "onNext:" + s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                /**
                 * 相较于Observer 多一个onStart  并且 Subscriber是Observer的继承类
                 */
            }

            @Override
            public void onCompleted() {
                LogUtil.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                LogUtil.e(TAG, "onNext:" + s);
            }

        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.method02:
                method02();
                break;
            case R.id.method03:
                method03();
                break;
            case R.id.method04:
                method04();
                break;
            case R.id.method05:
                method05();
                break;
            case R.id.method06:
                method06();
                break;
            case R.id.method07://图片
                method07();
                break;
            case R.id.method08:
                method08();
                break;
            case R.id.method09:
                method09();
                break;
            case R.id.method10:
                method10();
                break;
            case R.id.method11:
                method11();
                break;
            case R.id.method12:
                method12();
                break;
            case R.id.method13:
                method13();
                break;
            case R.id.throttle_first:
                throttleFirst();
                break;
            case R.id.lift:
                lift();
                break;
            case R.id.compose:
                compose();
                break;
            case R.id.scheduler2:
                scheduler2();
                break;
            case R.id.doOnSubscribe:
                doOnSubscribe();
                break;
            case R.id.retrofit:
                retrofit();
                break;
            case R.id.doOnNext:
                doOnNext();
                break;
            case R.id.method14:
                method14();
                break;
            case R.id.method15:
                method15();
                break;
            case R.id.ofType1:
                ofType1();
                break;
            case R.id.ofType2:
                ofType2();
                break;
            case R.id.interval://rx内存泄漏问题
                interval();
                break;
            case R.id.rxlifecycle1://rxlifecycle 解决rx内存泄漏
                rxlifecycle1();
                break;
            case R.id.rxlifecycle2://rxlifecycle 解决rx内存泄漏
                rxlifecycle2();
                break;
        }
    }

    /**
     * 第二个订阅  绑定
     */
    private void rxlifecycle2() {
        Observable.interval(2, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, "每2秒就打印一次log");
                    }
                });
    }

    /**
     * rxlifecycle 解决rx内存泄漏
     * 1.ac 继承RxAppCompatActivity
     * 2.compose(this.bindToLifecycle())
     * <p>
     * ※ 我并没有在onDestroy里 unsubscribe()
     */
    private void rxlifecycle1() {
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(this.bindToLifecycle())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, "每1秒就打印一次log");
                    }
                });
    }

    private Subscription subscription;

    /**
     * 间隔执行某事，内存泄漏
     * 如果不在onDestroy 中执行subscription.unsubscribe(); 就会内存泄漏
     */
    private void interval() {
        subscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, "每一秒就打印一次log");
                    }
                });
    }

    private void ofType2() {
        Observable.just("first", 2, 3, "four", 5, 6, 7)
                .ofType(Integer.class)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 5;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "integer=" + integer);
                    }
                });
    }

    private void ofType1() {
        Observable.just("first", 2, 3, "four")
                .ofType(Integer.class)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "integer=" + integer);
                    }
                });
    }

//    private void method01(){
//        Observable.from(folders).flatMap(new Func1<File, Observable<File>>() {
//            @Override
//            public Observable<File> call(File file) {
//                return Observable.from(file.listFiles());
//            }
//        }).filter(new Func1<File, Boolean>() {
//            @Override
//            public Boolean call(File file) {
//                return file.getName().endsWith(".png");
//            }
//        }).map(new Func1<File, Bitmap>() {
//            @Override
//            public Bitmap call(File file) {
//                return getBitmapFromFile(file);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        imageCollectorView.addImage(bitmap);
//                    }
//                });
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
