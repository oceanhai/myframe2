package com.wuhai.myframe2.ui.hook;


import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * 参考文章
 * Hook技术看这篇就够了
 * https://blog.csdn.net/shoneworn/article/details/80476089?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight
 */
public class HookViewClickUtil {

    public static HookViewClickUtil getInstance() {
        return UtilHolder.mHookViewClickUtil;
    }

    private static class UtilHolder {
        private static HookViewClickUtil mHookViewClickUtil = new HookViewClickUtil();
    }

    public static void hookView(View view) {

        try {
            Class viewClazz = Class.forName("android.view.View");

            //事件监听器都是这个实例保存的
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }

            Object listenerInfoObj = listenerInfoMethod.invoke(view);

            Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

            Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");

            if (!onClickListenerField.isAccessible()) {
                onClickListenerField.setAccessible(true);
            }

            View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);

            //自定义代理事件监听器
            View.OnClickListener onClickListenerProxy = new OnClickListenerProxy(mOnClickListener);

            //更换
            onClickListenerField.set(listenerInfoObj, onClickListenerProxy);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //自定义的代理事件监听器
    private static class OnClickListenerProxy implements View.OnClickListener {
        private View.OnClickListener object;
        private int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime = 0;
        private OnClickListenerProxy(View.OnClickListener object) {
            this.object = object;
        }

        @Override
        public void onClick(View v) {
            //点击时间控制
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                Log.e("OnClickListenerProxy", "OnClickListenerProxy");
                if (object != null) object.onClick(v);
            }
        }

    }

}
