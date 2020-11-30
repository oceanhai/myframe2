package com.wuhai.myframe2.ui.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Darren on 2017/2/4.
 * Email: 240336124@qq.com
 * Description:  IOC的View属性注解类
 */
// RUNTIME 运行时检测，CLASS 编译时butterKnife使用是这个  SOURCE 源码资源的时候
@Retention(RetentionPolicy.RUNTIME)
// FIELD 注解只能放在属性上    METHOD 方法上  TYPE 类上  CONSTRUCTOR 构造方法上
@Target(ElementType.FIELD)
public @interface ViewById {
    // 代表可以传值int类型  使用的时候：ViewById(R.id.xxx)
    int value();
}
