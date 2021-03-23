package com.wuhai.xiangxue;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import com.wuhai.xiangxue.p1_3.Click;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InjectUtils {


    public static void injectView(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();

        //获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field filed : declaredFields) {
            // 判断属性是否被InjectView注解声明
            if (filed.isAnnotationPresent(InjectView.class)){
                InjectView injectView = filed.getAnnotation(InjectView.class);
                //获得了注解中设置的id
                int id = injectView.value();
                View view = activity.findViewById(id);
                //反射设置 属性的值
                filed.setAccessible(true); //设置访问权限，允许操作private的属性
                try {
                    //反射赋值
                    filed.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectClick(Activity activity) {
        final Class<? extends Activity> cls = activity.getClass();

        //获得此类所有的成员
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (final Method method : declaredMethods) {
            // 判断属性是否被Click注解声明
            if (method.isAnnotationPresent(Click.class)){
                Click click = method.getAnnotation(Click.class);
                //获得了注解中设置的id
                int id = click.value();
                View view = activity.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(activity, v);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }


    public static void injectExtra(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Bundle bundle = activity.getIntent().getExtras();
        if(bundle == null){
            return;
        }

        //获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field filed : declaredFields) {
            // 判断属性是否被InjectView注解声明
            if (filed.isAnnotationPresent(InjectExtra.class)){
                InjectExtra injectExtra = filed.getAnnotation(InjectExtra.class);
                //获得了注解中设置的id
                String key = TextUtils.isEmpty(injectExtra.value()) ? filed.getName() : injectExtra.value();

                if(bundle.containsKey(key)){
                    Object obj = bundle.get(key);

                    // todo Parcelable数组类型不能直接设置，其他的都可以.
                    //获得数组单个元素类型
                    Class<?> componentType = filed.getType().getComponentType();
                    //当前属性是数组并且是 Parcelable（子类）数组
                    if(filed.getType().isArray() &&
                            Parcelable.class.isAssignableFrom(componentType)){
                        Object[] objs = (Object[]) obj;

                        //创建对应类型的数组并由objs拷贝
                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) filed.getType());
                        obj = objects;
                    }

                    //反射设置 属性的值
                    filed.setAccessible(true); //设置访问权限，允许操作private的属性
                    try {
                        //反射赋值
                        filed.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
