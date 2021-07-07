package com.wuhai.myframe2.ui.rxjava;

import android.os.Looper;
import android.view.View;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;


/**
 * 防止重复点击操作
 * 这个是指定 io.reactivex:rxjava:1.1.6  老版本的
 * 而社保那边的RxView用的版本是 io.reactivex.rxjava2:rxjava:2.2.0
 */

public class RxView {
    /**
     * 防止重复点击
     *
     * @param target 目标view
     * @param action 监听器
     */

    public static void setOnClickListeners(final RxViewOnClick<View> action, @NonNull View... target) {

        for (View view : target) {

            RxView.onClick(view).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<View>() {

                @Override
                public void call(View view) {
                    try {
                        action.onClick(view);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }

    /**
     * 防止重复点击 针对单个控件
     *
     * @param view   目标view
     * @param action 监听器
     */
    public static void setOnClickListener(@NonNull View view, final RxViewOnClick<View> action) {

        RxView.onClick(view).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<View>() {
            @Override
            public void call(View view) {
                try {
                    action.onClick(view);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 监听onclick事件防抖动
     *
     * @param view
     * @return
     */
    @CheckResult
    @NonNull
    private static Observable<View> onClick(@NonNull View view) {
        checkNotNull(view, "view == null");
        return Observable.create(new Observable.OnSubscribe<View>() {
            @Override
            public void call(Subscriber<? super View> subscriber) {
                checkUiThread();

                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onNext(view);
                    }
                };
                view.setOnClickListener(listener);
            }
        });
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    //线程判断
    public static void checkUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException(
                    "Must be called from the main thread. Was: " + Thread.currentThread());
        }
    }

    /**
     * A one-argument action. 点击事件转发接口
     *
     * @param <T> the first argument type
     */
    public interface RxViewOnClick<T> {
        void onClick(T t) throws ClassNotFoundException;
    }

}
