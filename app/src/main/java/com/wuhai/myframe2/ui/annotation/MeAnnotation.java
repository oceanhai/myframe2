package com.wuhai.myframe2.ui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者 wh
 *
 * 创建日期 2020/8/24 15:37
 *
 * 描述：注解
 * target注解 用来限制自定义注解修饰类型
 * type 修饰类或者接口
 * field 修饰成员变量
 * method 修饰方法
 * @Retention 元注解 自定义注解保留策略
 *
 * 处理基本类型之类 Class 类型  枚举类型
 * 特性.如果元素的命名 value 可以省略不写 其他情况一律以 key = value;
 *      可以设置默认值
 *
 * 参考文章
 * https://blog.csdn.net/u013598660/article/details/45198019
 * https://blog.csdn.net/sbsujjbcy/article/details/51292001?utm_medium=distribute.pc_relevant.none-task-blog-baidulandingword-7&spm=1001.2101.3001.4242
 */

@Retention(value= RetentionPolicy.RUNTIME) 
@Target(ElementType.FIELD)
public @interface MeAnnotation {
	// 声明元素
	int id() default 0;
	HelloWord helloWord();
	
}
