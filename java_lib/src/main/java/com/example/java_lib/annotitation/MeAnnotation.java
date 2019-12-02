package com.example.java_lib.annotitation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 张唯
 * @类   说   明:	
 * @version 1.0
 * @创建时间：2014年11月26日 下午4:28:30
 * 
 */
// target注解 用来限制自定义注解修饰类型
//type 修饰类或者接口
//field 修饰成员变量
//method 修饰方法
//@Retention 元注解 自定义注解保留策略

/**
 * 处理基本类型之类 Class 类型  枚举类型
 * @ClassName: MeAnnotation
 * @说明:
 * @author 张唯
 * @date 2014年11月26日 下午5:15:44
 * 特性.如果元素的命名 value 可以省略不写 其他情况一律以 key = value;
 *      可以设置默认值
 * 
 * 
 * 
 */

@Retention(value= RetentionPolicy.RUNTIME) 
@Target(ElementType.FIELD)
public @interface MeAnnotation {
	// 声明元素
	int id() default 0;
	HelloWord helloWord();
	
}
