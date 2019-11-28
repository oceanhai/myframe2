package com.wuhai.myframe2.ui.rxjava;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.network.ServiceProvider;
import com.wuhai.retrofit.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

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
 */
public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "rxjava";

    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.method02)
    Button method02;
    @BindView(R.id.method03)
    Button method03;
    @BindView(R.id.method04)
    Button method04;
    @BindView(R.id.method05)
    Button method05;
    @BindView(R.id.method06)
    Button method06;
    @BindView(R.id.method07)
    Button method07;
    @BindView(R.id.method08)
    Button method08;
    @BindView(R.id.method09)
    Button method09;
    @BindView(R.id.method10)
    Button method10;
    @BindView(R.id.method11)
    Button method11;
    @BindView(R.id.method12)
    Button method12;
    @BindView(R.id.method13)
    Button method13;
    @BindView(R.id.method14)
    Button method14;
    @BindView(R.id.method15)
    Button method15;
    @BindView(R.id.ofType1)
    Button ofType1;
    @BindView(R.id.ofType2)
    Button ofType2;

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
     * retrofit + rxJava  TODO 这个去查看RetrofitNetworkRequestActivity的 get2 rx
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
//        serviceProvider.activityhomeRx(new RequestNetCallBack<RootResponse<ActivityHomeEntity>>() {
//            @Override
//            public void onSuccess(RootResponse<ActivityHomeEntity> dataResponse) {
//                LogUtil.e(TAG, "code = "+dataResponse.getCode());
//                LogUtil.e(TAG, "success = "+dataResponse.isSuccess());
//                LogUtil.e(TAG, "message = "+dataResponse.getMessage());
//                LogUtil.e(TAG, "result = "+dataResponse.getResult());
//            }
//
//            /**
//             * 重写
//             * @param responseError
//             */
//            @Override
//            public void onFailure(ResponseError responseError) {
//                LogUtil.e(TAG, "err = "+responseError.toString());
//            }
//
//            @Override
//            public Dialog baseGetLoadingDialog() {
//                return null;
//            }
//        });

    }

    /**
     * 打印 Student 数组 学生的课程表     1对多
     * flatMap()
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

        //数组 方式二
        Student[] students = {student1, student2};

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
//        Observable.from(students)
        Observable.from(stuList)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber);
    }

    /**
     * 打印 Student 数组 学生的课程表
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
     * map
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
//        Observable.just(Environment.getExternalStorageDirectory() +
//                File.separator + "Pictures" + File.separator + "Screenshots" + File.separator + "p0.png")
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String s) {
//                        return BitmpUtils.getimage(s);
//                    }
//                })
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        iv01.setImageBitmap(bitmap);
//                    }
//                });

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
                        iv01.setImageBitmap(bitmap);
                    }
                });
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
     */
    private void method08() {
        //方式一
//        Observable.just(1, 2, 3, 4)
//                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        LogUtil.e(TAG, "int=" + integer+","+Thread.currentThread().getName());
//                    }
//                });

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
     * 加载资源图片
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
//        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
//        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    /**
     * 创建Observable的快捷方法
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
        Observable observable = Observable.just("hello", "hi", "gameover");

        String[] words = {"hello", "hi", "gameover"};
        Observable observable2 = Observable.from(words);

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
    }

    /**
     * 创建 Observable
     */
    private void method03() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("hi");
                subscriber.onNext("aliluya");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                LogUtil.e(TAG, "onStart");
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

        Subscription subscription = observable.subscribe(subscriber);
        LogUtil.e(TAG, "subscription.isUnsubscribed()=" + subscription.isUnsubscribed());
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
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
            case R.id.method07:
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
        }
    }

    private void ofType2() {
        Observable.just("first",2,3,"four",5,6,7)
                .ofType(Integer.class)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer>5;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "integer="+integer);
                    }
                });
    }

    private void ofType1() {
        Observable.just("first",2,3,"four")
                .ofType(Integer.class)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "integer="+integer);
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
}
