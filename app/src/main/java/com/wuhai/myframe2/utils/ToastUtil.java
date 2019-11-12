package com.wuhai.myframe2.utils;

import android.widget.Toast;

import com.wuhai.myframe2.application.BaseApplication;


/**
 *
 * 描述:吐司工具类,弹吐司用
 */
public class ToastUtil {

    private ToastUtil()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    private static Toast toast = null; //Toast的对象！
    /**
     * 弹词开关
     */
    private static   boolean IS_DEBUG_TOAST =true;

    // 设置Toase的开关
    public static void initToast(boolean is_debug_toast){
        IS_DEBUG_TOAST=is_debug_toast;
    }


    /**
     * 短时间弹吐司
     *
     * @param text 要吐司的文本
     */
    public static void showShort(String text) {

        if (toast == null) {
            toast = Toast.makeText(BaseApplication.application, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();

    }

    /**
     * 短时间谈吐司
     *
     * @param id 要吐司的资源id
     */
    public static void showShort(int id) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.application, id, Toast.LENGTH_SHORT);
        } else {
            toast.setText(id);
        }
        toast.show();
    }

    /**
     * 长时间弹吐司
     *
     * @param id 要吐司的资源id
     */
    public static void showLong(int id) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.application, id, Toast.LENGTH_LONG);
        } else {
            toast.setText(id);
        }
        toast.show();
    }

    /**
     * 长时间谈吐司
     *
     * @param text 要吐司的文本
     */
    public static void showLong(String text) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.application, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    /**
     * 调试用的吐司
     *
     * @param s
     */
    public static void showDebug(String s) {
        if (IS_DEBUG_TOAST) {
            showShort("Dubug："+s);
        }
    }
}
