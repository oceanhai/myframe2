package com.wuhai.myframe2.ui.hook;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 参考
 * Android Hook 机制之实战模拟
 * https://blog.csdn.net/botai2120/article/details/100951283?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight
 */
public class HookHelper {

    public static void hookOnClickListener(View view) throws Exception {
        // 第一步：反射得到 ListenerInfo 对象
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");

        getListenerInfo.setAccessible(true);

        // 第二步：得到原始的 OnClickListener事件方法
        Object listenerInfo = getListenerInfo.invoke(view);

        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");

        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");

        mOnClickListener.setAccessible(true);

        // 第三步：用 Hook代理类 替换原始的 OnClickListener
        View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);

        View.OnClickListener hookedOnClickListener = new HookedClickListenerProxy(originOnClickListener);

        mOnClickListener.set(listenerInfo, hookedOnClickListener);

    }

}
